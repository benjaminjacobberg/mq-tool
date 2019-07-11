package com.github.benjaminjacobberg.ibmmqtool

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MessageController(private val messageService: MessageService) {
    @PostMapping(value = ["/message/submit"], consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
    fun submitMessage(submission: Submission) = messageService.submit(submission)
}