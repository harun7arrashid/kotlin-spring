package id.ackerman.restful.service

import id.ackerman.restful.model.CreateProductRequest
import id.ackerman.restful.model.ProductResponse

interface ProductService {

    fun create(request: CreateProductRequest): ProductResponse

    fun get(id: String) : ProductResponse
}