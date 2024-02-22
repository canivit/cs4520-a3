package com.cs4520.assignment3.mvp

import com.cs4520.assignment3.common.Result

interface Contract {
    interface Model {
        fun add(firstInput: String, secondInput: String): Result<Double>
        fun subtract(firstInput: String, secondInput: String): Result<Double>
        fun multiply(firstInput: String, secondInput: String): Result<Double>
        fun divide(firstInput: String, secondInput: String): Result<Double>
    }

    interface View {
        fun showResult(result: Double)
        fun showToast(msg: String)
        fun clearInputs()
    }

    interface Presenter {
        fun onAddClick(firstInput: String, secondInput: String)
        fun onSubtractClick(firstInput: String, secondInput: String)
        fun onMultiplyClick(firstInput: String, secondInput: String)
        fun onDivideClick(firstInput: String, secondInput: String)
    }

}