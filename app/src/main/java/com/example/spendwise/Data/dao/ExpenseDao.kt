package com.example.spendwise.Data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.spendwise.Data.model.ExpenseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {

    @Query("SELECT * FROM expense_table")
    fun getAllExpenses(): Flow<List<ExpenseEntity>>

    @Insert
    suspend fun insertExpenses(expenseEntity: ExpenseEntity)

    @Delete
    suspend fun deleteExpenses(expenseEntity: ExpenseEntity)

    @Update
    suspend fun updateExpenses(expenseEntity: ExpenseEntity)
}