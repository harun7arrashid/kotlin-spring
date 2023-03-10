package id.ackerman.restful.controller

import id.ackerman.restful.model.*
import id.ackerman.restful.service.ProductService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.jws.WebService

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

    @PutMapping(
        value = ["/api/products/{idProduct}"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun updateProduct(
        @PathVariable("idProduct") id: String,
        @RequestBody request: UpdateProductRequest
    ): WebResponse<ProductResponse> {

        val response = service.update(id, request)
        return WebResponse(
            200,
            "OK",
            response
        )
    }

    @DeleteMapping(
        value = ["/api/products/{idProduct}"],
        produces = ["application/json"],
    )
    fun deleteProduct(@PathVariable("idProduct") id: String): WebResponse<String> {
        service.delete(id)

        return WebResponse(code = 200, status = "OK", id)
    }

    @GetMapping(
        value    = ["/api/products"],
        produces = ["application/json"]
    )
    fun listProduct(
        @RequestParam(value = "size", defaultValue = "10") size: Int,
        @RequestParam(value = "page", defaultValue = "0") page: Int
    ): WebResponse<List<ProductResponse>> {

        val request   = ListProductRequest(page, size)
        val responses = service.list(request)

        return WebResponse(
            code = 200,
            status = "OK",
            data = responses
        )
    }
}