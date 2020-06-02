package com.github.zoltanpal.demo.lambda.email.filter.ses.event

data class SESRecord(
        val ses: SES,
        val eventVersion: String,
        val eventSource: String
)
