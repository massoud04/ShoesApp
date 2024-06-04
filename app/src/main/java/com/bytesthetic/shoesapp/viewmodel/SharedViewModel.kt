package com.bytesthetic.shoesapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bytesthetic.shoesapp.mock.generateProducts
import com.bytesthetic.shoesapp.model.ProductUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {
    private val _selectedProduct = MutableStateFlow<ProductUiModel?>(null)
    val selectedProduct = _selectedProduct.asStateFlow()

    private val _products = MutableStateFlow<List<ProductUiModel>>(emptyList())
    val products = _products.asStateFlow()

    private val _shoppingCart = MutableStateFlow<List<ProductUiModel>>(emptyList())
    val shoppingCart = _shoppingCart.asStateFlow()

    init {
        fetchProducts()
    }

    fun selectProduct(product: ProductUiModel) {
        _selectedProduct.value = product
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            _products.value = generateProducts()
        }
    }
}
