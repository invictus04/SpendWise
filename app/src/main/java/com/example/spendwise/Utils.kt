package com.example.spendwise

import java.text.SimpleDateFormat
import java.util.Locale

object Utils {
    fun formatDateToHumanReadableFormat(dateinMillis: Long): String {
        val dateFormatter = SimpleDateFormat("dd/MM/YYYY", Locale.getDefault())
        return dateFormatter.format(dateinMillis)
    }

    fun formatToDecimalValue(d : Double): String{
        return String.format("%. 2f",d)
    }
}