package id.ackerman.restful.controller

import id.ackerman.restful.model.WebResponse
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class ErrorController {

    // jadi misalkan ada throwException di service kita trs tipe exceptionya itu ini, maka dia akan ditangkep oleh ini
    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun validationException(exception: ConstraintViolationException): WebResponse<String> {

        return WebResponse(
            400,
            "BAD REQUEST",
            exception.message ?: ""
            )

    }
}