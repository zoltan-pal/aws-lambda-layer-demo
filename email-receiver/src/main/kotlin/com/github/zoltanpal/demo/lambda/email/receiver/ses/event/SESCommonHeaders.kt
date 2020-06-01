package com.github.zoltanpal.demo.lambda.email.receiver.ses.event

data class SESCommonHeaders(
        val messageId: String?,
        val date: String?,
        val to: List<String>?,
        val cc: List<String>?,
        val bcc: List<String>?,
        val from: List<String>?,
        val sender: String?,
        val returnPath: String?,
        val replyTo: String?,
        val subject: String?
)
