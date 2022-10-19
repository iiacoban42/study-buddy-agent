package furhatos.app.quiz.flow.main

import furhatos.app.quiz.MyNameIsResponse
import furhatos.app.quiz.flow.Parent
import furhatos.flow.kotlin.*
import furhatos.records.User
import org.json.JSONObject
import org.json.JSONTokener
import java.io.File

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


        val myfile = File(storage)
        val fileText = File(storage).readText(Charsets.UTF_8)

        val data: JSONObject = JSONTokener(fileText).nextValue() as JSONObject

        if (!data.has(name.toString())){
            furhat.say("It's nice to meet you!")
            data.put(name.toString(), mutableMapOf<String, Float>())
        }
        else{
            furhat.say("Welcome back!")
        }

        myfile.writeText(data.toString())

        goto(QueryPerson(user))

    }


    onNoResponse {
        furhat.say("Ok, let's go on")

        goto(QueryPerson(user))
    }
}