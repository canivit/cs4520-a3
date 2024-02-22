package com.cs4520.assignment3.mvp

import com.cs4520.assignment3.common.CalcModel
import com.cs4520.assignment3.common.Result
import com.cs4520.assignment3.common.Result.Error
import com.cs4520.assignment3.common.Result.Success

class Presenter(private val view: Contract.View) : Contract.Presenter {
    private val model = CalcModel()

    override fun onAddClick(firstInput: String, secondInput: String) {
        handleResult(model.add(firstInput, secondInput))
    }

    override fun onSubtractClick(firstInput: String, secondInput: String) {
        handleResult(model.subtract(firstInput, secondInput))
    }

    override fun onMultiplyClick(firstInput: String, secondInput: String) {
        handleResult(model.multiply(firstInput, secondInput))
    }

    override fun onDivideClick(firstInput: String, secondInput: String) {
        handleResult(model.divide(firstInput, secondInput))
    }

    private fun handleResult(result: Result<Double>) {
        when (result) {
            is Error -> view.showToast(result.msg)
            is Success -> {
                view.showResult(result.value)
                view.clearInputs()
            }
        }
    }
}