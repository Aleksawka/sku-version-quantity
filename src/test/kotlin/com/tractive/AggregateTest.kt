package com.tractive

import com.tractive.model.ProductInfo
import com.tractive.model.PurchasedProductInfo
import kotlin.test.Test
import kotlin.test.assertContentEquals

internal class AggregateTest {

    private val testExample: IAggregatePurchasedInfo = AggregatePurchasedInfo()

    @Test
    fun test1() {
        val expected = listOf(
            PurchasedProductInfo(version = 1, edition = "X", quantity = 1),
            PurchasedProductInfo(version = 1, quantity = 1),
            PurchasedProductInfo(version = 2,edition = "Z",quantity = 2)
        )

        val productsInfo = mapOf(
            "CVCD" to ProductInfo(version = 1, edition = "X"),
            "DDDF" to ProductInfo(version = 1),
            "SDFD" to ProductInfo(version = 2,edition = "Z")
        )

        val productCodes = listOf("CVCD", "SDFD", "DDDF", "SDFD")

        assertContentEquals(expected, testExample.aggregate(productCodes, productsInfo))
    }
}