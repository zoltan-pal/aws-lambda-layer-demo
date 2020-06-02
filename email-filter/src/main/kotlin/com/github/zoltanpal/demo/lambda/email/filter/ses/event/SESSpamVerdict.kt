package com.github.zoltanpal.demo.lambda.email.filter.ses.event

data class SESSpamVerdict(
        val status: VerdictStatus
)
