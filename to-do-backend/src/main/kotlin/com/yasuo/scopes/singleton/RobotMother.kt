package com.yasuo.scopes.singleton

import jakarta.inject.Singleton
import org.jetbrains.annotations.NotNull

@Singleton
class RobotMother (private val robot: Robot) {
    @NotNull
    fun child():Robot{
        return robot
    }
}