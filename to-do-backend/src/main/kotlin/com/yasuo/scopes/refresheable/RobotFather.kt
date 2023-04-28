package com.yasuo.scopes.refresheable

import jakarta.inject.Singleton
import org.jetbrains.annotations.NotNull

@Singleton
class RobotFather (private val robot: RefreshableRobot) {
    @NotNull
    fun child():RefreshableRobot{
        return robot
    }
}