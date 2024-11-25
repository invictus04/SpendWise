package com.example.spendwise.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.spendwise.Data.dao.ExpenseDao
import com.example.spendwise.Data.model.ExpenseEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [ExpenseEntity::class], version = 1)
abstract class ExpenseDatabase: RoomDatabase() {
    abstract fun expenseDAO() : ExpenseDao

    companion object{
        const val DATABASE_NAME = "expense_table"

        @JvmStatic
        fun getDatabase(context: Context): ExpenseDatabase{
            return Room.databaseBuilder(
                context,
                ExpenseDatabase::class.java,
                DATABASE_NAME
            ).addCallback(object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    InitBasicData(context)
                }

                fun InitBasicData(context: Context){
                    CoroutineScope(Dispatchers.IO).launch {
                        val dao = getDatabase(context).expenseDAO()
                        dao.insertExpenses(ExpenseEntity(1, "Salary", 5000.40,
                            System.currentTimeMillis().toString(),"Salary", "Income"))
                        dao.insertExpenses(ExpenseEntity(2, "PayPal", 2000.0,
                            System.currentTimeMillis().toString(),"PayPal", "Income"))
                        dao.insertExpenses(ExpenseEntity(3, "Netflix", 100.43,
                            System.currentTimeMillis().toString(),"Netflix", "Expense"))
                        dao.insertExpenses(ExpenseEntity(4, "Starbucks", 400.56,
                            System.currentTimeMillis().toString(),"Starbucks", "Expense"))
                    }
                }

            }).build()
        }
    }
}