package com.yasuo.entities

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.GeneratedValue.Type.AUTO
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity

@MappedEntity
class ToDo(var title:String, val authorId: Long) {
    @Id
    @GeneratedValue(AUTO)
    var id: Long?=null

    var completed = false
}