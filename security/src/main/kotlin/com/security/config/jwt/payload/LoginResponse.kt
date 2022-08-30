package com.security.config.jwt.payload

class LoginResponse(val accessToken: String? = null) {
    private val tokenType = "Bearer"
}