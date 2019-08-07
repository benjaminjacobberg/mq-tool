package com.github.benjaminjacobberg.ibmmqtool

data class Message(val header: MessageHeader?, val body: String)
