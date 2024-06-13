package com.ptn.postotancredo.viewModel
import android.os.Build
import android.widget.DatePicker
import android.widget.TextView
import androidx.lifecycle.ViewModel
import java.util.Calendar
import java.util.TimeZone

class CalendarViewModel : ViewModel() {
    val calendar = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"))


    fun formatedDate(textView: TextView, datePicker: DatePicker){

        datePicker.minDate

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            datePicker.setOnDateChangedListener { _, year:Int, monthOfYear:Int, dayOfMonth:Int ->

                val cal = Calendar.getInstance()
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.YEAR, year)


                val diaDaSemana = cal.get(Calendar.DAY_OF_WEEK)
                val nomeDoDia: String = when (diaDaSemana) {
                    Calendar.SUNDAY -> "Domingo"
                    Calendar.MONDAY -> "Segunda-feira"
                    Calendar.TUESDAY -> "Terça-feira"
                    Calendar.WEDNESDAY -> "Quarta-feira"
                    Calendar.THURSDAY -> "Quinta-feira"
                    Calendar.FRIDAY -> "Sexta-feira"
                    Calendar.SATURDAY -> "Sábado"
                    else -> "Dia inválido"
                }

                textView.text = String.format("$dayOfMonth-$monthOfYear-$year")
            }
        }

    }
}