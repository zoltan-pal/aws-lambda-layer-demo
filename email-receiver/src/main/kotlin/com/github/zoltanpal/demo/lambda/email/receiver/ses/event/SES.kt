package com.github.zoltanpal.demo.lambda.email.receiver.ses.event

data class SES(
        val receipt: SESReceipt,
        val mail: SESMail
)