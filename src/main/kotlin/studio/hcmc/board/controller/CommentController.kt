package studio.hcmc.board.controller

import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.*
import studio.hcmc.board.dto.CommentDTO
import studio.hcmc.board.service.CommentService
import studio.hcmc.kotlin.protocol.SortOrder
import studio.hcmc.spring.controller.ControllerWrapper
import studio.hcmc.spring.controller.respondArray
import studio.hcmc.spring.controller.respondEmpty
import studio.hcmc.spring.controller.respondObject

@RestController
class CommentController(
    private val commentService: CommentService
) : ControllerWrapper {
    @RequestMapping(
        value = ["/boards/articles/{articleId}/comments"],
        method = [RequestMethod.POST]
    )
    fun post(
        @PathVariable("articleId") articleId: Long,
        @RequestBody dto: CommentDTO.Post,
        request: HttpServletRequest
    ) = respondObject {
        commentService.add(articleId, request.remoteAddr, dto)
    }

    @RequestMapping(
        value = ["/boards/articles/comments/{id}"],
        method = [RequestMethod.PUT]
    )
    fun put(
        @PathVariable("id") id: Long,
        @RequestBody dto: CommentDTO.Put
    ) = respondObject {
        commentService.set(id, dto)
    }

    @RequestMapping(
        value = ["/boards/articles/comments/{id}"],
        method = [RequestMethod.DELETE]
    )
    fun delete(
        @PathVariable("id") id: Long
    ) = respondEmpty {
        commentService.remove(id)
    }

    @RequestMapping(
        value = ["/boards/articles/comments/{id}"],
        method = [RequestMethod.GET]
    )
    fun get(
        @PathVariable("id") id: Long
    ) = respondObject {
        commentService.get(id)
    }

    @RequestMapping(
        value = ["/boards/articles/comments"],
        params = ["offset", "size", "sortOrder"],
        method = [RequestMethod.GET]
    )
    fun listAll(
        @RequestParam("offset") offset: Long,
        @RequestParam("size") size: Int,
        @RequestParam("sortOrder") sortOrder: SortOrder
    ) = respondArray {
        commentService.listAll(offset, size, sortOrder)
    }

    @RequestMapping(
        value = ["/boards/articles/{articleId}/comments"],
        params = ["offset", "size", "sortOrder"],
        method = [RequestMethod.GET]
    )
    fun listByArticleId(
        @PathVariable("articleId") articleId: Long,
        @RequestParam("offset") offset: Long,
        @RequestParam("size") size: Int,
        @RequestParam("sortOrder") sortOrder: SortOrder
    ) = respondArray {
        commentService.listByArticleId(articleId, offset, size, sortOrder)
    }

    @RequestMapping(
        value = ["/boards/articles/{articleId}/comments/count"],
        method = [RequestMethod.GET]
    )
    fun countByBoardId(
        @PathVariable("articleId") articleId: Long
    ) = respondObject {
        commentService.countByArticleId(articleId)
    }
}