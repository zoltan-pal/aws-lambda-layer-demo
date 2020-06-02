package com.github.zoltanpal.demo.lambda.email.filter.ses.event

import com.fasterxml.jackson.annotation.JsonProperty

data class SESEvent(
        @JsonProperty("Records") val records: List<SESRecord>
)
