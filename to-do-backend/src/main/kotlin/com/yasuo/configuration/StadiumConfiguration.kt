package com.yasuo.configuration

import io.micronaut.context.annotation.EachProperty
import io.micronaut.context.annotation.Parameter

@EachProperty("stadium")
class StadiumConfiguration constructor(@param:Parameter val name: String) {
    var city: String? = null
    var size: Int? = null
}