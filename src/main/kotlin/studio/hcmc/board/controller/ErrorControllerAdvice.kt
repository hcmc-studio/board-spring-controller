package studio.hcmc.board.controller

import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import studio.hcmc.board.dto.ErrorDTO
import studio.hcmc.board.dto.status
import studio.hcmc.kotlin.protocol.io.Response
import studio.hcmc.spring.controller.ControllerWrapper
import studio.hcmc.spring.controller.respondError

@RestControllerAdvice
class ErrorControllerAdvice : ControllerWrapper {
    @ExceptionHandler
    fun handleGlobal(throwable: Throwable, response: HttpServletResponse): Response.Error {
        if (throwable is ErrorDTO) {
            response.status = when (throwable) {
                is ErrorDTO.BoardNotFound -> throwable.status
                is ErrorDTO.ArticleNotFound -> throwable.status
                is ErrorDTO.ArticlePasswordMismatch -> throwable.status
                is ErrorDTO.CommentNotFound -> throwable.status
                is ErrorDTO.CommentPasswordMismatch -> throwable.status
            }.value()

            return respondError { throwable }
        }

        throw throwable
    }
}