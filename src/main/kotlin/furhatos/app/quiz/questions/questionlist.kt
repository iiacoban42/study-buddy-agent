package furhatos.app.quiz.questions

/**
 * The questions are structured like
 *  -The question
 *  -The correct answer, followed by alternative pronounciations
 *  -A list of other answers followed by their alternatives
 */

//https://www.dkfindout.com/us/quiz/space/take-solar-system-quiz/

val questionsEnglish = mutableListOf(

        Question("What is the Great Red Spot on Jupiter?",
                answer = listOf("a storm", "storm"),
                alternatives = listOf(listOf("a volcano",  "volcano"),
                        listOf("a crater", "crater"),
                        listOf("a lake", "lake")),
                explanation = "Jupiter has no volcanoes, craters or lakes. The Great Red Spot is a massive storm in Jupiter's atmosphere."

                ),

        Question("Which of this planet is the smallest?",
        answer = listOf("Mercury"),
        alternatives = listOf(listOf("Uranus"),
                listOf("Jupiter"),
                listOf("Earth")),
        explanation = "Jupiter is 142,984 km wide, Uranus is 51,118 km wide and Earth is 12,756 km wide. So Mercury is the smallest with 4,879 km wide"

        ),

        Question("Which planet do the moons Oberon and Titania belong to?",
        answer = listOf("Uranus"),
        alternatives = listOf(listOf("Jupiter"),
                listOf("Earth"),
                listOf("Venus")),
        explanation = "Jupiter has more than 60 moons but not Oberon and Titania, Earth is orbited by the moon and Venus has no moons. " +
                "Oberon and Titania are two of Uranus' 27 moons"

),
        Question("The largest volcano in the solar system is called Olympus Mons? Where is it?",
                answer = listOf("Mars"),
                alternatives = listOf(listOf("Venus"),
                        listOf("Earth"),
                        listOf("Jupiter")),
                explanation = "Venus has more than 1600 volcanoes but Olympus Mons is not one of them. " +
                        "Earth is not home to Olympus Mons. Earth's largest volcano is Mauna Loa, Hawaii." +
                        "Jupiter has no volcanoes" +
                        "Olympus Mons, the largest volcano in the solar system is on Mars"
),

        Question("What are comets made off?",
                answer = listOf("Snow ice and dust", "dust", "snow", "ice", "snow ice", "ice dust", "snow dust"),
                alternatives = listOf(listOf("Poisonous liquid"),
                        listOf("Rusty metal", "metal"),
                        listOf("Hot liquid rock", "liquid rock")),
                explanation = "Comets are not made of poisonous liquids, rusty metal or hot liquid rock." +
                        "Comets are mostly made of snow ice and dust."
        ),

        Question("What is the Sun mainly made from?",
        answer = listOf("Gas"),
        alternatives = listOf(listOf("Molten iron"),
                listOf("Rock"),
                listOf("Liquid lava", "Lava")),
        explanation = "The Sun is not made of molten iron, rock or lava" +
                "The Sun is mainly made from hydrogen and helium gas"
        ),
        Question("Which of these best describes the atmosphere surrounding Venus?",
                answer = listOf("Hot and poisonous", "poisonous"),
                alternatives = listOf(listOf("Cold and snowy", "snowy"),
                        listOf("Cold and wet", "wet"),
                        listOf("Bright and sunny", "Sunny")),
                explanation = "Venus is the hottest planet in the solar system, temperatures reach 464 degrees Celsius, however, " +
                        "very little sunlight passes through the thick clouds." +
                        "Venus is surrounded by thick, acidic clouds around 50 km above its surface."
        ),

        Question("How many moons does Mars have?",
        answer = listOf("Two"),
        alternatives = listOf(listOf("One", "Just one"),
                listOf("50"),
                listOf("13")),
        explanation = "Mars has two moons, Phobos and Deimos"
        ),

        Question("Which is the closest planet to the sun?",
                answer = listOf("Mercury"),
                alternatives = listOf(listOf("Neptune"),
                        listOf("Earth"),
                        listOf("Venus")),
                explanation = "Neptune is the furthest planet from the sun, Earth is the third planet from the sun, Venus is the second planet form the sun"+
                        "while, Mercury is the closest planet to the sun."
        ),

        Question("Where is the asteroid belt?",
                answer = listOf("Between Mars and Jupiter"),
                alternatives = listOf(listOf("Between Earth and Venus"),
                        listOf("Between Earth and Mars"),
                        listOf("Between Jupiter and Saturn")),
                explanation = "The asteroid belt is found in a region between the planets Mars and Jupiter."
        )

        )