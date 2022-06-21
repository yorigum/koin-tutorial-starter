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

package com.raywenderlich.markme.main.view.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.raywenderlich.markme.databinding.ActivityMainBinding
import com.raywenderlich.markme.feature.view.ui.FeatureActivity
import com.raywenderlich.markme.main.MainContract
import com.raywenderlich.markme.utils.ClassSection
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import timber.log.Timber

const val FEATURE_CATEGORY = "categoryName"

class MainActivity : AppCompatActivity(), MainContract.View {

    private val mainPresenter: MainContract.Presenter by inject()

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.activityMainCardviewAttendance.setOnClickListener {
            mainPresenter.onAttendanceOptionClick()
        }
       binding.activityMainCardviewGrading.setOnClickListener {
            mainPresenter.onGradingOptionClick()
        }
    }

    override fun navigateTo(section: ClassSection) {
        when (section) {
            ClassSection.ATTENDANCE -> {
                Timber.d("'Attendance' clicked")
                val intent = Intent(this, FeatureActivity::class.java).apply {
                    FEATURE_CATEGORY to ClassSection.ATTENDANCE
                }
                startActivity(intent)
            }
            ClassSection.GRADING -> {
                Timber.d("'Grading' clicked")
                val intent = Intent(this, FeatureActivity::class.java).apply {
                    FEATURE_CATEGORY to ClassSection.GRADING
                }
                startActivity(intent)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        mainPresenter.onViewDestroyed()
    }
}
