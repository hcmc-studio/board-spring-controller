package studio.hcmc.board.controller

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import studio.hcmc.spring.controller.ControllerWrapper
import java.io.File
import java.net.URLEncoder
import java.nio.charset.Charset

@RestController
class StaticController : ControllerWrapper {
    @RequestMapping(
        value = ["/static/**"],
        method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_OCTET_STREAM_VALUE]
    )
    fun get(
        request: HttpServletRequest,
        response: HttpServletResponse
    ): ByteArray {
        val path = request.requestURI.removePrefix("/static/")
        val file = File(path)
        val name = file.name
        response.setHeader("Content-Disposition", "attachment; filename=\"${URLEncoder.encode(name, Charset.defaultCharset())}\"")
        response.setHeader("Content-Transfer-Encoding", "binary")

        return file.readBytes()
    }
}