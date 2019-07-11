package com.github.benjaminjacobberg.ibmmqtool

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class IbmMqToolApplication

fun main(args: Array<String>) {
    runApplication<IbmMqToolApplication>(*args)
}
