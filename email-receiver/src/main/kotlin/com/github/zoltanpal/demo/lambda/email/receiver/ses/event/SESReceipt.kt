package com.github.zoltanpal.demo.lambda.email.receiver.ses.event

data class SESReceipt(
        val action: SESAction?,
        val dkimVerdict: SESDKIMVerdict?,
        val dmarcPolicy: String?,
        val dmarcVerdict: SESDMARCVerdict?,
        val processingTimeMillis: String?,
        val recipients: List<String?>?,
        val spamVerdict: SESSpamVerdict?,
        val spfVerdict: SESSPFVerdict?,
        val timestamp: String?,
        val virusVerdict: SESVirusVerdict?
)
