package com.cs4520.assignment3.mvp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import com.cs4520.assignment3.databinding.FragmentMvpBinding

class MVPFragment : Fragment(), Contract.View {
    private lateinit var binding: FragmentMvpBinding
    private lateinit var presenter: Presenter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMvpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = Presenter(this)
        binding.firstNumberInput.doAfterTextChanged { text ->
            try {
                presenter.firstNumberChanged(
                    text.toString().toDouble()
                )
            } catch (ignored: NumberFormatException) {
            }
        }

        binding.secondNumberInput.doAfterTextChanged { text ->
            try {
                presenter.secondNumberChanged(
                    text.toString().toDouble()
                )
            } catch (ignored: NumberFormatException) {
            }
        }

        binding.addButton.setOnClickListener { presenter.onAddClick() }
        binding.subtractButton.setOnClickListener { presenter.onSubtractClick() }
        binding.multiplyButton.setOnClickListener { presenter.onMultiplyClick() }
        binding.divideButton.setOnClickListener { presenter.onDivideClick() }
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