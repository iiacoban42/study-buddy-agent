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

        Question("Albert Einstein came up with the term 'black hole'.",
                answer = listOf("false", "that's false", "wrong", "that's wrong"),
                alternatives = listOf(listOf("true", "that's true", "right", "that's right")),
                explanation = "The term of black holes was coined by Robert H. Dicke in the early 1960s",
                id = "Black_holes_Einstein"

        ),

        Question("How is a black hole formed?",
                answer = listOf("when a star explodes at the end of its life cycle", "when a star dies", "when a star explodes"),
                alternatives = listOf(listOf("when the fabric of the universe tears", "when the fabric tears", "when the universe tears"),
                        listOf("when a small planet explodes", "when a planet explodes", "a planet explodes"),
                        listOf("they just appear", "just appear")),
                explanation = "Stellar black holes form when the center of a very massive star collapses in upon itself. This collapse also causes a supernova, or an exploding star, that blasts part of the star into space.",
                id = "Black_holes_formed"
        ),

        Question("Black holes cannot be seen because they don't reflect light.",
                answer = listOf("true", "that's true", "right", "that's right"),
                alternatives = listOf(listOf("false", "that's false", "wrong", "that's wrong")),
                explanation = "Light cannot escape a black hole, its extreme gravity warps space around it, which allows light to \"echo,\" bending around the back of the object.",
                id = "Black_holes_light"
        ),

        Question("What surrounds a black hole?",
                answer = listOf("space dust, stars and galaxies", "space dust", "stars", "galaxies"),
                alternatives = listOf(listOf("a ring of bright light", "ring of light", "a ring of light", "light"),
                        listOf("No one knows", "nobody knows", "we don't know"),
                        listOf("tiny planets", "planets")),
                explanation = "A black hole's gravity can sometimes pull off the outer gases of other stars and galaxies and grow a disk around itself.",
                id = "Black_holes_suroundings"
        ),
        Question("What pulls objects into a black hole?",
                answer = listOf("a strong gravitational force", "the gravitational force", "gravitational force", "gravity"),
                alternatives = listOf(listOf("a tractor beam", "a beam", "beam"),
                        listOf("objects just drift into it", "objects drifting into it", "object going towards it", "objects drifting towards it"),
                        listOf("it doesn't", "it actually doesn't", "that doesn't happen")),
                explanation = "The strong gravitational force pulls so much that even light can not get out. The gravity is so strong because matter has been squeezed into a tiny space.",
                id = "Black_holes_pull"
        ),

        Question("What is the area surrounding a black hole called?",
                answer = listOf("event horizon"),
                alternatives = listOf(listOf("light ring"),
                        listOf("perimeter"),
                        listOf("circumference")),
                explanation = "A black hole's “surface”, called its event horizon, defines the boundary where the velocity needed to escape exceeds the speed of light, which is the speed limit of the cosmos.",
                id = "Black_holes_event"
        ),

        Question("What are the three sizes of black holes known as?",
                answer = listOf("gargantuan, stellar and primordial"),
                alternatives = listOf(listOf("small, huge, massive"),
                        listOf("tiny, big, gigantic"),
                        listOf("large, huge, massive")),
                explanation = "The main three sizes of black holes are gargantuan, stellar and primordial.",
                id = "Black_holes_size"

        ),

        Question("How big can a black hole be?",
                answer = listOf("the size of several million suns", "million suns", "a few million suns", "millions of suns"),
                alternatives = listOf(listOf("the size of London", "size of London", "London", "about the size of London"),
                        listOf("the size of Mars", "size of Mars", "Mars", "about the size of Mars"),
                        listOf("the size of a football", "size of football", "football", "about the size of a football")),
                explanation = "There is no theoretical upper limit to the mass of a black hole. However, astronomers have noted that the ultra-massive black holes never seem to exceed about 10 billion solar masses.",
                id = "Black_holes_big"
        ),

        Question("What is the centre of a black hole called?",
                answer = listOf("Singularity"),
                alternatives = listOf(listOf("Sauron"),
                        listOf("Bullseye"),
                        listOf("Centre point")),
                explanation = "The singularity constitutes the center of a black hole, hidden by the object's “surface,” the event horizon.",
                id = "Black_holes_centre"
        ),

        Question("According to scientists, how many black holes exist?",
                answer = listOf("Billions"),
                alternatives = listOf(listOf("Millions"),
                        listOf("Thousands"),
                        listOf("Hundreds")),
                explanation = "Scientists estimate that there are as many as ten million to a billion such black holes in the Milky Way alone.",
                id = "Black_holes_number"
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