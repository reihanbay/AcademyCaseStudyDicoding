package com.dicoding.jetpack.academy.ui.reader.list

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.jetpack.academy.R
import com.dicoding.jetpack.academy.data.ModuleEntity
import com.dicoding.jetpack.academy.databinding.FragmentModuleListBinding
import com.dicoding.jetpack.academy.ui.academy.viewmodel.ViewModelFactory
import com.dicoding.jetpack.academy.ui.reader.CourseReaderActivity
import com.dicoding.jetpack.academy.ui.reader.CourseReaderCallback
import com.dicoding.jetpack.academy.ui.reader.CourseReaderViewModel
import com.dicoding.jetpack.academy.utils.DataDummy

class ModuleListFragment : Fragment(), MyAdapterClickListener {

    companion object {
        val TAG: String = ModuleListFragment::class.java.simpleName

        fun newInstance(): ModuleListFragment = ModuleListFragment()
    }

    private lateinit var binding: FragmentModuleListBinding
    private lateinit var adapter: ModuleListAdapter
    private lateinit var courseReaderCallback: CourseReaderCallback
    private lateinit var viewModel : CourseReaderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentModuleListBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(requireActivity(),factory)[CourseReaderViewModel::class.java]
        adapter = ModuleListAdapter(this)

        binding.progressBar.visibility = View.VISIBLE
        viewModel.getModules().observe(viewLifecycleOwner,{
            binding.progressBar.visibility = View.GONE
            populateRecyclerView(it)
        })
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        courseReaderCallback = context as CourseReaderActivity
    }
    override fun onItemClicked(position: Int, moduleId: String) {
        courseReaderCallback.moveTo(position, moduleId)
        viewModel.setsSelectedModule(moduleId)
    }

    private fun populateRecyclerView(modules: List<ModuleEntity>) {
        with(binding) {
            progressBar.visibility = View.GONE
            adapter.setModules(modules)
            rvModule.layoutManager = LinearLayoutManager(context)
            rvModule.setHasFixedSize(true)
            rvModule.adapter = adapter
            val dividerItemDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            rvModule.addItemDecoration(dividerItemDecoration)
        }
    }
}