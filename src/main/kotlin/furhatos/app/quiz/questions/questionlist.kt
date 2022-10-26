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

        Question("What is the most famous object in Kuiper Belt?",
                answer = listOf("Dwarf Planet Pluto", "Pluto"),
                alternatives = listOf(listOf("Charon Moon",  "Charon"),
                        listOf("Neptune"),
                        listOf("Saturn")),
                explanation = "Just outside of Neptune's orbit is a ring of icy bodies. We call it the Kuiper Belt. This is where you'll find dwarf planet Pluto.",
                id = "Solar_system_Kuiper_Belt"
        ),

        Question("Name the giant spherical shell, that surrounds our solar system.",
                answer = listOf("Oort Cloud", "Cloud", "Oort"),
                alternatives = listOf(listOf("Kuiper Belt"),
                        listOf("Asteroid belt", "Asteroid"),
                        listOf("Milky way")),
                explanation = "It's called the Oort Cloud. Unlike the orbits of the planets and the Kuiper Belt, which are pretty flat like a disk," +
                        " the Oort Cloud is a spherical shell surrounding everything in our solar system. It's like a bubble with a thick shell.",
                id = "Solar_system_smallest"

        ),

        Question("One astronomical unit is … .",
                answer = listOf("150 million kilometers"),
                alternatives = listOf(listOf("15 million kilometers"),
                        listOf("50 million kilometers"),
                        listOf("15 billion kilometers")),
                explanation = "The astronomical unit is a unit of length, roughly the distance from Earth to the Sun and equal to 150 million kilometres.",
                id = "Solar_system_unit"

        ),
        Question("What is the name of the “bubble” created by the solar wind?",
                answer = listOf("heliosphere"),
                alternatives = listOf(listOf("atmosphere"),
                        listOf("stratosphere"),
                        listOf("lithosphere")),
                explanation = "As the solar wind flows from the sun, it creates a bubble in space known as the heliosphere around our solar system.",
                id = "Solar_system_solar_wind"
        ),

        Question("When did the first spacecraft cross the termination shock?",
                answer = listOf("2004"),
                alternatives = listOf(listOf("1977"),
                        listOf("2007"),
                        listOf("1969")),
                explanation = "The termination shock was traversed by Voyager 1 in 2004, and Voyager 2 in 2007. ",
                id = "Solar_system_termination_shock"
        ),

        Question("Which planets in the Solar system have no moons?",
                answer = listOf("Mercury and Venus"),
                alternatives = listOf(listOf("Mars and Jupiter"),
                        listOf("Jupiter and Saturn"),
                        listOf("Uranus and Neptune")),
                explanation = "Of the terrestrial (rocky) planets of the inner solar system, neither Mercury nor Venus have any moons at all, Earth has one and Mars has its two small moons.",
                id = "Solar_system_no_moons"
        ),
        Question("Which planets in the Solar system have the most moon counts?",
                answer = listOf("Jupiter and Saturn"),
                alternatives = listOf(listOf("Mercury and Venus"),
                        listOf("Uranus and Neptune"),
                        listOf("Earth and Mars")),
                explanation = "In our solar system, Saturn has the most moons, with around 63 being the latest confirmed count. Jupiter comes in second, with 57.",
                id = "Solar_system_moon_counts"
        ),

        Question("Which of these is not a terrestrial planet?",
                answer = listOf("Saturn"),
                alternatives = listOf(listOf("Mars"),
                        listOf("Venus"),
                        listOf("Mercury")),
                explanation = "The first four planets: Mercury, Venus, Earth, and Mars are terrestrial planets. They are all small with solid, rocky surfaces.",
                id = "Solar_system_terrestrial"
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

        Question("What is the first artificial satellite to orbit Earth?",
                answer = listOf("Sputnik 1"),
                alternatives = listOf(listOf("Vostok 1"),
                        listOf("Soyuz 1"),
                        listOf("Kosmos 1")),
                explanation = "On October 4, 1957, the USSR launched Sputnik, the first artificial satellite to orbit Earth, a metal sphere the size of a basketball.",
                id = "Space_exploration_satellite"
        ),

        Question("What was the first creature, carried by satellite?",
                answer = listOf("A dog", "dog"),
                alternatives = listOf(listOf("A cat", "cat"),
                        listOf("A rat", "rat"),
                        listOf("A rabbit", "rabbit")),
                explanation = "Hurriedly prepared to take advantage of the propaganda value of the first satellite, Sputnik 2 utilized an animal habitat and carried the dog Laika, the first animal to orbit the Earth.",
                id = "Space_exploration_creature"
        ),

        Question("Who was the first cosmonaut in space?",
                answer = listOf("Yuri Gagarin"),
                alternatives = listOf(listOf("Neil Armstrong"),
                        listOf("Sergei Korolev"),
                        listOf("Michael Collins")),
                explanation = "Yuri Gagarin was a Soviet pilot and cosmonaut who became the first human to journey into outer space. Travelling in the Vostok 1 capsule, Gagarin completed one orbit of Earth on 12 April 1961.",
                id = "Space_exploration_first_in_space"
        ),

        Question("Which nation developed the first human-made object to hit the Moon?",
                answer = listOf("The Soviet Union", "U.S.S.R."),
                alternatives = listOf(listOf("The United States of America", "The US", "The United States", "US", "United States", "America"),
                        listOf("China", "The Republic of China", "People's Republic of China"),
                        listOf("Germany")),
                explanation = "Launched on 2 January 1959, the Soviet Union probe Luna 1 travelled to within almost 4000 miles of the Moon's surface in 34 hours.",
                id = "Space_exploration_hit_moon"
        ),

        Question("Which planets were not observed during Mariner program?",
                answer = listOf("Venus"),
                alternatives = listOf(listOf("Mars"),
                        listOf("Mercury"),
                        listOf("Earth")),
                explanation = "Between 1962 and 1973, NASA's Jet Propulsion Laboratory designed and built 10 spacecraft named Mariner to explore the inner solar system," +
                        " visiting the planets Venus, Mars and Mercury for the first time, and returning to Venus and Mars for additional close observations.",
                id = "Space_exploration_mariner"
        ),

        Question("What is considered to be hidden under surface ice of Jupiter’s moon, called Europa?",
                answer = listOf("An ocean", "ocean"),
                alternatives = listOf(listOf("A lake", "lake"),
                        listOf("Living organism", "Organisms"),
                        listOf("Poisonous gas", "Gas")),
                explanation = "Scientists are almost certain that hidden beneath the icy surface of Europa is a saltwater ocean thought to contain about twice as much water as Earth's global ocean.",
                id = "Space_exploration_Europa"
        )
)