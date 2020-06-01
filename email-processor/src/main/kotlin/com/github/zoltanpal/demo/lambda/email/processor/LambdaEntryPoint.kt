package com.github.zoltanpal.demo.lambda.email.processor

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import org.slf4j.LoggerFactory

class LambdaEntryPoint : RequestHandler<Any, Any> {

    companion object {
        @JvmStatic private val LOGGER = LoggerFactory.getLogger(LambdaEntryPoint::class.java)
    }

    override fun handleRequest(event: Any?, lambdaContext: Context?): Any? {
        LOGGER.info("Processing email...")
        return null
    }
}
