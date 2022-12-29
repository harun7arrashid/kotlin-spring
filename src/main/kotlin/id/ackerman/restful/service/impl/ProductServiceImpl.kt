package id.ackerman.restful.service.impl

import id.ackerman.restful.entity.Product
import id.ackerman.restful.model.CreateProductRequest
import id.ackerman.restful.model.ProductResponse
import id.ackerman.restful.repository.ProductRepository
import id.ackerman.restful.service.ProductService
import id.ackerman.restful.validation.ValidationUtil
import org.springframework.stereotype.Service
import java.util.*

// implementasi dari ProductService

@Service
class ProductServiceImpl(
    val repository: ProductRepository,
    val validationUtil: ValidationUtil
    ) : ProductService {

    override fun create(request: CreateProductRequest): ProductResponse {

        validationUtil.validate(request)

        val product = Product(
            request.id!!,
            request.name!!,
            request.price!!,
            request.quantity!!,
            Date(),
            null
        )
        repository.save(product) // di save ke database

        return ProductResponse(
            product.id,
            product.name,
            product.price,
            product.quantity,
            product.createAt,
            product.updateAt
        )
    }
}