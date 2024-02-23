package com.cs4520.assignment3.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cs4520.assignment3.common.CalcModel
import com.cs4520.assignment3.common.Result

class CalcViewModel(private val model: CalcModel) : ViewModel() {
    private val _result: MutableLiveData<Result<Double>?> = MutableLiveData(null)
    val result: LiveData<Result<Double>?> = _result

    fun add(firstInput: String, secondInput: String) {
        _result.value = model.add(firstInput, secondInput)
    }

    fun subtract(firstInput: String, secondInput: String) {
        _result.value = model.subtract(firstInput, secondInput)
    }

    fun multiply(firstInput: String, secondInput: String) {
        _result.value = model.multiply(firstInput, secondInput)
    }

    fun divide(firstInput: String, secondInput: String) {
        _result.value = model.divide(firstInput, secondInput)
    }
}