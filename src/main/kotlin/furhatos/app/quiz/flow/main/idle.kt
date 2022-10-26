package furhatos.app.quiz.flow.main

import furhatos.app.quiz.*
import furhatos.app.quiz.flow.Parent
import furhatos.app.quiz.nlu.*
import furhatos.app.quiz.questions.*
import furhatos.app.quiz.setting.interested
import furhatos.app.quiz.setting.playing
import furhatos.app.quiz.setting.quiz
import furhatos.flow.kotlin.*
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes
import furhatos.records.User
import java.util.*

val Idle: State = state {
    onEntry {
        /*
            Loop through all (potentially) interested users.
            Goto calls are used since users may enter and leave
            while we are querying other users and we want to
            ask all users before moving on. I.e we want to update the
            users.interested() list of users.
          */
        users.interested().forEach {
            furhat.attend(it)
            goto(Start(it))
        }
        // Once no more user, start the game with all interested users
        if (users.playing().isNotEmpty()) {
            furhat.attendAll()
            goto(NewGame)
        }

    }

    onUserEnter(instant = true) {
        /* Say hi and query a new user if it's the only interested user.
            Other interested users would already be greeted at this point.
            If not, we glance at the user and keep doing whatever we were doing.
         */
        if (users.interested().count() == 1) {
            furhat.attend(it.id)
            furhat.say("Hello there")
            goto(Start(it))
//            goto(QueryPerson(it))
        } else {
            furhat.glance(it.id, async=true)
        }
    }

    onUserLeave(instant = true) {
        if (users.count > 0) {
            furhat.attend(users.other)
        } else {
            furhat.attendNobody()
        }
    }

    onResponse{
        reentry()
    }

    onNoResponse {
        reentry()
    }
}

// Variables

val maxRounds = 5
var rounds = 0
var playing = false

fun QueryPerson(user: User) = state(parent = Parent) {
    onEntry {
        val userName = user.quiz.userName

        val a = mem.getPersonMemory(userName)

        if (a.isNullOrEmpty()){
            furhat.ask("What would you like to know about space? My favourite topic is the solar system, but we can talk about black holes or space exploration if you want.")
        }
        else{
            val topic = mem.getPersonTopic(userName)

            user.quiz.playing = true

            if (topic == BLACK_HOLES){
                questions = questionsBlackHoles
            }
            else if (topic == SOLAR_SYSTEM){
                questions = questionsSolarSystem
            }
            else if (topic == SPACE_EXPLORATION){
                questions = questionsSpaceExploration
            }

            val score = mem.getPersonScore(userName)

            val grade = 10 * score / maxRounds

            if(grade < 5)

                furhat.say("Oh, I remember that you struggled with the topic of $topic last time. Let's try again.")

            else if(grade < 8)

                furhat.say("Oh, I remember that you enjoyed the topic of $topic last time. Let's see if you can get a perfect score this time.")

            else if(grade <= 10)

                furhat.say("Oh, I remember that you did really well with the topic of $topic last time. Let's see if you can get a perfect score this time too.")

            furhat.say("Alright, here we go!")

            QuestionSet.next()
            furhat.attend(users.playing().first())
            goto(AskQuestion)
        }

    }

    onResponse<Yes> {
        user.quiz.playing = true
        furhat.say("great!")
        goto(Idle)
    }

    onResponse<No> {
        user.quiz.interested = false
        furhat.say("oh well")
        goto(Idle)
    }

    onResponse<SelectTopic> {
        val topic = parseTopic(it.intent.topic)
        user.quiz.playing = true
        if(topic == null){
            furhat.say("I can't teach you that at the moment, but I'll let you know when I'll learn it myself.")
            reentry()
        }else {
            user.quiz.selectedTopic = topic
            goto(Lesson(topic))
        }
    }

    onNoResponse {
        furhat.say("Sorry I didn't get that")
        reentry()
    }

}


fun Lesson(topic: String) = state(parent = Parent) {
    onEntry {

        if (topic == BLACK_HOLES){
            questions = questionsBlackHoles
            furhat.say("You want to know about black holes")
            furhat.say("Alright, here we go!")

            furhat.say("Black holes are points in space that are so dense they create deep gravity sinks. Beyond a certain region, not even light can escape the powerful tug of a black hole's gravity. And anything that ventures too close—be it star, planet, or spacecraft—will be stretched and compressed like putty in a theoretical process aptly known as spaghettification.")

            furhat.say("There are four types of black holes: stellar, intermediate, supermassive, and miniature. The most commonly known way a black hole forms is by stellar death. As stars reach the ends of their lives, most will inflate, lose mass, and then cool to form white dwarfs. But the largest of these fiery bodies, those at least 10 to 20 times as massive as our own sun, are destined to become either super-dense neutron stars or so-called stellar-mass black holes.")

            furhat.say("In their final stages, enormous stars go out with a bang in massive explosions known as supernovae. Such a burst flings star matter out into space but leaves behind the stellar core. While the star was alive, nuclear fusion created a constant outward push that balanced the inward pull of gravity from the star's own mass. In the stellar remnants of a supernova, however, there are no longer forces to oppose that gravity, so the star core begins to collapse in on itself.")

            furhat.say("If its mass collapses into an infinitely small point, a black hole is born. Packing all of that bulk—many times the mass of our own sun—into such a tiny point gives black holes their powerful gravitational pull. Thousands of these stellar-mass black holes may lurk within our own Milky Way galaxy.")

            furhat.say("Supermassive black holes, predicted by Einstein's general theory of relativity, can have masses equal to billions of suns; these cosmic monsters likely hide at the centers of most galaxies. The Milky Way hosts its own supermassive black hole at its center known as Sagittarius A* (pronounced “ay star”) that is more than four million times as massive as our sun.")

            furhat.say("The tiniest members of the black hole family are, so far, theoretical. These small vortices of darkness may have swirled to life soon after the universe formed with the big bang, some 13.7 billion years ago, and then quickly evaporated.")

            furhat.say("No matter their starting size, black holes can grow throughout their lives, slurping gas and dust from any objects that creep too close. Anything that passes the event horizon, the point at which escape becomes impossible, is in theory destined for spaghettification thanks to a sharp increase in the strength of gravity as you fall into the black hole.")

            furhat.say("Because black holes swallow all light, astronomers can't spot them directly like they do the many glittery cosmic objects in the sky. But there are a few keys that reveal a black hole's presence.")

            furhat.say("For one, a black hole's intense gravity tugs on any surrounding objects. Astronomers use these erratic movements to infer the presence of the invisible monster that lurks nearby. Or objects can orbit a black hole, and astronomers can look for stars that seem to orbit nothing to detect a likely candidate. That's how astronomers eventually identified Sagittarius A* as a black hole in the early 2000s.")

            furhat.say("Black holes are also messy eaters, which often betrays their locations. As they sip on surrounding stars, their massive gravitational and magnetic forces superheat the infalling gas and dust, causing it to emit radiation.")

        }
        else if (topic == SOLAR_SYSTEM){
            questions = questionsSolarSystem
            furhat.say("You want to know about the solar system")
            furhat.say("Alright, here we go!")

            furhat.say("Our solar system extends much farther than the eight planets that orbit the Sun. The solar system also includes the Kuiper Belt that lies past Neptune's orbit. This is a sparsely occupied ring of icy bodies, almost all smaller than the most popular Kuiper Belt Object – dwarf planet Pluto.")

            furhat.say("Beyond the fringes of the Kuiper Belt is the Oort Cloud. This giant spherical shell surrounds our solar system. It has never been directly observed, but its existence is predicted based on mathematical models and observations of comets that likely originate there.")

            furhat.say("The Oort Cloud is made of icy pieces of space debris - some bigger than mountains – orbiting our Sun as far as 1.6 light-years away. This shell of material is thick, extending from 5,000 astronomical units to 100,000 astronomical units. One astronomical unit (or AU) is the distance from the Sun to Earth, or about 93 million miles (150 million kilometers). The Oort Cloud is the boundary of the Sun's gravitational influence, where orbiting objects can turn around and return closer to our Sun.")

            furhat.say("The Sun's heliosphere doesn't extend quite as far. The heliosphere is the bubble created by the solar wind – a stream of electrically charged gas blowing outward from the Sun in all directions. The boundary where the solar wind is abruptly slowed by pressure from interstellar gases is called the termination shock. ")

            furhat.say("Two NASA spacecraft launched in 1977 have crossed the termination shock: Voyager 1 in 2004 and Voyager 2 in 2007. Voyager 1 went interstellar in 2012 and Voyager 2 joined it in 2018. But it will be many thousands of years before the two Voyagers exit the Oort Cloud.")

            furhat.say("There are more than 200 known moons in our solar system and several more awaiting confirmation of a discovery. Of the eight planets, Mercury and Venus are the only ones with no moons. The giant planets Jupiter and Saturn lead our solar system’s moon counts. In some ways, the swarms of moons around these worlds resemble mini versions of our solar system.")

            furhat.say("The order and arrangement of the planets and other bodies in our solar system are due to the way the solar system formed. Nearest to the Sun, only rocky material could withstand the heat when the solar system was young. For this reason, the first four planets – Mercury, Venus, Earth, and Mars – are terrestrial planets. They are all small with solid, rocky surfaces.")

            furhat.say("Meanwhile, materials we are used to seeing as ice, liquid, or gas settled in the outer regions of the young solar system. Gravity pulled these materials together, and that is where we find gas giants Jupiter and Saturn, and the ice giants Uranus and Neptune.")
        }
        else if (topic == SPACE_EXPLORATION){
            questions = questionsSpaceExploration
            furhat.say("You want to know about space exploration")
            furhat.say("Alright, here we go!")

            furhat.say("We human beings have been venturing into space since October 4, 1957, when the Union of Soviet Socialist Republics (U.S.S.R.) launched Sputnik, the first artificial satellite to orbit Earth. This happened during the period of political hostility between the Soviet Union and the United States known as the Cold War. For several years, the two superpowers had been competing to develop missiles, called intercontinental ballistic missiles (ICBMs), to carry nuclear weapons between continents. In the U.S.S.R., the rocket designer Sergei Korolev had developed the first ICBM, a rocket called the R7, which would begin the space race.")

            furhat.say("This competition came to a head with the launch of Sputnik. Carried atop an R7 rocket, the Sputnik satellite was able to send out beeps from a radio transmitter. After reaching space, Sputnik orbited Earth once every 96 minutes.")

            furhat.say("Then, a month later, on November 3, 1957, the Soviets achieved an even more impressive space venture. This was SputnikII, a satellite that carried a living creature, a dog named Laika.")

            furhat.say("Prior to the launch of Sputnik, the United States had been working on its own capability to launch a satellite. The United States made two failed attempts to launch a satellite into space before succeeding with a rocket that carried a satellite called Explorer on January 31, 1958. ")

            furhat.say("The first human in space was the Soviet cosmonaut Yuri Gagarin, who made one orbit around Earth on April 12, 1961, on a flight that lasted 108 minutes. A little more than three weeks later, NASA launched astronaut Alan Shepard into space, not on an orbital flight, but on a suborbital trajectory—a flight that goes into space but does not go all the way around Earth. Shepard’s suborbital flight lasted just over 15 minutes.")

            furhat.say("In addition to launching the first artificial satellite, the first dog in space, and the first human in space, the Soviet Union achieved other space milestones ahead of the United States. These milestones included Luna 2, which became the first human-made object to hit the Moon in 1959. ")

            furhat.say("Less than four months after Gagarin’s flight in 1961, a second Soviet human mission orbited a cosmonaut around Earth for a full day. The U.S.S.R. also achieved the first spacewalk and launched the Vostok 6 mission, which made Valentina Tereshkova the first woman to travel to space.")

            furhat.say("In 1969, on Apollo 11, the United States sent the first astronauts to the Moon, and Neil Armstrong became the first human to set foot on its surface. During the landed missions, astronauts collected samples of rocks and lunar dust that scientists still study to learn about the moon. During the 1960s and 1970s, NASA also launched a series of space probes called Mariner, which studied Venus, Mars, and Mercury.\n")

            furhat.say("Space stations marked the next phase of space exploration. The first space station in Earth orbit was the Soviet Salyut 1 station, which was launched in 1971. This was followed by NASA’s Skylab space station, the first orbital laboratory in which astronauts and scientists studied Earth and the effects of spaceflight on the human body. ")

            furhat.say("Since the Apollo lunar program ended in 1972, human space exploration has been limited to low-Earth orbit, where many countries participate and conduct research on the International Space Station. However, unpiloted probes have traveled throughout our solar system. In recent years, probes have made a range of discoveries, including that a moon of Jupiter, called Europa, and a moon of Saturn, called Enceladus, have oceans under their surface ice that scientists think may harbor life. ")
        }

        QuestionSet.next()
        furhat.attend(users.playing().first())
        goto(AskQuestion)
    }
}