package com.thkim.calculator.extension

/*
 * Created by Taehyung Kim on 2021-07-05
 */

fun String.isNumber(): Boolean {
    return try {
        this.toBigInteger()
        true
    } catch (e: NumberFormatException) {
        e.printStackTrace()
        false
    }
}