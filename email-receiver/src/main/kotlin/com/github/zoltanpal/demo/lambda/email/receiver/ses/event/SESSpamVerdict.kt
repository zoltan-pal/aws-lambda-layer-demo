package com.github.zoltanpal.demo.lambda.email.receiver.ses.event

data class SESSpamVerdict(
        val status: VerdictStatus
)
