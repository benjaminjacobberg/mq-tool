package com.github.benjaminjacobberg.ibmmqtool

import org.springframework.stereotype.Service

@Service
class MessageService(private val messageProducer: MessageProducer) {
    fun submit(submission: Submission) = messageProducer.put(submission)
}