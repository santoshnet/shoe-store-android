package com.frontendsource.shoestore.models

import androidx.room.Entity
import kotlinx.serialization.Serializable

@Entity(tableName = "productSizes", primaryKeys = ["productId", "size"])
@Serializable
data class ProductSize(
    val productId: Int,
    val size: Int,
)