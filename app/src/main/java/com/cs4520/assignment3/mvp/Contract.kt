package com.cs4520.assignment3.mvp

interface Contract {
    interface Model {
        fun setFirstNumber(value: Double)
        fun setSecondNumber(value: Double)
        fun add(): ModelResult<Double>
        fun subtract(): ModelResult<Double>
        fun multiply(): ModelResult<Double>
        fun divide(): ModelResult<Double>
        fun reset()
        sealed interface ModelResult<T>
        data class Success<T>(val value: T) : ModelResult<T>
        data class Error<T>(val msg: String) : ModelResult<T>
    }

    interface View {
        fun showResult(result: Double)
        fun showToast(msg: String)
        fun clearInputs()
    }

    interface Presenter {
        fun firstNumberChanged(value: Double)
        fun secondNumberChanged(value: Double)
        fun onAddClick()
        fun onSubtractClick()
        fun onMultiplyClick()
        fun onDivideClick()
    }
}