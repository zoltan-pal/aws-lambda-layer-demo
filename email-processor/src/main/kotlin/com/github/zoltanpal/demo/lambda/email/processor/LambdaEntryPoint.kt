package com.github.zoltanpal.demo.lambda.email.processor

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.amazonaws.services.lambda.runtime.events.SNSEvent
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.slf4j.LoggerFactory

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
