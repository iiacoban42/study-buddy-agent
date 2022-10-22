package furhatos.app.quiz.flow.main

import furhatos.app.quiz.*
import furhatos.app.quiz.flow.Parent
import furhatos.app.quiz.questions.*
import furhatos.app.quiz.setting.interested
import furhatos.app.quiz.setting.playing
import furhatos.app.quiz.setting.quiz
import furhatos.flow.kotlin.*
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes
import furhatos.records.User

val Idle: State = state {
    onEntry {
        /*
            Loop through all (potentially) interested users.
            Goto calls are used since users may enter and leave
            while we are querying other users and we want to
            ask all users before moving on. I.e we want to update the
            users.interested() list of users.
          */
        users.interested().forEach {
            furhat.attend(it)
            goto(Start(it))
        }
        // Once no more user, start the game with all interested users
        if (users.playing().isNotEmpty()) {
            furhat.attendAll()
            goto(NewGame)
        }

    }

    onUserEnter(instant = true) {
        /* Say hi and query a new user if it's the only interested user.
            Other interested users would already be greeted at this point.
            If not, we glance at the user and keep doing whatever we were doing.
         */
        if (users.interested().count() == 1) {
            furhat.attend(it.id)
            furhat.say("Hello there")
            goto(Start(it))
//            goto(QueryPerson(it))
        } else {
            furhat.glance(it.id, async=true)
        }
    }

    onUserLeave(instant = true) {
        if (users.count > 0) {
            furhat.attend(users.other)
        } else {
            furhat.attendNobody()
        }
    }

    onResponse{
        reentry()
    }

    onNoResponse {
        reentry()
    }
}

// Variables

val maxRounds = 5
var rounds = 0
var playing = false

fun QueryPerson(user: User) = state(parent = Parent) {
    onEntry {
        if (!user.quiz.played) {
            furhat.ask("Do you want to learn about the solar system? We could also talk about black holes and space exploration.")
        } else {
            furhat.ask("Do you want to practice with some more questions? ")
        }
    }

    onResponse<Yes> {
        user.quiz.playing = true
        furhat.say("great!")
        goto(Idle)
    }

    onResponse<No> {
        user.quiz.interested = false
        furhat.say("oh well")
        goto(Idle)
    }

    onResponse<SelectTopic> {
        val topic = parseTopic(it.intent.topic)
        if(topic == null){
            furhat.say("I can't teach you that at the moment, but I'll let you know when I'll learn it myself.")
            reentry()
        }else {
            user.quiz.selectedTopic = topic
            goto(Lesson(topic))
        }
    }

    onNoResponse {
        random(
            { furhat.say("Too slow! Here comes the next question") },
            { furhat.say("A bit too slow amigo! Get ready for the next question") }
        )
        reentry()
    }

}


fun Lesson(topic: String) = state(parent = Parent) {
    onEntry {

        furhat.say("Alright, here we go!")

        if (topic == BLACK_HOLES){
            questions = questionsBlackHoles
            furhat.say("You want to know about black holes")
        }
        else if (topic == SOLAR_SYSTEM){
            questions = questionsSolarSystem
            furhat.say("You want to know about the solar system")
        }
        else if (topic == SPACE_EXPLORATION){
            questions = questionsSpaceExploration
            furhat.say("You want to know about space exploration")
        }

        furhat.say("")


        QuestionSet.next()
        furhat.attend(users.playing().first())
        goto(AskQuestion)
    }
}