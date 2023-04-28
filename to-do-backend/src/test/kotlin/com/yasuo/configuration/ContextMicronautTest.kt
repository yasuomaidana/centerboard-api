package com.yasuo.configuration

import io.micronaut.context.ApplicationContext
import io.micronaut.context.exceptions.BeanInstantiationException
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
import java.util.*

class ContextMicronautTest{
    @Test
    fun lifeCycleOfClassesAnnotatedWithAtContextIsBoundToThatOfTheBeanContext() {
        val e =
            Executable {
                ApplicationContext.run(
                    Collections.singletonMap<String, Any>(
                        "context_micronaut.language",
                        "scala"
                    )
                )
            }
        val thrown: BeanInstantiationException = assertThrows(BeanInstantiationException::class.java, e)
        assertTrue(thrown.message!!.contains("language - must match \"groovy|java|kotlin\""))
    }
}