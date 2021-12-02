package com.dicoding.jetpack.academy.ui.reader.content

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.dicoding.jetpack.academy.R
import com.dicoding.jetpack.academy.data.ContentEntity
import com.dicoding.jetpack.academy.data.ModuleEntity
import com.dicoding.jetpack.academy.databinding.FragmentModuleContentBinding
import com.dicoding.jetpack.academy.ui.academy.viewmodel.ViewModelFactory
import com.dicoding.jetpack.academy.ui.reader.CourseReaderViewModel

class ModuleContentFragment : Fragment() {

    companion object {
        val TAG: String = ModuleContentFragment::class.java.simpleName
        fun newInstance() : ModuleContentFragment = ModuleContentFragment()
    }


    private lateinit var binding: FragmentModuleContentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentModuleContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(requireActivity(),factory)[CourseReaderViewModel::class.java]
            val module = viewModel.getSelectedModule()
            populateWebView(module)
        }
    }

    private fun populateWebView(content: ModuleEntity){
        binding.webView.loadData(content.contentEntity?.content ?: "" , "text/html", "UTF-8")
    }

}