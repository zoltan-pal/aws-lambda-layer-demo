package com.github.zoltanpal.demo.lambda.email.confirmation.sender

import com.amazonaws.regions.Regions
import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.amazonaws.services.lambda.runtime.events.SNSEvent
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder
import com.amazonaws.services.simpleemail.model.*
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.slf4j.LoggerFactory

/**
 * The entry point for the confirmation email sender lambda function.
 * Sends confirmation email to the original sender.
 */
class LambdaEntryPoint : RequestHandler<SNSEvent, Void> {

    companion object {
        @JvmStatic private val LOGGER = LoggerFactory.getLogger(LambdaEntryPoint::class.java)
        private const val UTF_8_CHARSET = "UTF-8"
    }

    private val objectMapper: ObjectMapper
    private val sesClient: AmazonSimpleEmailService
    private val confirmationSender: String

    init {
        val awsRegionName = System.getenv("AWS_REGION")
        val awsRegion = Regions.fromName(awsRegionName)
        confirmationSender = System.getenv("CONFIRMATION_SENDER")
        objectMapper = jacksonObjectMapper()
        sesClient = AmazonSimpleEmailServiceClientBuilder.standard()
                .withRegion(awsRegion)
                .build()
    }

    override fun handleRequest(snsEvent: SNSEvent, lambdaContext: Context): Void? {
        LOGGER.info("Sending email confirmation...")
        val rawSnsMessage = snsEvent.records[0].sns.message
        val confirmationEmailRecipient = objectMapper.readTree(rawSnsMessage).path("mail").path("source").asText()
        val emailMessageId = objectMapper.readTree(rawSnsMessage).path("mail").path("messageId").asText()
        LOGGER.debug("Extracted sender={} for email with messageId={}", confirmationEmailRecipient, emailMessageId)

        try {
            val htmlMessageContent = Content().withCharset(UTF_8_CHARSET).withData(ConfirmationEmailConstants.HTML_BODY)
            val rawMessageContent = Content().withCharset(UTF_8_CHARSET).withData(ConfirmationEmailConstants.TEXT_BODY)
            val subjectContent = Content().withCharset(UTF_8_CHARSET).withData(ConfirmationEmailConstants.SUBJECT)
            val request: SendEmailRequest = SendEmailRequest()
                    .withDestination(Destination().withToAddresses(confirmationEmailRecipient))
                    .withMessage(Message()
                            .withBody(Body()
                                    .withHtml(htmlMessageContent)
                                    .withText(rawMessageContent))
                            .withSubject(subjectContent))
                    .withSource(confirmationSender)
            sesClient.sendEmail(request)
            LOGGER.info("Confirmation sent to={} for email with messageId={}", confirmationEmailRecipient, emailMessageId)
        } catch (e: Exception) {
            LOGGER.error("Failed to send confirmation for email with messageId={}", emailMessageId, e)
        }
        return null
    }
}
