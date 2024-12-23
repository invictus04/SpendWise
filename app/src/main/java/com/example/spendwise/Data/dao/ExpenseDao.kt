package com.example.spendwise.Data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.spendwise.Data.model.ExpenseEntity
import com.example.spendwise.Data.model.ExpenseSummary
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {

    @Query("SELECT * FROM expense_table")
    fun getAllExpenses(): Flow<List<ExpenseEntity>>

    @Query("SELECT type, date, SUM(amount) AS total_amount FROM expense_table where type = :type GROUP BY type, date ORDER BY date")
    fun getAllExpensesByDate(type: String = "Expense"): Flow<List<ExpenseSummary>>

    @Query("SELECT * FROM expense_table WHERE type = 'Expense' ORDER BY amount DESC LIMIT 5")
    fun getTopExpenses(): Flow<List<ExpenseEntity>>

    @Query("SELECT * FROM expense_table WHERE type = 'Income' ORDER BY amount DESC LIMIT 5")
    fun getTopIncome(): Flow<List<ExpenseEntity>>


    @Insert
    suspend fun insertExpenses(expenseEntity: ExpenseEntity)

    @Delete
    suspend fun deleteExpenses(expenseEntity: ExpenseEntity)

    @Update
    suspend fun updateExpenses(expenseEntity: ExpenseEntity)
}