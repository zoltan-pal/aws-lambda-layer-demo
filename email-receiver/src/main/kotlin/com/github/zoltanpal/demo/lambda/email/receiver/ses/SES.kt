package com.github.zoltanpal.demo.lambda.email.receiver.ses

data class SES(
        val receipt: SESReceipt,
        val mail: SESMail
)
