package com.security.config.jwt

import com.security.common.FunctionCommonUtils
import com.security.config.jwt.payload.LoginResponse
import com.security.config.jwt.service.UserDetailsImpl
import io.jsonwebtoken.*
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtils {
    @Value("\${app.jwt.secret}")
    private val jwtSecret: String? = null

    @Value("\${app.jwt.expiration}")
    private val jwtExpirationMs = 0

    @Autowired
    lateinit var functionCommonUtils: FunctionCommonUtils

    fun generateJwtToken(authentication: Authentication): LoginResponse {
        val userPrincipal: UserDetailsImpl = authentication.principal as UserDetailsImpl
        val issuedAt = Date()
        val expiration = Date(issuedAt.time + jwtExpirationMs)
        val token = Jwts.builder()
            .setSubject(userPrincipal.getUsername())
            .setIssuedAt(issuedAt)
            .setExpiration(expiration)
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact()
        val pattern = "yyyy-MM-dd HH:mm:ss"
        return LoginResponse(
            accessToken = token,
            issuedAt = functionCommonUtils.dateToString(issuedAt, pattern),
            expiration = functionCommonUtils.dateToString(expiration, pattern)
        )
    }

    fun getUserNameFromJwtToken(token: String?): String {
        return Jwts.parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(token)
            .body
            .subject
    }

    fun validateJwtToken(authToken: String?) {
        try {
            Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(authToken)
        } catch (e: Exception) {
            logger.error(e.message, e)
            throw e
        }
//        } catch (e: SignatureException) {
//            logger.error("Invalid JWT signature: {}", e.message)
//            throw Exception()
//        } catch (e: MalformedJwtException) {
//            logger.error("Invalid JWT token: {}", e.message)
//        } catch (e: ExpiredJwtException) {
//            logger.error("JWT token is expired: {}", e.message)
//        } catch (e: UnsupportedJwtException) {
//            logger.error("JWT token is unsupported: {}", e.message)
//        } catch (e: IllegalArgumentException) {
//            logger.error("JWT claims string is empty: {}", e.message)
//        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(JwtUtils::class.java)
    }
}