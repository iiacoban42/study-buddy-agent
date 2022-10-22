package furhatos.app.quiz.setting

import furhatos.records.Record
import furhatos.records.User
import furhatos.skills.UserManager

// User variables
class SkillData(
        var userName : String = "",
        var selectedTopic: String = "",
        var score : Int = 0,
        var lastScore : Int = 0,
        var interested : Boolean = true,
        var playing: Boolean = false,
        var played : Boolean = false,
        var questionsAsked : MutableList<String> = mutableListOf()
) : Record()

val User.quiz : SkillData
    get() = data.getOrPut(SkillData::class.qualifiedName, SkillData())

// Custom user getters for convenience
fun UserManager.interested() = list.filter {
    it.quiz.interested && !it.quiz.playing
}

fun UserManager.playing() = list.filter {
    it.quiz.playing
}

fun UserManager.notQuestioned(question: String) = list.filter {
    it.quiz.playing && !it.quiz.questionsAsked.contains(question)
}

