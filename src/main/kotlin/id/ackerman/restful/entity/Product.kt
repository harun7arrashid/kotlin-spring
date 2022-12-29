package id.ackerman.restful.entity

import java.util.Date
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name= "products")
data class Product(
    @Id // ngasih tau klo ini primary key
    val id: String,

    @Column(name = "name")
    val name: String,

    @Column(name = "price")
    val price: Long,

    @Column(name = "quantity")
    val quantity: Int,

    @Column(name = "create_at")
    val createAt: Date,

    @Column(name = "update_at")
    val updateAt: Date?
)