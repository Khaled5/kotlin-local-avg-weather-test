package com.easyinc.weatherapp.common.extentions

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import com.easyinc.weatherapp.R
import com.google.android.material.snackbar.Snackbar

fun <T> Iterable<T>.batch(chunkSize: Int) =
    withIndex().
    groupBy { it.index / chunkSize }.
    map { it.value.map { it.value } }

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun <T> androidLazy(initializer: () -> T): Lazy<T> =
    lazy(LazyThreadSafetyMode.NONE, initializer)

fun View.snack(message: String, length: Int = Snackbar.LENGTH_LONG) {
    val snacks = Snackbar.make(this,message,length)
    snacks.show()
}

fun Array<out String>.menuAdapter(context: Context): ArrayAdapter<String> {
    return ArrayAdapter(context,
        R.layout.dropdown_menu_popup_item,this)
}

fun EditText.isFieldEmpty(): Boolean{
    return this.text.toString().isEmpty()
}
