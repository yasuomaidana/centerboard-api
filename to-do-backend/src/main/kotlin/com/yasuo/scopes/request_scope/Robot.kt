package com.yasuo.scopes.request_scope

import io.micronaut.http.HttpRequest
import io.micronaut.runtime.http.scope.RequestAware
import io.micronaut.runtime.http.scope.RequestScope

@RequestScope
class Robot:RequestAware{
    lateinit var serialNumber:String
    override fun setRequest(request: HttpRequest<*>?) {
        serialNumber = request!!.headers.get("UUID")!!.toString()
    }

}
