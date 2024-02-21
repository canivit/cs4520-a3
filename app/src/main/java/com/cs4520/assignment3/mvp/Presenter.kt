package com.cs4520.assignment3.mvp

import com.cs4520.assignment3.mvp.Contract.Model.Error
import com.cs4520.assignment3.mvp.Contract.Model.ModelResult
import com.cs4520.assignment3.mvp.Contract.Model.Success

class Presenter(private val view: Contract.View) : Contract.Presenter {
    private val model = Model()
    override fun firstNumberChanged(value: Double) {
        model.setFirstNumber(value)
    }

    override fun secondNumberChanged(value: Double) {
        model.setSecondNumber(value)
    }

    override fun onAddClick() {
        handleResult(model.add())
    }

    override fun onSubtractClick() {
        handleResult(model.subtract())
    }

    override fun onMultiplyClick() {
        handleResult(model.multiply())
    }

    override fun onDivideClick() {
        handleResult(model.divide())
    }

    private fun handleResult(result: ModelResult<Double>) {
        when (result) {
            is Error -> view.showToast(result.msg)
            is Success -> {
                view.showResult(result.value)
                view.clearInputs()
                model.reset()
            }
        }
    }
}