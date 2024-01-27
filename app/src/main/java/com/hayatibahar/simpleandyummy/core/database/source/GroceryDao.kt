package com.hayatibahar.simpleandyummy.core.database.source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hayatibahar.simpleandyummy.core.database.entity.GroceryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GroceryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGroceries(groceries: List<GroceryEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGrocery(groceryEntity: GroceryEntity)

    @Delete
    suspend fun deleteGrocery(groceryEntity: GroceryEntity)

    @Query("DELETE FROM GROCERY_TABLE WHERE isChecked = 1")
    suspend fun deleteCheckedGroceries()

    @Query("DELETE FROM GROCERY_TABLE")
    suspend fun deleteGroceries()

    @Query("UPDATE GROCERY_TABLE SET isChecked = :isChecked WHERE id = :id")
    suspend fun updateGroceryCheckStatus(id: Int, isChecked: Boolean)

    @Query("SELECT * FROM GROCERY_TABLE")
    fun getGroceries(): Flow<List<GroceryEntity>>
}