package com.example.spendwise.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.spendwise.Data.ExpenseDatabase
import com.example.spendwise.Data.dao.ExpenseDao
import com.example.spendwise.Data.model.ExpenseEntity
import com.example.spendwise.Data.model.ExpenseSummary
import com.example.spendwise.Utils
import com.patrykandpatrick.vico.core.cartesian.data.LineCartesianLayerModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class StatsViewModel(val dao: ExpenseDao): ViewModel() {
    val entries = dao.getAllExpensesByDate().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun getEntriesForChart(entries: List<ExpenseSummary>): List<LineCartesianLayerModel.Entry>{
        return entries.map { expense->
            val formattedDate = Utils.getMillisFromDate(expense.date)
            LineCartesianLayerModel.Entry(x=formattedDate.toFloat(), y= expense.total_amount.toFloat())
        }
    }


}

class StatsViewModelFactory(private  val context: Context): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(StatsViewModel::class.java)){
            val dao = ExpenseDatabase.getDatabase(context).expenseDAO()
            @Suppress("UNCHECKED_CAST")
            return  StatsViewModel(dao) as T
        }
        throw  IllegalArgumentException("Unkown ViewModel class")
    }
}