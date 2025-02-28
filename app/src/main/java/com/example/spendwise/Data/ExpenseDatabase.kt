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
        private const val DATABASE_NAME = "expense_table"

        @JvmStatic
        fun getDatabase(context: Context): ExpenseDatabase{
            return Room.databaseBuilder(
                context,
                ExpenseDatabase::class.java,
                DATABASE_NAME
            ).addCallback(object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                }

            }).build()
        }
    }
}