package com.github.zoltanpal.demo.lambda.email.receiver.ses

data class SESRecord(
        val ses: SES,
        val eventVersion: String,
        val eventSource: String
)
