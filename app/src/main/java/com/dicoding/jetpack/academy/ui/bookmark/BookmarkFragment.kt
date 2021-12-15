package com.dicoding.jetpack.academy.ui.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.jetpack.academy.R
import com.dicoding.jetpack.academy.data.CourseEntity
import com.dicoding.jetpack.academy.databinding.FragmentBookmarkBinding
import com.dicoding.jetpack.academy.ui.academy.viewmodel.ViewModelFactory
import com.dicoding.jetpack.academy.utils.DataDummy


class BookmarkFragment : Fragment(),BookmarkFragmentCallback{

    private lateinit var binding: FragmentBookmarkBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this,factory)[BookmarkViewModel::class.java]
            val adapter = BookmarkAdapter(this)
            binding.progressBar.visibility = View.VISIBLE
            viewModel.getBookmarks().observe(viewLifecycleOwner, {
                binding.progressBar.visibility = View.GONE
                adapter.setCourses(it)
                adapter.notifyDataSetChanged()
            })
            with(binding.rvBookmark) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
    }
    override fun onShareClick(course: CourseEntity) {
        if (activity != null) {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder
                .from(requireActivity())
                .setType(mimeType)
                .setChooserTitle("Bagikan Aplikasi ini sekarang")
                .setText(resources.getString(R.string.share_text, course.title))
                .startChooser()
        }
    }

}