package com.example.sampleapp.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations

fun <T, U> LiveData<T>.map(predicate: (T) -> U) = Transformations.map(this, predicate)

fun <T, U> LiveData<T>.switchMap(predicate: (T) -> LiveData<U>) = Transformations.switchMap(this, predicate)

fun <T> MutableLiveData<List<T>>.add(item: T) {
    val list = this.value as ArrayList
    list.add(item)
    this.value = list
}
