package com.yasuo.configuration

import io.micronaut.context.ApplicationContext
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.util.*
import java.util.function.Consumer

@MicronautTest
class TeamConfigurationTest{
    @Test
    fun testTeamConfiguration(){
        val names = Arrays.asList("Nirav Assar", "Lionel Messi")
        val items: MutableMap<String, Any> = HashMap()
        items["team.name"] = "evolution"
        items["team.color"] = "green"
        items["team.player-names"] = names

        items["team.team-admin.manager"] = "Jerry Jones"
        items["team.team-admin.coach"] = "Tommy O'Neill"
        items["team.team-admin.president"] = "Mark Scanell"


        val ctx = ApplicationContext.run(items) // Creates a configuration for this test,
        // it is the equivalent of having a custom yml
        val teamConfiguration = ctx.getBean(TeamConfiguration::class.java)
        val teamAdmin = teamConfiguration.teamAdmin.build()


        assertEquals("evolution", teamConfiguration.name)
        assertEquals("green", teamConfiguration.color)
        assertEquals(names.size, teamConfiguration.playerNames!!.size)
        names.forEach(Consumer { name: String? -> assertTrue(teamConfiguration.playerNames!!.contains(name!!)) })

        // check the builder has values set
        assertEquals("Jerry Jones", teamConfiguration.teamAdmin.manager)
        assertEquals("Tommy O'Neill", teamConfiguration.teamAdmin.coach)
        assertEquals("Mark Scanell", teamConfiguration.teamAdmin.president)

        // check the object can be built
        assertEquals("Jerry Jones", teamAdmin.manager)
        assertEquals("Tommy O'Neill", teamAdmin.coach)
        assertEquals("Mark Scanell", teamAdmin.president)

        ctx.close()
    }

}