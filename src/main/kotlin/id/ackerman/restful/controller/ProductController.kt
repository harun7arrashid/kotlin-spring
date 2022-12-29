package id.ackerman.restful.controller

import id.ackerman.restful.model.CreateProductRequest
import id.ackerman.restful.model.ProductResponse
import id.ackerman.restful.model.WebResponse
import id.ackerman.restful.service.ProductService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController // untuk nandain klo ini RESTful API
class ProductController(val service: ProductService) {

    @PostMapping(
        value    = ["/api/products"],
        produces = ["application/json"], // ini itu response
        consumes = ["application/json"] // ini untuk body (ngisi request)
    )
    fun createProduct(@RequestBody request: CreateProductRequest): WebResponse<ProductResponse> {
        val response = service.create(request)

        return WebResponse(
            200,
            "OK",
            response
        )
    }

    @GetMapping(
        value    = ["/api/products/{idProduct}"],
        produces = ["application/json"]
    )
    fun getProduct(@PathVariable("idProduct") id: String): WebResponse<ProductResponse> {

        val response = service.get(id)

        return WebResponse(
            200,
            "OK",
            response
        )
    }
}