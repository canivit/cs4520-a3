package com.cs4520.assignment3.common

import com.cs4520.assignment3.mvp.Contract

class CalcModel : Contract.Model {
    override fun add(firstInput: String, secondInput: String): Result<Double> {
        return calculate(firstInput, secondInput) { a, b -> a + b }
    }

    override fun subtract(firstInput: String, secondInput: String): Result<Double> {
        return calculate(firstInput, secondInput) { a, b -> a - b }
    }

    override fun multiply(firstInput: String, secondInput: String): Result<Double> {
        return calculate(firstInput, secondInput) { a, b -> a * b }
    }

    override fun divide(firstInput: String, secondInput: String): Result<Double> {
        return calculate(firstInput, secondInput) { a, b -> a / b }
    }

    private fun calculate(
        firstInput: String,
        secondInput: String,
        operation: (Double, Double) -> Double
    ): Result<Double> {
        val num1 =
            if (firstInput.isEmpty()) {
                return Result.Error("First input is empty")
            } else {
                try {
                    firstInput.toDouble()
                } catch (ignored: NumberFormatException) {
                    return Result.Error("First input is not a valid number")
                }
            }

        val num2 =
            if (secondInput.isEmpty()) {
                return Result.Error("Second input is empty")
            } else {
                try {
                    secondInput.toDouble()
                } catch (ignored: NumberFormatException) {
                    return Result.Error("Second input is not a valid number")
                }
            }

        return Result.Success(operation(num1, num2))
    }
}