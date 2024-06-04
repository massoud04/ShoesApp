package com.bytesthetic.shoesapp.model

import kotlin.random.Random

data class ProductUiModel(
    var id: String?=null,
    var imageResource: String? = null,
    var color: String? = null,
    var name: String? = null,
    var price: Float? = null,
    var oldPrice: Float? = null,
    val rating: Float = 4.5f,
    val number: Int = Random.nextInt(10, 99),
    var description: String? = null // Add a new property for description
) {
    constructor() : this(null, null, null, null, null)
}

data class CartItemModel(
    val id: String = "",
    var quantity: Int = 0
)