package com.hayatibahar.simpleandyummy.core.common

inline fun <I,O> I.mapTo(crossinline mapper:(I)->O):O{
    return mapper(this)
}