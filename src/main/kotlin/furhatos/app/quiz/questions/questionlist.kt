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