package com.dicoding.jetpack.academy.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.dicoding.jetpack.academy.R
import com.dicoding.jetpack.academy.data.CourseEntity
import com.dicoding.jetpack.academy.databinding.ActivityDetailCourseBinding
import com.dicoding.jetpack.academy.databinding.ContentDetailCourseBinding
import com.dicoding.jetpack.academy.ui.academy.viewmodel.ViewModelFactory
import com.dicoding.jetpack.academy.ui.reader.CourseReaderActivity
import com.dicoding.jetpack.academy.utils.DataDummy

class DetailCourseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailCourseBinding

    companion object {
        const val EXTRA_COURSE = "extra_course"
    }
    private lateinit var detailContentBinding: ContentDetailCourseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailCourseBinding.inflate(layoutInflater)
        detailContentBinding = binding.detailContent
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val adapter = DetailCourseAdapter()

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this,factory)[DetailCourseViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val courseId = extras.getString(EXTRA_COURSE)
            if (courseId != null) {
                binding.progressBar.visibility = View.VISIBLE
                binding.content.visibility = View.INVISIBLE
                viewModel.setSelectedCourse(courseId)
                viewModel.getModules().observe(this, {
                    binding.progressBar.visibility = View.GONE
                    binding.content.visibility = View.VISIBLE

                    adapter.setModules(it)
                    adapter.notifyDataSetChanged()
                })
                viewModel.getCourse().observe(this, {
                    populateCourse(it)
                })
            }
        }

        with(detailContentBinding.rvModule) {
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(this@DetailCourseActivity)
            setHasFixedSize(true)
            this.adapter = adapter
            val dividerItemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }
    }
    private fun populateCourse(courseEntity: CourseEntity) {
        detailContentBinding.textTitle.text = courseEntity.title
        detailContentBinding.textDescription.text = courseEntity.desc
        detailContentBinding.textDate.text = resources.getString(R.string.deadline_date, courseEntity.deadline)

        Glide.with(this)
            .load(courseEntity.imagePath)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                .error(R.drawable.ic_error))
            .into(detailContentBinding.imagePoster)

        detailContentBinding.btnStart.setOnClickListener {
            val intent = Intent(this@DetailCourseActivity, CourseReaderActivity::class.java)
            intent.putExtra(CourseReaderActivity.EXTRA_COURSE_ID, courseEntity.courseId)
            startActivity(intent)
        }
    }


}