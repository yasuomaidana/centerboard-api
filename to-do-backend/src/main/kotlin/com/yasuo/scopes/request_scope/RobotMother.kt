package com.yasuo.scopes.request_scope

import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.jetbrains.annotations.NotNull

@Singleton
class RobotMother {
    @Inject
    private lateinit var robot: Robot
    @NotNull
    fun child():Robot{
        return robot
    }
}