package com.github.zoltanpal.demo.lambda.email.confirmation.sender

class ConfirmationEmailConstants {

    companion object {

        const val SUBJECT = "Confirmation"
        const val HTML_BODY = ("<p>Hi, </p><p>Thank you for contacting us, your inquiry will be processed shortly.</p>")
        const val TEXT_BODY = ("Hi, Thank you for contacting us, your inquiry is being processed.")
    }
}
