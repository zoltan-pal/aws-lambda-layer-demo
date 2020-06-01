package com.github.zoltanpal.demo.lambda.email.receiver.ses.event

data class SESMail(
        val destination: List<String?>?,
        val messageId: String?,
        val source: String?,
        val timestamp: String?,
        val headers: List<SESMailHeader?>?,
        val commonHeaders: SESCommonHeaders?,
        val headersTruncated: String?
)
