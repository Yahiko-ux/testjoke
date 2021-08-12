package com.testjoke.ui.jokes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.testjoke.R
import com.testjoke.databinding.FragmentJokesBinding
import com.testjoke.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JokesFragment : Fragment(R.layout.fragment_jokes) {
    private val binding: FragmentJokesBinding by viewBinding()
    private val viewModel: JokesViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.appCompatButton.setOnClickListener {
            viewModel.setCount(binding.countEditText.text.toString().toInt())
        }

        viewModel.jokes.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Success -> {
                    Toast.makeText(activity, response.data?.type, Toast.LENGTH_LONG)
                        .show()
                    binding.appCompatButton.text = response.data.toString()
                }

                is Resource.Error -> {
                    response.message?.let {
                        Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
                        binding.appCompatButton.text = it
                    }
                }

                is Resource.Loading -> {
                    Toast.makeText(activity, "123142", Toast.LENGTH_SHORT).show()
                }
            }
        })

    }
}