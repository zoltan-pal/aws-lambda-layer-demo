package com.github.zoltanpal.demo.lambda.email.receiver.ses

data class SESAction(
        val type: String?,
        val topicArn: String?,
        val bucketName: String?,
        val objectKey: String?,
        val smtpReplyCode: String?,
        val statusCode: String?,
        val message: String?,
        val sender: String?,
        val functionArn: String?,
        val invocationType: String?,
        val organizationArn: String?
)
