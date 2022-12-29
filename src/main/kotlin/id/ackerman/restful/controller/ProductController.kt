package id.ackerman.restful.controller

import id.ackerman.restful.model.CreateProductRequest
import id.ackerman.restful.model.ProductResponse
import id.ackerman.restful.model.WebResponse
import id.ackerman.restful.service.ProductService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController // untuk nandain klo ini RESTful API
class ProductController(val service: ProductService) {

    @PostMapping(
        value    = ["/api/products"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun createProduct(@RequestBody request: CreateProductRequest): WebResponse<ProductResponse> {
        val response = service.create(request)

        return WebResponse(
            200,
            "OK",
            response
        )
    }
}