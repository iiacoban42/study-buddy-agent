package furhatos.app.quiz.questions

/**
 * The questions are structured like
 *  -The question
 *  -The correct answer, followed by alternative pronounciations
 *  -A list of other answers followed by their alternatives
 */

//https://www.dkfindout.com/us/quiz/space/take-solar-system-quiz/

var questions = mutableListOf<Question>()

val questionsSolarSystem = mutableListOf(

        Question("What is the Great Red Spot on Jupiter?",
                answer = listOf("a storm", "storm"),
                alternatives = listOf(listOf("a volcano",  "volcano"),
                        listOf("a crater", "crater"),
                        listOf("a lake", "lake")),
                explanation = "Jupiter has no volcanoes, craters or lakes. The Great Red Spot is a massive storm in Jupiter's atmosphere.",
                id = "Solar_system_Jupiter"
        ),

        Question("Which of this planet is the smallest?",
                answer = listOf("Mercury"),
                alternatives = listOf(listOf("Uranus"),
                        listOf("Jupiter"),
                        listOf("Earth")),
                explanation = "Jupiter is 142,984 km wide, Uranus is 51,118 km wide and Earth is 12,756 km wide. So Mercury is the smallest with 4,879 km wide",
                id = "Solar_system_smallest"

        ),

        Question("Which planet do the moons Oberon and Titania belong to?",
                answer = listOf("Uranus"),
                alternatives = listOf(listOf("Jupiter"),
                        listOf("Earth"),
                        listOf("Venus")),
                explanation = "Jupiter has more than 60 moons but not Oberon and Titania, Earth is orbited by the moon and Venus has no moons. " +
                        "Oberon and Titania are two of Uranus' 27 moons",
                id = "Solar_system_Oberon_Titania"

        ),
        Question("The largest volcano in the solar system is called Olympus Mons? Where is it?",
                answer = listOf("Mars"),
                alternatives = listOf(listOf("Venus"),
                        listOf("Earth"),
                        listOf("Jupiter")),
                explanation = "Venus has more than 1600 volcanoes but Olympus Mons is not one of them. " +
                        "Earth is not home to Olympus Mons. Earth's largest volcano is Mauna Loa, Hawaii." +
                        "Jupiter has no volcanoes" +
                        "Olympus Mons, the largest volcano in the solar system is on Mars",
                id = "Solar_system_volcano"
        ),

        Question("What are comets made off?",
                answer = listOf("Snow ice and dust", "dust", "snow", "ice", "snow ice", "ice dust", "snow dust"),
                alternatives = listOf(listOf("Poisonous liquid"),
                        listOf("Rusty metal", "metal"),
                        listOf("Hot liquid rock", "liquid rock")),
                explanation = "Comets are not made of poisonous liquids, rusty metal or hot liquid rock." +
                        "Comets are mostly made of snow ice and dust.",
                id = "Solar_system_comets"
        ),

        Question("What is the Sun mainly made from?",
                answer = listOf("Gas"),
                alternatives = listOf(listOf("Molten iron"),
                        listOf("Rock"),
                        listOf("Liquid lava", "Lava")),
                explanation = "The Sun is not made of molten iron, rock or lava" +
                        "The Sun is mainly made from hydrogen and helium gas",
                id = "Solar_system_sun"
        ),
        Question("Which of these best describes the atmosphere surrounding Venus?",
                answer = listOf("Hot and poisonous", "poisonous"),
                alternatives = listOf(listOf("Cold and snowy", "snowy"),
                        listOf("Cold and wet", "wet"),
                        listOf("Bright and sunny", "Sunny")),
                explanation = "Venus is the hottest planet in the solar system, temperatures reach 464 degrees Celsius, however, " +
                        "very little sunlight passes through the thick clouds." +
                        "Venus is surrounded by thick, acidic clouds around 50 km above its surface.",
                id = "Solar_system_Venus"
        ),

        Question("How many moons does Mars have?",
                answer = listOf("Two"),
                alternatives = listOf(listOf("One", "Just one"),
                        listOf("50"),
                        listOf("13")),
                explanation = "Mars has two moons, Phobos and Deimos",
                id = "Solar_system_Mars"
        ),

        Question("Which is the closest planet to the sun?",
                answer = listOf("Mercury"),
                alternatives = listOf(listOf("Neptune"),
                        listOf("Earth"),
                        listOf("Venus")),
                explanation = "Neptune is the furthest planet from the sun, Earth is the third planet from the sun, Venus is the second planet form the sun"+
                        "while, Mercury is the closest planet to the sun.",
                id = "Solar_system_closest"
        ),

        Question("Where is the asteroid belt?",
                answer = listOf("Between Mars and Jupiter"),
                alternatives = listOf(listOf("Between Earth and Venus"),
                        listOf("Between Earth and Mars"),
                        listOf("Between Jupiter and Saturn")),
                explanation = "The asteroid belt is found in a region between the planets Mars and Jupiter.",
                id = "Solar_system_asteroid"
        )

)

val questionsBlackHoles = mutableListOf(

        Question("What is the name of the stretching process, while reaching a black hole?",
                answer = listOf("spaghetti", "spaghettification"),
                alternatives = listOf(listOf("pasta", "pastification"),
                        listOf("lasagna", "lasagnification"),
                        listOf("penne", "pennification")),
                explanation = "The process is named spagehtification, after spaghetti, which is long, stringy pasta.",
                id = "Black_holes_spaghetti"

        ),

        Question("Which of these is a type of black hole? ",
                answer = listOf("stellar", "star-like"),
                alternatives = listOf(listOf("large"),
                        listOf("big"),
                        listOf("huge")),
                explanation = "The four types of black holes are: stellar, intermediate, supermassive, and miniature",
                id = "Black_holes_type"
        ),

        Question("What is a supernova?",
                answer = listOf("a powerful and luminous explosion of a star", "luminous explosion of a star", "powerful explosion of a star", "explosion of a star", "star explosion"),
                alternatives = listOf(listOf("solar wind", "wind from the sun"),
                        listOf("a large comet", "comet", "large comet"),
                        listOf("the core of a star", "star core", "stellar core", "core of a star")),
                explanation = "A supernova is an explosion produced at the end of a massive star's life.",
                id = "Black_holes_supernova"
        ),

        Question("What is the name of the supermassive black hole in the Milky Way?",
                answer = listOf("Sagittarius A*", "Sagittarius A", "Sagittarius", "Sagittarius A star"),
                alternatives = listOf(listOf("Gemini A*", "Gemini A", "Gemini", "Gemini A star"),
                        listOf("Psices A*", "Pisces A", "Pisces", "Pisces A star"),
                        listOf("Taurus A*", "Taurus A", "Taurus", "Taurus A star")),
                explanation = "Sagittarius A* is the supermassive black hole at the center of our galaxy.",
                id = "Black_holes_sagittarius"
        ),
        Question("When did the big bang approximately happen?",
                answer = listOf("13.7 billion years ago", "billions of years ago", "13.7 billion years"),
                alternatives = listOf(listOf("10.7 thousand years ago", "thousands of years ago", "10.7 thousand years"),
                        listOf("300 thousand years ago", "hundreds of thousands of years ago", "300 thousand years"),
                        listOf("3.4 million years ago", "millions of years ago", "3.4 million years")),
                explanation = "The universe is thought to have begun with the big bang, approximately 13.7 billion years ago.",
                id = "Black_holes_big_bang"
        ),

        Question("How did astronomers eventually identify Sagittarius A*?",
                answer = listOf("they looked for stars that seem to orbit nothing", "stars that orbit nothing", "orbit nothing", "seem to orbit nothing"),
                alternatives = listOf(listOf("they found it with a powerful telescope", "telescope", "powerful telescope"),
                        listOf("they took images from satellites", "satellite", "satellite images"),
                        listOf("they sent a probe into outer space", "probe", "probe in outer space")),
                explanation = "Black holes don't emit light, so the easiest way is to look for objects that orbit something that is not there",
                id = "Black_holes_identify_sagittarius"
        ),

        Question("What is emitted when a black hole swallows a star?",
                answer = listOf("radiation"),
                alternatives = listOf(listOf("space dust", "dust"),
                        listOf("hot gas", "gas"),
                        listOf("plasma")),
                explanation = "The massive gravitational and magnetic forces superheat the infalling gas and dust, causing it to emit radiation.",
                id = "Black_holes_emitted"

        ),

        Question("What is the 'event horizon'?",
                answer = listOf("the point at which escape becomes impossible", "the point of no return"),
                alternatives = listOf(listOf("the center of a black hole", "the center", "center", "center of a black hole"),
                        listOf("a ring of light around the black hole", "ring of light", "ring around a black hole"),
                        listOf("the radiation left behind from when the black hole formed", "radiation", "radiation from when the black hole formed")),
                explanation = "The event horizon is the point at which you cannot escape a black hole's gravitational pull.",
                id = "Black_holes_event_horizon"
        )
)

val questionsSpaceExploration = mutableListOf(

        Question("When did the first man walk on the moon?",
                answer = listOf("1969"),
                alternatives = listOf(listOf("1970"),
                        listOf("1980"),
                        listOf("1965")),
                explanation = "Apollo 11 (July 16–24, 1969) was the American spaceflight that first landed humans on the Moon.",
                id = "Space_exploration_Moon"
        )
)