package br.com.pgtel.pgtelbackend.config.security.jwt

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.firewall.RequestRejectedException
import org.springframework.stereotype.Component


@Component
class AuthEntryPointJwt : AuthenticationEntryPoint {
    private val logger: Logger = LoggerFactory.getLogger(AuthEntryPointJwt::class.java)
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse?,
        authException: AuthenticationException
    ) {

        logger.error("Unauthorized error: {}", authException.message)

        response!!.contentType = MediaType.APPLICATION_JSON_VALUE
        response.status = HttpServletResponse.SC_UNAUTHORIZED

        val body: MutableMap<String, Any> = HashMap()
        body["status"] = HttpServletResponse.SC_UNAUTHORIZED
        body["error"] = "Unauthorized"
        body["message"] = authException.message ?: "Unexpected error"
        body["path"] = request.servletPath

        val mapper = ObjectMapper()
        mapper.writeValue(response.outputStream, body)
    }

}