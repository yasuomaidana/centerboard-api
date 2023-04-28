package com.yasuo.scopes.refresheable

import io.micronaut.runtime.context.scope.Refreshable
import java.util.*

@Refreshable
class RefreshableRobot {
    var serialNumber:String = UUID.randomUUID().toString()
    fun refresh(){
        serialNumber = UUID.randomUUID().toString()
    }
}
