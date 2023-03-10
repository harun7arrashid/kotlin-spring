package id.ackerman.restful.service.impl

import id.ackerman.restful.entity.Product
import id.ackerman.restful.error.NotFoundException
import id.ackerman.restful.model.CreateProductRequest
import id.ackerman.restful.model.ListProductRequest
import id.ackerman.restful.model.ProductResponse
import id.ackerman.restful.model.UpdateProductRequest
import id.ackerman.restful.repository.ProductRepository
import id.ackerman.restful.service.ProductService
import id.ackerman.restful.validation.ValidationUtil
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

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

        val product = findProductByIdOrThrowNotFound(id) // jadi klo product nya tidak ada dari id tersebut maka return null\

        return convertProductToResponse(product)
    }

    override fun update(id: String, request: UpdateProductRequest): ProductResponse {

        val product = findProductByIdOrThrowNotFound(id)

        validationUtil.validate(request)

        product.apply {
            name     = request.name!!
            price    = request.price!! // ini knp dibiarin null aja klo misal null, soalnya biar nanti bisa masuk ek exception
            quantity = request.quantity!!
            updateAt = Date()
        }

        repository.save(product)

        return convertProductToResponse(product)
    }

    override fun delete(id: String) {

        val product = findProductByIdOrThrowNotFound(id)
        repository.delete(product)

    }

    override fun list(request: ListProductRequest): List<ProductResponse> {

        val page = repository.findAll(PageRequest.of(request.page, request.size))
        val products: List<Product> = page.get().collect(Collectors.toList())

        return products.map { convertProductToResponse(it) }
    }

    private fun findProductByIdOrThrowNotFound(id: String): Product {

        val product = repository.findByIdOrNull(id)
        if (product == null) throw NotFoundException()
        else return product

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