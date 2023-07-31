package studio.hcmc.board.controller

import org.springframework.web.bind.annotation.*
import studio.hcmc.board.dto.BoardDTO
import studio.hcmc.board.service.BoardService
import studio.hcmc.kotlin.protocol.SortOrder
import studio.hcmc.spring.controller.ControllerWrapper
import studio.hcmc.spring.controller.respondArray
import studio.hcmc.spring.controller.respondEmpty
import studio.hcmc.spring.controller.respondObject

@RestController
class BoardController(
    private val boardService: BoardService
) : ControllerWrapper {
    @RequestMapping(
        value = ["/boards"],
        method = [RequestMethod.POST]
    )
    fun post(
        @RequestBody dto: BoardDTO.Post
    ) = respondObject {
        boardService.add(dto)
    }

    @RequestMapping(
        value = ["/boards/{id}"],
        method = [RequestMethod.PUT]
    )
    fun put(
        @PathVariable("id") id: Long,
        @RequestBody dto: BoardDTO.Put
    ) = respondObject {
        boardService.set(id, dto)
    }

    @RequestMapping(
        value = ["/boards/{id}"],
        method = [RequestMethod.DELETE]
    )
    fun delete(
        @PathVariable("id") id: Long
    ) = respondEmpty {
        boardService.remove(id)
    }

    @RequestMapping(
        value = ["/boards/{id}"],
        method = [RequestMethod.GET]
    )
    fun get(
        @PathVariable("id") id: Long
    ) = respondObject {
        boardService.get(id)
    }

    @RequestMapping(
        value = ["/boards"],
        params = ["offset", "size", "sortOrder"],
        method = [RequestMethod.GET]
    )
    fun listAll(
        @RequestParam("offset") offset: Long,
        @RequestParam("size") size: Int,
        @RequestParam("sortOrder") sortOrder: SortOrder
    ) = respondArray {
        boardService.listAll(offset, size, sortOrder)
    }

    @RequestMapping(
        value = ["/boards/count"],
        method = [RequestMethod.GET]
    )
    fun countAll() = respondObject {
        boardService.countAll()
    }
}