package com.cs4520.assignment3.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cs4520.assignment3.common.CalcModel

class CalcViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CalcViewModel(model = CalcModel()) as T
    }
}