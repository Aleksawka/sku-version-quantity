package com.tractive

import com.tractive.model.ProductInfo
import com.tractive.model.PurchasedProductInfo

interface IAggregatePurchasedInfo {
    fun aggregate(products: List<String>, productInfo: Map<String, ProductInfo>): List<PurchasedProductInfo>
}

class AggregatePurchasedInfo : IAggregatePurchasedInfo {
        @Throws(IllegalArgumentException::class)
        override fun aggregate(products: List<String>, productInfo: Map<String, ProductInfo>): List<PurchasedProductInfo> {
            // Check product info before processing
            val productWithoutInfo = products.firstOrNull { code ->
                code !in productInfo.keys
            }

            if (productWithoutInfo != null) {
                throw IllegalArgumentException("No information for product code $productWithoutInfo")
            }

            return products.groupingBy { productName -> productName }.eachCount().toSortedMap().map { productAggregate ->
                val product = productInfo[productAggregate.key]!!
                PurchasedProductInfo(product.version, product.edition, productAggregate.value)
            }
        }
}