package id.ackerman.restful.service

import id.ackerman.restful.model.CreateProductRequest
import id.ackerman.restful.model.ListProductRequest
import id.ackerman.restful.model.ProductResponse
import id.ackerman.restful.model.UpdateProductRequest

interface ProductService {

    fun create(request: CreateProductRequest): ProductResponse

    fun get(id: String) : ProductResponse

    fun update(id: String, request: UpdateProductRequest): ProductResponse

    fun delete(id: String)

    fun list(request: ListProductRequest): List<ProductResponse>

}