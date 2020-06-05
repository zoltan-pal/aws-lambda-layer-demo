package com.github.zoltanpal.demo.lambda.email.processor

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.amazonaws.services.lambda.runtime.events.SNSEvent
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.slf4j.LoggerFactory

/**
 * The entry point for the email processor lambda function.
 * Does not do any processing, it is just here for the sake of
 * demonstrating multiple functions being triggered from SNS
 * and how lambda layers are shared across multiple functions.
 */
class LambdaEntryPoint : RequestHandler<SNSEvent, Void> {

    companion object {
        @JvmStatic private val LOGGER = LoggerFactory.getLogger(LambdaEntryPoint::class.java)
    }

    private val objectMapper = jacksonObjectMapper()

    override fun handleRequest(snsEvent: SNSEvent, lambdaContext: Context): Void? {
        LOGGER.info("Processing email...")
        val rawSnsMessage = snsEvent.records[0].sns.message
        val emailMessageId = objectMapper.readTree(rawSnsMessage).path("mail").path("messageId").asText()
        LOGGER.debug("Pretending to do some processing here...")
        LOGGER.debug("Raw message={}", rawSnsMessage)
        LOGGER.info("Email processing done for email with messageId={}", emailMessageId)
        return null
    }
}
