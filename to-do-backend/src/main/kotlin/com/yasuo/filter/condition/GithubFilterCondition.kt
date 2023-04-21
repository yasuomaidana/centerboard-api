package com.yasuo.filter.condition

import com.yasuo.configuration.GithubConfiguration
import io.micronaut.context.condition.Condition
import io.micronaut.context.condition.ConditionContext

class GithubFilterCondition: Condition {
    override fun matches(context: ConditionContext<*>?): Boolean {
        if(context != null && context.beanContext != null){
            val githubConfiguration = context.beanContext.getBean(GithubConfiguration::class.java)
            if(githubConfiguration.token != null && githubConfiguration.username != null){
                return true
            }
        }
        return false
    }
}