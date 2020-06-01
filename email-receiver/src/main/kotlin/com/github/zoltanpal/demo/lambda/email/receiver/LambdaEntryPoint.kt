package com.github.zoltanpal.demo.lambda.email.receiver

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestStreamHandler
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.github.zoltanpal.demo.lambda.email.receiver.ses.EmailDisposition
import com.github.zoltanpal.demo.lambda.email.receiver.ses.event.SESEvent
import com.github.zoltanpal.demo.lambda.email.receiver.ses.event.SESRecord
import com.github.zoltanpal.demo.lambda.email.receiver.ses.event.VerdictStatus
import org.slf4j.LoggerFactory
import java.io.BufferedReader
import java.io.InputStream
import java.io.OutputStream

/**
 * The entry point for the email receiver lambda function.
 * Filters emails, drops on spam-check failure or missing subject.
 * Uses low-level [RequestStreamHandler] to handle custom deserialization.
 */
class LambdaEntryPoint : RequestStreamHandler {

    companion object {
        @JvmStatic private val LOGGER = LoggerFactory.getLogger(LambdaEntryPoint::class.java)
    }

    override fun handleRequest(inputStream: InputStream, outputStream: OutputStream, lambdaContext: Context) {
        LOGGER.info("Handling incoming email...")
        val mapper = jacksonObjectMapper()
        val jsonString = inputStream.bufferedReader().use(BufferedReader::readText)
        LOGGER.debug("Incoming JSON={}", jsonString)
        val event = mapper.readValue(jsonString, SESEvent::class.java)
        LOGGER.debug("Parsed SES event={}", event)
        val filterResult = filterEmail(event.records[0])
        LOGGER.info("Email filter result for disposition={}", filterResult)
        outputStream.write(filterResult.toString().toByteArray(Charsets.UTF_8))
    }

    private fun filterEmail(sesRecord: SESRecord): EmailDisposition {
        val receipt = sesRecord.ses.receipt
        val subject = sesRecord.ses.mail.commonHeaders?.subject
        return if (receipt.dkimVerdict?.status == VerdictStatus.FAIL
                || receipt.spamVerdict?.status == VerdictStatus.FAIL
                || receipt.virusVerdict?.status == VerdictStatus.FAIL
                || receipt.spfVerdict?.status == VerdictStatus.FAIL
                || subject.isNullOrBlank()) {
            LOGGER.info("Dropping message with id={}", sesRecord.ses.mail.messageId)
            EmailDisposition.STOP_RULE_SET
        } else {
            EmailDisposition.CONTINUE
        }
    }
}
