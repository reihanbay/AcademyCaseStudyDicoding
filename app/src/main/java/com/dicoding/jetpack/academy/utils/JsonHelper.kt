package com.dicoding.jetpack.academy.utils

import android.content.Context
import com.dicoding.jetpack.academy.data.source.remote.response.ContentResponse
import com.dicoding.jetpack.academy.data.source.remote.response.CourseResponse
import com.dicoding.jetpack.academy.data.source.remote.response.ModuleResponse
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private val context: Context) {

    private fun parsingFileToString(fileName: String) : String? {
        return try {
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    fun loadCourses() : List<CourseResponse> {
        val list = ArrayList<CourseResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("CourseResponse.json").toString())
            val listArray = responseObject.getJSONArray("courses")
            for (i in 0 until listArray.length()) {
                val course = listArray.getJSONObject(i)
                val id = course.getString("id")
                var title = course.getString("title")
                val desc = course.getString("description")
                val date = course.getString("date")
                val imagePath = course.getString("imagePath")

                val courseResponse = CourseResponse(id,title,desc,date,imagePath)
                list.add(courseResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

    fun loadModule(courseId:String) : List<ModuleResponse> {
        val fileName = String.format("Module_%s.json", courseId)
        val list = ArrayList<ModuleResponse>()
        try {
            val result = parsingFileToString(fileName)
            if (result != null) {
                val responseObjet = JSONObject(result)
                val listArray = responseObjet.getJSONArray("modules")
                for (item in 0 until listArray.length()) {
                    val course = listArray.getJSONObject(item)
                    val moduleId = course.getString("moduleId")
                    val title = course.getString("title")
                    val position = course.getString("position")
                    val courseResponse = ModuleResponse(moduleId,courseId,title,position.toInt())
                    list.add(courseResponse)
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }

    fun loadContent(moduleId: String) : ContentResponse {
        val fileName = String.format("Content_%s", moduleId)
        var contentResponse : ContentResponse? = null
        try {
            val result = parsingFileToString(fileName)
            if (result != null) {
                val responseObject = JSONObject(result)
                val content = responseObject.getString("content")
                contentResponse = ContentResponse(moduleId,content)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return contentResponse as ContentResponse

    }
}