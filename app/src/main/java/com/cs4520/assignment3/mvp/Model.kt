package com.cs4520.assignment3.mvp

import com.cs4520.assignment3.mvp.Contract.Model.Error
import com.cs4520.assignment3.mvp.Contract.Model.ModelResult
import com.cs4520.assignment3.mvp.Contract.Model.Success

class Model : Contract.Model {
    private var firstNumber: Double? = null
    private var secondNumber: Double? = null
    override fun setFirstNumber(value: Double) {
        firstNumber = value
    }

    override fun setSecondNumber(value: Double) {
        secondNumber = value
    }

    override fun add(): ModelResult<Double> {
        return combineNumbers { a, b -> a + b }
    }

    override fun subtract(): ModelResult<Double> {
        return combineNumbers { a, b -> a - b }
    }

    override fun multiply(): ModelResult<Double> {
        return combineNumbers { a, b -> a * b }
    }

    override fun divide(): ModelResult<Double> {
        return combineNumbers { a, b -> a / b }
    }

    override fun reset() {
        firstNumber = null
        secondNumber = null
    }

    private fun combineNumbers(combiner: (Double, Double) -> Double): ModelResult<Double> {
        if (firstNumber == null) {
            return Error("First number is missing")
        }

        if (secondNumber == null) {
            return Error("Second number is missing")
        }

        return Success(combiner(firstNumber!!, secondNumber!!))
    }
}

