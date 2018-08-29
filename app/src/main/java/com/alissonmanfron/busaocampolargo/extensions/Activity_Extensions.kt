package com.alissonmanfron.busaocampolargo.extensions

import android.app.Activity
import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.Toast
import org.joda.time.DateTime
import org.joda.time.Period
import org.joda.time.format.PeriodFormatterBuilder
import java.text.SimpleDateFormat
import java.util.*


// findViewById + setOnClickListener
fun AppCompatActivity.onClick(@IdRes viewId: Int, onClick: (v: android.view.View?) -> Unit) {
    val view = findViewById<View>(viewId)
    view.setOnClickListener { onClick(it) }
}

// Mostra um toast
fun Activity.toast(message: CharSequence, length: Int = Toast.LENGTH_SHORT) =
        Toast.makeText(this, message, length).show()

fun Activity.toast(@StringRes message: Int, length: Int = Toast.LENGTH_SHORT) =
        Toast.makeText(this, message, length).show()

fun Fragment.toast(@StringRes message: String, length: Int = Toast.LENGTH_SHORT) =
        Toast.makeText(context, message, length).show()


// Configura a Toolbar
fun AppCompatActivity.setupToolbar(@IdRes id: Int, title: String? = null, upNavigation: Boolean = false): ActionBar {
    val toolbar = findViewById<Toolbar>(id)
    setSupportActionBar(toolbar)
    if (title != null) {
        supportActionBar?.title = title;
    }
    supportActionBar?.setDisplayHomeAsUpEnabled(upNavigation)
    Log.d("carros", "Up nav config em $upNavigation $supportActionBar")
    return supportActionBar!!
}

// Adiciona o fragment no layout
fun AppCompatActivity.addFragment(@IdRes layoutId: Int, fragment: Fragment) {
    fragment.arguments = intent.extras
    val ft = supportFragmentManager.beginTransaction()
    ft.add(layoutId, fragment)
    ft.commit()
}

fun DateTime.between(date: DateTime) : String {
    val period = Period(this, date)

    val formatter = PeriodFormatterBuilder()
            .appendHours().appendSuffix(" hora, ", " horas, ")
            .appendMinutes().appendSuffix(" minuto, ", " minutos, ")
            .appendSeconds().appendSuffix(" segundo, ", " segundos, ")
            .printZeroNever()
            .toFormatter()

     return formatter.print(period)
}

fun String.toDate() : DateTime {
    return DateTime(Date(this.toLong()  * 1000))
}

fun DateTime.toDateString() : String {
    val formato = SimpleDateFormat("HH:mm")
    val dt = this.plusHours(2).toDate()
    return formato.format(dt)
}