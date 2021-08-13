package com.testjoke.ui.jokes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.testjoke.R
import com.testjoke.databinding.FragmentJokesBinding
import com.testjoke.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JokesFragment : Fragment(R.layout.fragment_jokes) {
    private val binding: FragmentJokesBinding by viewBinding()
    private val viewModel: JokesViewModel by viewModels()
    private val adapter = JokesAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()
        processReloadButtonTap()
    }

    private fun setupObservers() {
        viewModel.jokes.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    response.data?.let { adapter.setItems(it.value) }
                }

                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    response.message?.let {
                        Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
                    }
                }

                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun setupRecyclerView() {
        binding.jokesRecyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.jokesRecyclerView.adapter = adapter
    }

    private fun processReloadButtonTap() {
        binding.reloadButton.setOnClickListener {
            viewModel.setCount(binding.countEditText.text.toString().toInt())
        }
    }
}