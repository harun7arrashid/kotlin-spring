package id.ackerman.restful.validation

import org.springframework.stereotype.Component
import javax.validation.ConstraintViolationException
import javax.validation.Validator

@Component class ValidationUtil(val validator: Validator) {

    fun validate(any: Any) { // jadi ini dipanggil di ServiceImpl, trs nanti bakal di detek di ErrorController
        val result = validator.validate(any)
        if (result.size != 0) {
            throw ConstraintViolationException(result) // ini klo gk dihandle nanti balikannya itu 500
        }
    }
}
// jika result.size nya == 0 dia valid, ini dipake di tiap service
