package com.github.benjaminjacobberg.ibmmqtool

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MessageController(private val messageService: MessageService) {
    @PostMapping(value = ["/message"], consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
    fun sendMessage(sendMessageDTO: SendMessageDTO) = messageService.submit(Message(sendMessageDTO.body), ConnectionInformation(host = sendMessageDTO.host, port = sendMessageDTO.port, channel = sendMessageDTO.channel, qm = sendMessageDTO.qm, userId = sendMessageDTO.userId, password = sendMessageDTO.password, queue = sendMessageDTO.queue))

    @GetMapping(value = ["/message/{size}/connection/details/host/{host}/port/{port}/channel/{channel}/queue_manager/{qm}/username/{userId}/password/{password}/queue/{queue}"])
    fun getMessage(@PathVariable(value = "size", required = false) size: Int = 100, @PathVariable(value = "host") host: String, @PathVariable(value = "port") port: Int, @PathVariable(value = "channel") channel: String, @PathVariable(value = "qm") qm: String, @PathVariable(value = "userId") userId: String, @PathVariable(value = "password") password: String, @PathVariable(value = "queue") queue: String): List<Message> = messageService.get(size, ConnectionInformation(host = host, port = port, channel = channel, qm = qm, userId = userId, password = password, queue = queue))
}
