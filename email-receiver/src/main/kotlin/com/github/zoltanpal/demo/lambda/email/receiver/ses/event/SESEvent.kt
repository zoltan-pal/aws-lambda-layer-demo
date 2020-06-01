package com.github.zoltanpal.demo.lambda.email.receiver.ses.event

import com.fasterxml.jackson.annotation.JsonProperty

data class SESEvent(
        @JsonProperty("Records") val records: List<SESRecord>
)
