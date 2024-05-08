package org.example.grocery.core.device

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform