package com.bytesthetic.shoesapp.mock

import com.bytesthetic.shoesapp.model.ProductUiModel
import com.google.firebase.database.*
import kotlinx.coroutines.tasks.await

suspend fun generateProducts(): List<ProductUiModel> {
    val productList = mutableListOf<ProductUiModel>()

    // Get a reference to your Firebase database
    val database = FirebaseDatabase.getInstance().getReference("products")

    // Attach a listener to read the data at your products reference
    val dataSnapshot = database.get().await()
    for (productSnapshot in dataSnapshot.children) {
        val product = productSnapshot.getValue(ProductUiModel::class.java)
        product?.let { productList.add(it) }
    }

    return productList
}

fun generateCategories(): List<String> {
    val categoryList = mutableListOf<String>()

    categoryList.add("Sneakers")

    return categoryList
}
