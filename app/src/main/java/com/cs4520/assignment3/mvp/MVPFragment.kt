package com.cs4520.assignment3.mvp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.cs4520.assignment3.R
import com.cs4520.assignment3.databinding.FragmentCalcBinding

class MVPFragment : Fragment(), Contract.View {
    private lateinit var binding: FragmentCalcBinding
    private lateinit var presenter: Presenter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCalcBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = Presenter(this)
        setBackgroundColor()
        setOnClickListeners()
    }

    private fun setBackgroundColor() {
        binding.root.setBackgroundColor(
            ContextCompat.getColor(
                binding.root.context,
                R.color.mvp_background
            )
        )
    }

    private fun setOnClickListeners() {
        listOf(
            Pair(binding.addButton) { firstInput: String, secondInput: String ->
                presenter.onAddClick(
                    firstInput,
                    secondInput
                )
            },
            Pair(binding.subtractButton) { firstInput: String, secondInput: String ->
                presenter.onSubtractClick(
                    firstInput,
                    secondInput
                )
            },
            Pair(binding.multiplyButton) { firstInput: String, secondInput: String ->
                presenter.onMultiplyClick(
                    firstInput,
                    secondInput
                )
            },
            Pair(binding.divideButton) { firstInput: String, secondInput: String ->
                presenter.onDivideClick(
                    firstInput,
                    secondInput
                )
            }
        ).forEach { pair ->
            val button = pair.first
            val operation = pair.second
            button.setOnClickListener {
                val firstInput = binding.firstNumberInput.text.toString()
                val secondInput = binding.secondNumberInput.text.toString()
                operation(firstInput, secondInput)
            }
        }
    }

    override fun showResult(result: Double) {
        binding.resultLabel.text = result.toString()
    }

    override fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }

    override fun clearInputs() {
        binding.firstNumberInput.text.clear()
        binding.secondNumberInput.text.clear()
    }
}