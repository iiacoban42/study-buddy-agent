package furhatos.app.quiz.flow.main

import furhatos.app.quiz.flow.Parent
import furhatos.app.quiz.setting.playing
import furhatos.app.quiz.setting.quiz
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.users


// End of game, announcing results
val EndGame: State = state(parent = Parent) {
    var skipNext = false

    onEntry {
        playing = false
        val score = users.current.quiz.score

        val grade = 10 * score / maxRounds

        if (grade < 5 ){
            furhat.say("You seem to be struggling with this topic.")
        }
        else if (grade < 8 ){
            furhat.say("Good job!")
        }
        else{
            furhat.say("Excellent! You really master this topic!")
        }

        furhat.say("Thanks for playing!")

        // Resetting user state variables
        users.playing().forEach {
            it.quiz.playing = false
            it.quiz.played = true
            it.quiz.lastScore = it.quiz.score
        }

//        mem.store(path)
//
//        println("wrote to file")


        delay(1000)

        goto(Idle)
    }
}