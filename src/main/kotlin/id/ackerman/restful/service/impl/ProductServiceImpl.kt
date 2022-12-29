package id.ackerman.restful.service.impl

import id.ackerman.restful.entity.Product
import id.ackerman.restful.error.NotFoundException
import id.ackerman.restful.model.CreateProductRequest
import id.ackerman.restful.model.ProductResponse
import id.ackerman.restful.repository.ProductRepository
import id.ackerman.restful.service.ProductService
import id.ackerman.restful.validation.ValidationUtil
import org.springframework.data.repository.findByIdOrNull
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

        return convertProductToResponse(product)
    }

    override fun get(id: String): ProductResponse {

        val product = repository.findByIdOrNull(id) // jadi klo product nya tidak ada dari id tersebut maka return null

        if (product == null) throw NotFoundException()
        else {
            return convertProductToResponse(product)
        }
    }

    private fun convertProductToResponse(product: Product): ProductResponse =
        ProductResponse(
            product.id,
            product.name,
            product.price,
            product.quantity,
            product.createAt,
            product.updateAt
        )

}

// di fun get
// if (product == null) throw NotFoundException(), ini gda yg nangkep exceptionnya