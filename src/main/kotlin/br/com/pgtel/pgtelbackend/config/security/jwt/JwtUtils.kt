package br.com.pgtel.pgtelbackend.config.security.jwt

import io.jsonwebtoken.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component


@Component
class JwtUtils(
    @Value("\${pgtel.app.jwtSecret}")
    private val jwtSecret: String,
    @Value("\${pgtel.app.jwtExpirationMs}")
    private val jwtExpirationMs: Int,
) {

    private val logger: Logger = LoggerFactory.getLogger(JwtUtils::class.java)

    fun generateJwtTokenFromUsername(username: String): String {
        logger.debug("Generating JWT token for username: $username")
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(java.util.Date())
            .setExpiration(java.util.Date(System.currentTimeMillis() + jwtExpirationMs))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact()
    }

    fun validateJwtToken(jwt: String): Boolean {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt)
            return true
        } catch (e: SignatureException) {
            logger.error("Invalid JWT signature: {}", e.message)
        } catch (e: MalformedJwtException) {
            logger.error("Invalid JWT token: {}", e.message)
        } catch (e: ExpiredJwtException) {
            logger.error("JWT token is expired: {}", e.message)
        } catch (e: UnsupportedJwtException) {
            logger.error("JWT token is unsupported: {}", e.message)
        } catch (e: IllegalArgumentException) {
            logger.error("JWT claims string is empty: {}", e.message)
        }

        return false
    }

    fun getUsernameFromJwtToken(jwt: String): String? {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt).body.subject
    }

}