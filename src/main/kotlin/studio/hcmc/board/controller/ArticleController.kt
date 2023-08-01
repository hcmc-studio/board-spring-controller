package studio.hcmc.board.controller

import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import studio.hcmc.board.dto.ArticleDTO
import studio.hcmc.board.service.ArticleService
import studio.hcmc.kotlin.protocol.SortOrder
import studio.hcmc.spring.controller.ControllerWrapper
import studio.hcmc.spring.controller.respondArray
import studio.hcmc.spring.controller.respondEmpty
import studio.hcmc.spring.controller.respondObject

@RestController
class ArticleController(
    private val articleService: ArticleService
) : ControllerWrapper {
    @RequestMapping(
        value = ["/boards/{boardId}/articles"],
        method = [RequestMethod.POST]
    )
    fun post(
        @PathVariable("boardId") boardId: Long,
        @RequestBody dto: ArticleDTO.Post,
        request: HttpServletRequest
    ) = respondObject {
        articleService.add(boardId, request.remoteAddr, dto)
    }

    @RequestMapping(
        value = ["/boards/articles/{id}"],
        method = [RequestMethod.PUT]
    )
    fun put(
        @PathVariable("id") id: Long,
        @RequestBody dto: ArticleDTO.Put
    ) = respondObject {
        articleService.set(id, dto)
    }

    @RequestMapping(
        value = ["/boards/articles/{id}"],
        method = [RequestMethod.DELETE]
    )
    fun delete(
        @PathVariable("id") id: Long
    ) = respondEmpty {
        articleService.remove(id)
    }

    @RequestMapping(
        value = ["/boards/articles/{id}"],
        method = [RequestMethod.GET]
    )
    fun get(
        @PathVariable("id") id: Long
    ) = respondObject {
        articleService.get(id)
    }

    @RequestMapping(
        value = ["/boards/articles"],
        params = ["offset", "size", "sortOrder"],
        method = [RequestMethod.GET]
    )
    fun listAll(
        @RequestParam("offset") offset: Long,
        @RequestParam("size") size: Int,
        @RequestParam("sortOrder") sortOrder: SortOrder
    ) = respondArray {
        articleService.listAll(offset, size, sortOrder)
    }

    @RequestMapping(
        value = ["/boards/{boardId}/articles"],
        params = ["offset", "size", "sortOrder"],
        method = [RequestMethod.GET]
    )
    fun listByBoardId(
        @PathVariable("boardId") boardId: Long,
        @RequestParam("offset") offset: Long,
        @RequestParam("size") size: Int,
        @RequestParam("sortOrder") sortOrder: SortOrder
    ) = respondArray {
        articleService.listByBoardId(boardId, offset, size, sortOrder)
    }

    @RequestMapping(
        value = ["/boards/{boardId}/articles/count"],
        method = [RequestMethod.GET]
    )
    fun countByBoardId(
        @PathVariable("boardId") boardId: Long,
    ) = respondObject {
        articleService.countByBoardId(boardId)
    }
}