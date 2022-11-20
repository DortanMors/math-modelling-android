package com.ssau.sunsystem.util

val Any.tag: String
    get() = this::class.java.simpleName
