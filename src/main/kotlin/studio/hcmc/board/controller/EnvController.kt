package studio.hcmc.board.controller

import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import studio.hcmc.spring.controller.ControllerWrapper
import studio.hcmc.spring.controller.respondObject

@RestController
class EnvController : ControllerWrapper {
    @RequestMapping(
        value = ["/env/address"],
        method = [RequestMethod.GET]
    )
    fun getAddress(
        request: HttpServletRequest
    ) = respondObject {
        request.remoteAddr
    }
}