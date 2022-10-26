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
import memory.Answer
import memory.MemoryManager
import org.zeromq.SocketType
import org.zeromq.ZMQ
import recording.SoundRecorder
import java.nio.file.Paths

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

fun face_score(face: String): Int {
    when(face){
        "angry" -> return -4
        "disgust" -> return -3
        "fear" -> return -3
        "sad" -> return -1
        "neutral" -> return 0
        "happy" -> return 1
        "surprise" -> return 2
    }
    return -100000 //should not happen
}

fun voice_score(voice: String): Int {
    when(voice){
        "sa" -> return -2
        "boredo" -> return -1
        "neutra" -> return 0
        "happ" -> return 1
    }
    return -100000 //should not happen
}

var recorder: SoundRecorder? = null

var mem = MemoryManager()

val path = Paths.get("").toAbsolutePath().toString() + "\\src\\main\\kotlin\\furhatos\\app\\quiz\\db.json"

var answersList = HashMap<String, Pair<String, Answer>>()

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

                if(rawRequest != null){
                    val cleanRequest = String(rawRequest, 0, rawRequest.size - 1)
                    println("pulled some data : $cleanRequest")

                    val request = cleanRequest.split(" ")

                    val question_id = request[1].split(".")[0]

                    val face = request[4].split(",")[0]

                    val voice = request[8]

                    val face_value = face_score(face).toDouble()

                    val voice_value = voice_score(voice).toDouble()

                    val confidence: Double = (face_value + voice_value) / 2

                    var confidence_text = ""

                    if(confidence < -0.5)
                        confidence_text = "low"
                    if(confidence>= - 0.5 && confidence <= 0.5)
                        confidence_text = "medium"
                    if(confidence > 0.5)
                        confidence_text = "high"

                    if(answersList.containsKey(question_id)){
                        val ans = answersList[question_id]

                        if (ans != null) {

                            val answr = ans.second
                            answr.confidence = confidence_text

                            val userName = ans.first

                            mem.load(path)

                            val a = mem.getPersonMemory(userName)

                            mem.addToPerson(userName, answr)
                        }

                    }

                }

            } catch (e: Exception) {
                println(e)
            }

            withContext(Dispatchers.IO) {
                Thread.sleep(10_000)
            }
        }
    }

    onEntry {
        recorder?.finish()

        failedAttempts = 0
        users.current.quiz.selectedTopic
        // Set speech rec phrases based on the current question's answers
        furhat.setSpeechRecPhrases(QuestionSet.current.speechPhrases)

        val userName = users.getUser(users.current.id).quiz.userName

        val question_id = QuestionSet.current.id

        val confidence = mem.getPersonConfidence(userName, question_id)

        val correctness = mem.getQuestionCorrectness(userName, question_id)

        if(confidence != ""){

            random(
                { furhat.say("Here comes a question you've seen before.") },
                { furhat.say("Here's a question from last time.") },
                { furhat.say("Oh, I think you'll remember this one.") }
            )

            if (confidence == "low") {
                if (correctness == false)
                    random(
                        { furhat.say("You seemed puzzled by this last time. I think you'll get it right this time. I believe in you!") },
                        { furhat.say("You seemed puzzled by this last time. I know you can do it!") },
                        { furhat.say("You seemed puzzled by this last time. You have definitely improved. You got this!") }
                    )

                else random(
                    { furhat.say("You seemed puzzled by this last time, but you did get it right. Trust in your knowledge!") },
                    { furhat.say("You seemed puzzled by this last time, but you did get it right. No question is too hard for you!") },
                    { furhat.say("You seemed puzzled by this last time, but you did get it right. Do your best this time too!") }
                )

            }
            else if (confidence == "high"){
                if(correctness == false)
                    random(
                        { furhat.say("You seemed confident last time, but the answer was wrong. That's alright. Mistakes happen. Let's see how you do this time.") },
                        { furhat.say("You seemed confident last time, but the answer was wrong. Don't worry though. No question is too hard for you!") },
                        { furhat.say("You seemed confident last time, but the answer was wrong. I know you can do better this time!") }
                    )
                else random(
                    { furhat.say("You got this right last time. Let's see how well you still remember the lecture!") },
                    { furhat.say("You got this right last time. I know you'll get it right again!") },
                    { furhat.say("You got this right last time. I know it wasn't a lucky guess. You got this!") }
                )
            }
            else if(confidence == "medium"){
                if(correctness == false){
                    furhat.gesture(Gestures.Thoughtful)
                    random(
                        { furhat.say("Hmm... You didn't get it right last time. Let's see if my explanation helped.") },
                        { furhat.say("Hmm... You didn't get it right last time, but I'm sure you'll get it right this time.") },
                        { furhat.say("Hmm... You didn't get it right last time. Let's see how you do now.") }
                    )
                }

                else random(
                    { furhat.say("This question seemed a bit boring to you. Maybe the topic is too easy? Let's see.") },
                    { furhat.say("This question seemed a bit boring to you. Let's see if you still remember.") },
                    { furhat.say("This question seemed a bit boring to you. Can you get the right answer again?") }
                )
            }
        }

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

        val userName = users.getUser(users.current.id).quiz.userName

        val question_id = QuestionSet.current.id

        val confidence = mem.getPersonConfidence(userName, question_id)

        val correctness = mem.getQuestionCorrectness(userName, question_id)

        // If the user answers correct, we up the user's score and congratulates the user
        if (answer.correct) {
            furhat.gesture(Gestures.Smile)
            val ans = Answer(QuestionSet.current.id, true, "none")

            answersList[QuestionSet.current.id] = Pair(userName, ans)

            users.current.quiz.score++
            random(
                    { furhat.say("Great! That was the ${furhat.voice.emphasis("right")}  answer") },
                    { furhat.say("That's indeed ${furhat.voice.emphasis("correct")}") },
                    { furhat.say("That's ${furhat.voice.emphasis("right")}") }
            )

            if(confidence != ""){
                if(confidence == "low" && correctness == false){
                    furhat.gesture(Gestures.BigSmile)
                    furhat.say("See? I know you could do it")
                }
                else if (confidence == "high" && correctness == true){
                    furhat.gesture(Gestures.BigSmile)
                    furhat.say("I see you still remember the lecture. I'm glad")
                }
                else if(confidence == "medium" && correctness == false){
                    furhat.gesture(Gestures.BigSmile)
                    furhat.say("I see the explanation from last time helped. I'm glad")
                }
                else if(confidence == "high" && correctness == false){
                    furhat.say("You got it right this time. Good job!")
                }

            }

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

            if(confidence != "") {
                if (confidence == "low" && correctness == false) {
                    furhat.gesture(Gestures.Smile)
                    furhat.say("It's okay. Let's look at the right answer again.")
                } else if (correctness == true) {
                    furhat.gesture(Gestures.Smile)
                    furhat.say("That being said, you did get it right last time. Maybe you forgot in the meantime. Let's refresh your memory.")
                } else if (confidence == "medium" && correctness == false) {
                    furhat.gesture(Gestures.Thoughtful)
                    furhat.say("Hmm... maybe this is a bit too difficult. Let's look at the right answer again.")
                } else if (confidence == "high" && correctness == false) {
                    furhat.gesture(Gestures.Surprise)
                    furhat.say("Oh my! Did I forget to explain that? Let's see the right answer")
                }
            }

            val explanation = QuestionSet.current.explanation

            furhat.say(explanation)
        }

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