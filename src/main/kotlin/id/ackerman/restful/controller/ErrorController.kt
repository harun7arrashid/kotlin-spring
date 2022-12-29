package id.ackerman.restful.controller

import id.ackerman.restful.error.NotFoundException
import id.ackerman.restful.model.WebResponse
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class ErrorController {

    // jadi misalkan ada throwException di service kita trs tipe exceptionya itu ini, maka dia akan ditangkep oleh ini
    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun validationHandler(exception: ConstraintViolationException): WebResponse<String> {

        return WebResponse(
            400,
            "BAD REQUEST",
            exception.message ?: ""
        )
    }

    @ExceptionHandler(value = [NotFoundException::class])
    fun notFound(exception: NotFoundException): WebResponse<String> =
        WebResponse(
            404,
            "NOT FOUND",
            "Not Found"
        )
}