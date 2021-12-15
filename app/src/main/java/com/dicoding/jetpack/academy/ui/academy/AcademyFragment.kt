package com.dicoding.jetpack.academy.ui.academy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.jetpack.academy.R
import com.dicoding.jetpack.academy.databinding.FragmentAcademyBinding
import com.dicoding.jetpack.academy.ui.academy.viewmodel.ViewModelFactory
import com.dicoding.jetpack.academy.utils.DataDummy

class AcademyFragment : Fragment() {

    private lateinit var binding : FragmentAcademyBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAcademyBinding.inflate(inflater,container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this,factory)[AcademyViewModel::class.java]
            val academyAdapter = AcademyAdapter()
            binding.progressBar.visibility = View.VISIBLE
            viewModel.getCourses().observe(viewLifecycleOwner, {
                binding.progressBar.visibility = View.GONE
                academyAdapter.setCourses(it)
                academyAdapter.notifyDataSetChanged()
            })
            with(binding.rvAcademy) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = academyAdapter
            }
        }
    }

}