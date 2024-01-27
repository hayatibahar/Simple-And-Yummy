package com.hayatibahar.simpleandyummy.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hayatibahar.simpleandyummy.core.common.DbConstants.GROCERY_TABLE

@Entity(tableName = GROCERY_TABLE)
data class GroceryEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val nameClean: String,
    val amount: Double,
    val unit: String,
    val isChecked: Boolean,
)