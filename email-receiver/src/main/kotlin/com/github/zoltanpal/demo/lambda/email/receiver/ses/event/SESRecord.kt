package com.github.zoltanpal.demo.lambda.email.receiver.ses.event

import com.github.zoltanpal.demo.lambda.email.receiver.ses.event.SES

data class SESRecord(
        val ses: SES,
        val eventVersion: String,
        val eventSource: String
)
