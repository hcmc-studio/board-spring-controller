package studio.hcmc.board.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import studio.hcmc.spring.controller.ControllerWrapper
import studio.hcmc.spring.controller.respondObject

@RestController
class RootController : ControllerWrapper {
    @RequestMapping(
        value = ["/"],
        params = ["message"],
        method = [RequestMethod.GET]
    )
    fun echo(
        @RequestParam("message") message: String
    ) = respondObject {
        message
    }
}