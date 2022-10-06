package com.tractive.model

data class ProductInfo(val version: Int, val edition: String? = null)

data class PurchasedProductInfo(val version: Int, val edition: String? = null, val quantity: Int)