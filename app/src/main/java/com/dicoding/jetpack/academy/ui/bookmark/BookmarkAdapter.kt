package com.dicoding.jetpack.academy.ui.bookmark

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.jetpack.academy.R
import com.dicoding.jetpack.academy.data.CourseEntity
import com.dicoding.jetpack.academy.databinding.ItemsBookmarkBinding
import com.dicoding.jetpack.academy.ui.detail.DetailCourseActivity

class BookmarkAdapter(private var callback : BookmarkFragmentCallback): RecyclerView.Adapter<BookmarkAdapter.CourseViewHolder>() {
    inner class CourseViewHolder(private val binding: ItemsBookmarkBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(course: CourseEntity) {
            with(binding) {
                tvItemTitle.text = course.title
                tvItemDate.text = itemView.resources.getString(R.string.deadline_date,course.deadline)
                itemView.setOnClickListener{
                    val intent = Intent(itemView.context,DetailCourseActivity::class.java)
                    intent.putExtra(DetailCourseActivity.EXTRA_COURSE, course.courseId)
                    itemView.context.startActivity(intent)
                }
                imgShare.setOnClickListener { callback.onShareClick(course) }
                Glide.with(itemView.context)
                    .load(course.imagePath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error))
                    .into(imgPoster)
            }
        }
    }

    private val listCourses = ArrayList<CourseEntity>()

    fun setCourses(courses: List<CourseEntity>) {
        if (courses == null) return
        this.listCourses.clear()
        this.listCourses.addAll(courses)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CourseViewHolder {
        val view = ItemsBookmarkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookmarkAdapter.CourseViewHolder, position: Int) {
        val course = listCourses[position]
        holder.bind(course)
    }

    override fun getItemCount(): Int = listCourses.size
}

interface BookmarkFragmentCallback {
    fun onShareClick(course: CourseEntity)
}