package com.github.benjaminjacobberg.mqtool

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MqToolApplication

fun main(args: Array<String>) {
    runApplication<MqToolApplication>(*args)
}
