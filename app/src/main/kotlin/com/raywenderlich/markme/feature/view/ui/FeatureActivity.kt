/*
 * Copyright (c) 2019 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.markme.feature.view.ui

import android.app.Activity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.raywenderlich.markme.databinding.ActivityFeatureBinding
import com.raywenderlich.markme.feature.FeatureContract
import com.raywenderlich.markme.main.view.ui.FEATURE_CATEGORY
import com.raywenderlich.markme.model.Student
import com.raywenderlich.markme.model.studentList
import com.raywenderlich.markme.utils.ClassSection


class FeatureActivity : AppCompatActivity(), FeatureContract.View<Student> {

   private lateinit var binding: ActivityFeatureBinding
    private val classList = studentList.map { Student(uid = null, name = it, attendance = false, grade = -1) }
//    private val featurePresenter : FeatureContract.Presenter by inject { parametersOf(this) }

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            binding = ActivityFeatureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onResume() {
        super.onResume()

        val featureType = intent.getParcelableExtra<ClassSection>(FEATURE_CATEGORY)
        featureType?.let { feature ->
            binding.activityFeatureLabelCategory.text = feature.sectionName
            binding.activityFeatureLabelCategory.background = ContextCompat.getDrawable(this, feature.color)
            // Set up UI elements
            setupSaveButton(feature)
            setupRecyclerView(feature)
            // Load persisted data if any

        }
    }

   /* override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }*/

    override fun showToastMessage(msg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPersistedDataLoaded(data: List<Student>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun setupSaveButton(feature: ClassSection) {
        binding.activityFeatureBtnSave.text = if (feature == ClassSection.ATTENDANCE) "Save to prefs" else "Save to db"
        binding.activityFeatureBtnSave.setOnClickListener {
            when (feature) {
                ClassSection.ATTENDANCE -> {
                  //  @Suppress("UNCHECKED_CAST")
                  //  featurePresenter.onSave2PrefsClick((binding.activityFeatureRvList.adapter as? RwAdapter<Student>)?.getData())
                }
                ClassSection.GRADING -> {
                    hideSoftKeyboard()
                    binding.activityFeatureRvList.requestFocus()   // Get focus to update 'dataList'
              //      @Suppress("UNCHECKED_CAST")
                    //featurePresenter.onSave2DbClick((binding.activityFeatureRvList.adapter as? RwAdapter<Student>)?.getData())
                }
            }
        }
    }

    private fun setupRecyclerView(feature: ClassSection) {
        binding.activityFeatureRvList.apply {
            layoutManager = LinearLayoutManager(this@FeatureActivity, LinearLayout.VERTICAL, false)
//            adapter = when (feature) {
//                ClassSection.ATTENDANCE ->
//                   // FeatureAttendanceAdapter(dataList = classList)
//                ClassSection.GRADING ->
//                   // FeatureGradingAdapter(dataList = classList)
//            }
        }
    }

    private fun hideSoftKeyboard() {
        val inputMethodManager: InputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
}