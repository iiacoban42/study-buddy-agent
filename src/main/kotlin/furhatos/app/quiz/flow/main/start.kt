package furhatos.app.quiz.flow.main

import furhatos.app.quiz.nlu.MyNameIsResponse
import furhatos.app.quiz.flow.Parent
import furhatos.app.quiz.setting.quiz
import furhatos.flow.kotlin.*
import furhatos.records.User
import org.json.JSONObject
import org.json.JSONTokener
import java.io.File
import java.nio.file.Paths

const val storage = "src/main/kotlin/furhatos/app/quiz/db.json"

fun Start(user: User) = state(parent = Parent)  {
    onEntry {

        furhat.say("What is your name?")

        furhat.glance(users.current)
        furhat.listen(timeout = 20000);
    }

    onReentry {
        furhat.listen(timeout = 10000)
    }

    onResponse<MyNameIsResponse> {
        val name = it.intent.name
        furhat.say("Hello $name!")


//        val path = Paths.get("").toAbsolutePath().toString() + "\\src\\main\\kotlin\\furhatos\\app\\quiz\\db.json"
//
//        mem.load(path)
//
//        val a = mem.getPersonMemory(name.toString())

//        if (a.isNullOrEmpty()){
        furhat.say("It's nice to meet you!")
//        }
//        else{
//            furhat.say("Welcome back!")
//        }
        user.quiz.userName = name.toString()


        goto(QueryPerson(user))

    }


    onNoResponse {
        furhat.say("Ok, let's go on")

        goto(QueryPerson(user))
    }
}