package furhatos.app.quiz

import furhatos.app.quiz.questions.QuestionSet
import furhatos.nlu.ComplexEnumEntity
import furhatos.nlu.EnumEntity
import furhatos.nlu.EnumItem
import furhatos.nlu.Intent
import furhatos.nlu.common.PersonName
import furhatos.util.Language

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

class MyNameIsResponse(val name: furhatos.nlu.common.PersonName = PersonName()) : ComplexEnumEntity() {
    override fun getEnum(lang: Language): List<String> {
        return listOf("Call me @name", "My name is @name", "I'm @name", "I am @name", "@name")
    }
}
