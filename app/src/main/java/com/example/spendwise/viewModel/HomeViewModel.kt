package com.example.spendwise.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.spendwise.Data.ExpenseDatabase
import com.example.spendwise.Data.dao.ExpenseDao
import com.example.spendwise.Data.model.ExpenseEntity
import com.example.spendwise.R
import com.example.spendwise.Utils

class HomeViewModel(dao: ExpenseDao): ViewModel() {
    val expenses = dao.getAllExpenses()
    val spending = dao.getTopExpenses()
    val income = dao.getTopIncome()


    fun getBalance(list: List<ExpenseEntity>): String{
        var total =0.0
        list.forEach {
            if(it.type == "Income"){
                total += it.amount
            } else {
                total -= it.amount
            }
        }
        return Utils.formatToDecimalValue(total)
    }

    fun getTotalExpense(list: List<ExpenseEntity>): String{
        var total = 0.0
        for (expense in list) {
            if (expense.type != "Income") {
                total += expense.amount
            }
        }
        return Utils.formatToDecimalValue(total)
    }

    fun getTotalIncome(list: List<ExpenseEntity>): String{
        var totalIncome = 0.0
        for (expense in list) {
            if (expense.type == "Income") {
                totalIncome += expense.amount
            }
        }
        return Utils.formatToDecimalValue(totalIncome);
    }

    fun getItemIcon(item: ExpenseEntity): Int {
        return when (item.category) {
            "PayPal" -> {
                R.drawable.paypal
            }
            "Spotify" -> {
                R.drawable.spotify
            }
            "Starbucks" -> {
                R.drawable.starbucks
            }
            "Upwork" -> {
                R.drawable.upwork
            }
            "YouTube" -> {
                R.drawable.youtube
            }
            else -> {
                R.drawable.photo
            }
        }
    }
}

class HomeViewModelFactory(private  val context:Context): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
            val dao = ExpenseDatabase.getDatabase(context).expenseDAO()
            @Suppress("UNCHECKED_CAST")
            return  HomeViewModel(dao) as T
        }
        throw  IllegalArgumentException("Unkown ViewModel class")
    }
}