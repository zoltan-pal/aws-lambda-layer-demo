package com.github.zoltanpal.demo.lambda.email.filter.ses.event

data class SES(
        val receipt: SESReceipt,
        val mail: SESMail
)
