package id.ackerman.restful.auth

import id.ackerman.restful.error.UnauthorizedException
import id.ackerman.restful.repository.ApiKeyRepository
import org.springframework.stereotype.Component
import org.springframework.ui.ModelMap
import org.springframework.web.context.request.WebRequest
import org.springframework.web.context.request.WebRequestInterceptor
import java.lang.Exception

@Component // jadi ini semacem Middleware
class ApiKeyInterceptor(val repository: ApiKeyRepository) : WebRequestInterceptor {

    override fun preHandle(request: WebRequest) {
        // cek si Header nya ada di database apa ngga api_key nya
        val apiKey = request.getHeader("X-Api-Key") ?: throw UnauthorizedException()

        // klo dia ada di database brarti Validd
        if (!repository.existsById(apiKey)) {
            throw UnauthorizedException()
        }

    }

    override fun postHandle(request: WebRequest, model: ModelMap?) {}

    override fun afterCompletion(request: WebRequest, ex: Exception?) {}

}


// preHandle adalah fun yg akan dicall sblm Controller itu di eksekusi
// postHandle setelah Controller di eksekusi
// afterCompleteion, setelah selesai semuanya termasuk View