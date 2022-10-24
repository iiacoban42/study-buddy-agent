package furhatos.app.quiz.flow.main

//import furhatos.app.quiz.setting.nextPlaying
import furhatos.app.quiz.flow.Parent
import furhatos.app.quiz.nlu.AnswerOption
import furhatos.app.quiz.nlu.DontKnow
import furhatos.app.quiz.nlu.RequestRepeatOptions
import furhatos.app.quiz.nlu.RequestRepeatQuestion
import furhatos.app.quiz.questions.QuestionSet
import furhatos.app.quiz.setting.quiz
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures
import furhatos.nlu.common.RequestRepeat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import org.zeromq.SocketType
import org.zeromq.ZMQ
import recording.SoundRecorder

fun record(id: String): SoundRecorder? {
    val sound = SoundRecorder("-1")
    var recorder = sound.initialize(id)

    GlobalScope.async {
        println("Recording...")

        recorder.startRecording(recorder)

        println("Done recording")
    }

    return recorder
}

var recorder: SoundRecorder? = null

val AskQuestion: State = state(parent = Parent) {
    var failedAttempts = 0

    val context = ZMQ.context(1)
    val port = "5657"
    val socket = context.socket(SocketType.SUB)
    socket.isConflate = true
    socket.connect("tcp://127.0.0.1:$port")
    socket.subscribe("123")

    GlobalScope.async {
        while (true) {
            println("Checking received messages...")
            try {
                val rawRequest = socket.recv()
                val cleanRequest = String(rawRequest, 0, rawRequest.size - 1)
                println("pulled some data : $cleanRequest")
            } catch (e: Exception) {
                println(e)
            }

            withContext(Dispatchers.IO) {
                Thread.sleep(5_000)
            }
        }
    }

    onEntry {
        recorder?.finish()

        failedAttempts = 0
        users.current.quiz.selectedTopic
        // Set speech rec phrases based on the current question's answers
        furhat.setSpeechRecPhrases(QuestionSet.current.speechPhrases)

        recorder = record(QuestionSet.current.id)

        // Ask the question followed by the options
        furhat.ask(QuestionSet.current.text + " " + QuestionSet.current.getOptionsString())
    }

    // Here we re-state the question
    onReentry {
        recorder?.finish()

        recorder = record(QuestionSet.current.id)

        failedAttempts = 0
        furhat.ask("The question was, ${QuestionSet.current.text} ${QuestionSet.current.getOptionsString()}")
    }

    // User is answering with any of the alternatives
    onResponse<AnswerOption> {
        recorder?.finish()

        val answer = it.intent

        // If the user answers correct, we up the user's score and congratulates the user
        if (answer.correct) {
            furhat.gesture(Gestures.Smile)
            users.current.quiz.score++
            random(
                    { furhat.say("Great! That was the ${furhat.voice.emphasis("right")}  answer") },
                    { furhat.say("That's indeed ${furhat.voice.emphasis("correct")}") },
                    { furhat.say("That's ${furhat.voice.emphasis("right")}") }
            )
            /*
            If the user answers incorrect, we give another user the chance of answering if one is present in the game.
            If we indeed ask another player, the furhat.ask() interrupts the rest of the handler.
             */
        } else {
            furhat.gesture(Gestures.BrowFrown)

            random(
                { furhat.say("Sorry, that was ${furhat.voice.emphasis("not")} the right answer") },
                { furhat.say("Sorry, that was ${furhat.voice.emphasis("not")} correct") },
                { furhat.say("Sorry, that was ${furhat.voice.emphasis("not")} right") }
            )

            val explanation = QuestionSet.current.explanation

            furhat.say(explanation)
        }

//        println("The question was, ${QuestionSet.current.text}")
//        val rawRequest = socket.recv()
//        val cleanRequest = String(rawRequest, 0, rawRequest.size - 1)
//        println("pulled some data : $cleanRequest")

        // Check if the game has ended and if not, goes to a new question
        if (++rounds >= maxRounds) {
            furhat.say("That was the last question")
            goto(EndGame)
        } else {
            goto(NewQuestion)
        }
    }

    // The users answer that they don't know
    onResponse<DontKnow> {
        recorder?.finish()

        furhat.say("Too bad. Here comes the next question")
        goto(NewQuestion)
    }

    onResponse<RequestRepeat> {
        recorder?.finish()

        reentry()
    }

    onResponse<RequestRepeatQuestion> {
        recorder?.finish()

        furhat.gesture(Gestures.BrowRaise)
        furhat.ask(QuestionSet.current.text)
    }

    // The user wants to hear the options again
    onResponse<RequestRepeatOptions> {
        recorder?.finish()

        furhat.gesture(Gestures.Surprise)
        random(
                { furhat.ask("They are ${QuestionSet.current.getOptionsString()}") },
                { furhat.ask(QuestionSet.current.getOptionsString()) }
        )
    }

    // If we don't get any response, we assume the user was too slow
    onNoResponse {
        recorder?.finish()

        random(
                { furhat.say("Too slow! Here comes the next question") },
                { furhat.say("A bit too slow amigo! Get ready for the next question") }
        )
        goto(NewQuestion)
    }

    /* If we get a response that doesn't map to any alternative or any of the above handlers,
        we track how many times this has happened in a row and give them two more attempts and
        finally moving on if we still don't get it.
     */
    onResponse {
        recorder?.finish()

        failedAttempts++
        if(failedAttempts < 3)
            recorder = record(QuestionSet.current.id)
        when (failedAttempts) {
            1 -> furhat.ask("I didn't get that, sorry. Try again!")
            2 -> {
                furhat.say("Sorry, I still didn't get that")
                furhat.ask("The options are ${QuestionSet.current.getOptionsString()}")
            }
            else -> {
                furhat.say("Still couldn't get that. Let's try a new question")
                goto(NewQuestion)
            }
        }
    }
}

val NewQuestion = state(parent = Parent) {
    onEntry {
        /*
            If more than one player, we determine what user to target next here, based on the shouldChangeUser boolean
         */

        if (!users.current.isAttendingFurhat) {
            furhat.say {
                random {
                    block {
                        +"But then I do want you to pay attention"
                        +Gestures.BigSmile
                    }
                    +"Look at me, I'm captain now"
                    +"Could you pay some attention to me"
                }
            }
        }
        // Ask new question
        QuestionSet.next()
        goto(AskQuestion)
    }
}