package com.example.spendwise.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.spendwise.Data.ExpenseDatabase
import com.example.spendwise.Data.dao.ExpenseDao
import com.example.spendwise.Data.model.ExpenseEntity

class AddExpenseViewModel(val dao: ExpenseDao): ViewModel() {

    suspend fun addExpense(expenseEntity: ExpenseEntity): Boolean{
        return try {
            dao.insertExpenses(expenseEntity)
            true
        }catch (ex: Throwable){
            false
        }
    }

}

class AddExpenseViewModelFactory(private val context: Context): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AddExpenseViewModel::class.java)){
            val dao = ExpenseDatabase.getDatabase(context).expenseDAO()
            @Suppress("UNCHECKED_CAST")
            return  AddExpenseViewModel(dao) as T
        }
        throw  IllegalArgumentException("Unkown ViewModel class")
    }
}