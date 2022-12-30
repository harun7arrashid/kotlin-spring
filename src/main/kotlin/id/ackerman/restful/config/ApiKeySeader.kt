package id.ackerman.restful.config

import id.ackerman.restful.entity.ApiKey
import id.ackerman.restful.repository.ApiKeyRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component // biar ter registrasi di Spring
class ApiKeySeader(val repository: ApiKeyRepository): ApplicationRunner {

    val apiKey = "SECRET"

    override fun run(args: ApplicationArguments?) {
        if (!repository.existsById(apiKey)) { // kalo misalkan blm tersedia maka apiKey nya disimpan ke DB

            val entity = ApiKey(id = apiKey)
            repository.save(entity)

        }
     }
}


// ApplicationRunner dia akan di eksekusi ketika program spring AKAN berjalan..