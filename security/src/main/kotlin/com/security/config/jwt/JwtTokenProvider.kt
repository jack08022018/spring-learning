package com.security.config.jwt

import com.security.config.jwt.user.CustomUserDetails
import io.jsonwebtoken.*
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtTokenProvider {
    private val logger = LoggerFactory.getLogger(JwtTokenProvider::class.java)

    private val JWT_SECRET = "lodaaaaaa"
    private val JWT_EXPIRATION = 604800000L
    fun generateToken(userDetails: CustomUserDetails): String {
        // Lấy thông tin user
        val now = Date()
        val expiryDate = Date(now.time + JWT_EXPIRATION)
        // Tạo chuỗi json web token từ id của user.
        return Jwts.builder()
            .setSubject(userDetails.user!!.id.toString())
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
            .compact()
    }

    fun getUserIdFromJWT(token: String?): Long {
        val claims = Jwts.parser()
            .setSigningKey(JWT_SECRET)
            .parseClaimsJws(token)
            .body
        return claims.subject.toLong()
    }

    fun validateToken(authToken: String?): Boolean {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken)
            return true
        } catch (ex: MalformedJwtException) {
            logger.error("Invalid JWT token")
        } catch (ex: ExpiredJwtException) {
            logger.error("Expired JWT token")
        } catch (ex: UnsupportedJwtException) {
            logger.error("Unsupported JWT token")
        } catch (ex: IllegalArgumentException) {
            logger.error("JWT claims string is empty.")
        }
        return false
    }
}