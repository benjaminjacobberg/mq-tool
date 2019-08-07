package com.github.benjaminjacobberg.ibmmqtool

import org.springframework.web.bind.annotation.*

@RestController
class MessageController(private val messageService: MessageService) {

    @PostMapping(value = ["/message"])
    fun sendMessage(@RequestBody sendMessageDTO: SendMessageDTO): Unit {
        val message = Message(null, sendMessageDTO.body)
        val connectionInformation = ConnectionInformation(host = sendMessageDTO.host, port = sendMessageDTO.port, channel = sendMessageDTO.channel, qm = sendMessageDTO.qm, userId = sendMessageDTO.userId, password = sendMessageDTO.password, queue = sendMessageDTO.queue)
        messageService.submit(message, connectionInformation)
    }

    @GetMapping(value = ["/message"])
    fun getMessage(@RequestParam(name = "size", required = false) size: Int = 100,
                   @RequestParam(name = "host") host: String,
                   @RequestParam(name = "port") port: Int,
                   @RequestParam(name = "channel") channel: String,
                   @RequestParam(name = "qm") qm: String,
                   @RequestParam(name = "userId") userId: String,
                   @RequestParam(name = "password", required = false) password: String?,
                   @RequestParam(name = "queue") queue: String): List<Message> {
        val connectionInformation = ConnectionInformation(host = host, port = port, channel = channel, qm = qm, userId = userId, password = password, queue = queue)

        return messageService.get(size, connectionInformation)
    }

}
