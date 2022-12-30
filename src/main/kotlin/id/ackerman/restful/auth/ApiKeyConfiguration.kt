package id.ackerman.restful.auth

import org.springframework.stereotype.Component
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Component // untuk nge registrasiin ApiKeyInterceptor ke WebMvcConfigurer
class ApiKeyConfiguration(val interceptor: ApiKeyInterceptor) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        super.addInterceptors(registry)

        registry.addWebRequestInterceptor(interceptor) // biar otomatis ke registrasi
    }

}