package com.github.benjaminjacobberg.mqtool

import org.springframework.web.bind.annotation.*

@RestController
class MessageController(private val messageService: MessageService) {

    @PostMapping(value = ["/message"])
    @CrossOrigin
    fun sendMessage(@RequestBody messageRequest: MessageRequest) {
        val message = Message(null, messageRequest.body)
        val connectionInformation = ConnectionInformation(host = messageRequest.host, port = messageRequest.port, channel = messageRequest.channel, qm = messageRequest.qm, userId = messageRequest.userId, password = messageRequest.password, queue = messageRequest.queue, implementation = messageRequest.implementation)
        messageService.submit(message, connectionInformation)
    }

    @GetMapping(value = ["/message/{jmsId}"])
    @CrossOrigin
    fun pullMessage(@PathVariable(name = "jmsId") jmsId: String,
                    @RequestParam(name = "host") host: String,
                    @RequestParam(name = "port") port: Int,
                    @RequestParam(name = "channel") channel: String,
                    @RequestParam(name = "qm") qm: String,
                    @RequestParam(name = "userId") userId: String,
                    @RequestParam(name = "password", required = false) password: String?,
                    @RequestParam(name = "queue") queue: String,
                    @RequestParam(name = "implementation") implementation: Implementation): Message? {
        val connectionInformation = ConnectionInformation(host = host, port = port, channel = channel, qm = qm, userId = userId, password = password, queue = queue, implementation = implementation)

        return messageService.pull(jmsId, connectionInformation)
    }

    @GetMapping(value = ["/message/list"])
    @CrossOrigin
    fun listMessages(@RequestParam(name = "size", required = false) size: Int = 100,
                     @RequestParam(name = "host") host: String,
                     @RequestParam(name = "port") port: Int,
                     @RequestParam(name = "channel") channel: String,
                     @RequestParam(name = "qm") qm: String,
                     @RequestParam(name = "userId") userId: String,
                     @RequestParam(name = "password", required = false) password: String?,
                     @RequestParam(name = "queue") queue: String,
                     @RequestParam(name = "implementation") implementation: Implementation): List<Message> {
        val connectionInformation = ConnectionInformation(host = host, port = port, channel = channel, qm = qm, userId = userId, password = password, queue = queue, implementation = implementation)

        return messageService.list(size, connectionInformation)
    }

    @GetMapping(value = ["/message/stats"])
    @CrossOrigin
    fun getMessageStats(@RequestParam(name = "host") host: String,
                        @RequestParam(name = "port") port: Int,
                        @RequestParam(name = "channel") channel: String,
                        @RequestParam(name = "qm") qm: String,
                        @RequestParam(name = "userId") userId: String,
                        @RequestParam(name = "password", required = false) password: String?,
                        @RequestParam(name = "queue") queue: String,
                        @RequestParam(name = "implementation") implementation: Implementation): MessageQueueInfoResponse {
        val connectionInformation = ConnectionInformation(host = host, port = port, channel = channel, qm = qm, userId = userId, password = password, queue = queue, implementation = implementation)

        return messageService.info(connectionInformation)
    }

}
