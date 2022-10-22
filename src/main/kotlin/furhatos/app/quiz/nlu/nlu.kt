package furhatos.app.quiz.nlu

import furhatos.app.quiz.questions.QuestionSet
import furhatos.nlu.ComplexEnumEntity
import furhatos.nlu.EnumEntity
import furhatos.nlu.EnumItem
import furhatos.nlu.Intent
import furhatos.nlu.common.PersonName
import furhatos.util.Language

class Topic() : EnumEntity()

const val BLACK_HOLES = "blackHoles"
const val SOLAR_SYSTEM = "solarSystem"
const val SPACE_EXPLORATION = "spaceExploration"
fun parseTopic(topic: Topic?): String? {
    if (topic == null) {
        return null;
    }
    val value = topic.value;
    if (value?.contains("black hole", ignoreCase=true) == true) {
        return BLACK_HOLES
    } else if (value?.contains("solar system", ignoreCase=true) == true) {
        return SOLAR_SYSTEM
    } else if (value?.contains("space exploration", ignoreCase=true) == true) {
        return SPACE_EXPLORATION
    }

    return null;
}

class DontKnow : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "I don't know",
                "don't know",
                "no idea",
                "I have no idea"
        )
    }
}

class RequestRules : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "what are the rules",
                "how does it work"
        )
    }
}

class RequestRepeatQuestion : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "what was the question",
                "can you repeat the question",
                "what was the question again"
        )
    }
}

class RequestRepeatOptions : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "what are the options",
                "can you repeat the options",
                "what were the options"
        )
    }
}

class AnswerOption : EnumEntity {

    var correct : Boolean = false
    // Every entity and intent needs an empty constructor.
    constructor() {
    }

    // Since we are overwriting the value, we need to use this custom constructor
    constructor(correct : Boolean, value : String) {
        this.correct = correct
        this.value = value
    }

    override fun getEnumItems(lang: Language): List<EnumItem> {
        return QuestionSet.current.options;
    }

}


class SelectTopic(val topic: Topic? = null) : ComplexEnumEntity() {
    override fun getEnum(lang: Language): List<String> {
        return listOf("@topic")
    }
}

class MyNameIsResponse(val name: furhatos.nlu.common.PersonName = PersonName()) : ComplexEnumEntity() {
    override fun getEnum(lang: Language): List<String> {
        return listOf("Call me @name", "My name is @name", "I'm @name", "I am @name", "@name")
    }
}
