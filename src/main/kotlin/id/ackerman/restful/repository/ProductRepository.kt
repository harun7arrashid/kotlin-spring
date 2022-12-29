package id.ackerman.restful.repository

import id.ackerman.restful.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository: JpaRepository<Product, String> { // Generic yg kedua itu tipe data id nya



}