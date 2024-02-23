package com.cs4520.assignment3.mvvm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cs4520.assignment3.R
import com.cs4520.assignment3.common.Result
import com.cs4520.assignment3.databinding.FragmentCalcBinding

class MVVMFragment : Fragment() {
    private lateinit var viewModel: CalcViewModel
    private lateinit var binding: FragmentCalcBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCalcBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBackgroundColor()
        createViewModel()
        setOnClickListeners()
        observerViewModel()
    }

    private fun setBackgroundColor() {
        binding.root.setBackgroundColor(
            ContextCompat.getColor(
                binding.root.context,
                R.color.mvvm_background
            )
        )
    }

    private fun createViewModel() {
        viewModel = ViewModelProvider(this, CalcViewModelFactory())[CalcViewModel::class.java]
    }

    private fun setOnClickListeners() {
        listOf(
            Pair(binding.addButton) { firstInput: String, secondInput: String ->
                viewModel.add(
                    firstInput,
                    secondInput
                )
            },
            Pair(binding.subtractButton) { firstInput: String, secondInput: String ->
                viewModel.subtract(
                    firstInput,
                    secondInput
                )
            },
            Pair(binding.multiplyButton) { firstInput: String, secondInput: String ->
                viewModel.multiply(
                    firstInput,
                    secondInput
                )
            },
            Pair(binding.divideButton) { firstInput: String, secondInput: String ->
                viewModel.divide(
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

    private fun observerViewModel() {
        viewModel.result.observe(viewLifecycleOwner, Observer { result ->
            if (result == null) {
                return@Observer
            }

            when (result) {
                is Result.Error -> showToast(result.msg)
                is Result.Success -> {
                    binding.resultLabel.text = result.value.toString()
                    binding.firstNumberInput.text.clear()
                    binding.secondNumberInput.text.clear()
                }
            }
        })
    }

    private fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }
}