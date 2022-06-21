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

package com.raywenderlich.markme.feature.presenter

import com.raywenderlich.markme.feature.FeatureContract
import com.raywenderlich.markme.model.Student
import com.raywenderlich.markme.repository.AppRepository
import com.raywenderlich.markme.utils.ClassSection

class FeaturePresenter(private var view: FeatureContract.View<Student>?) : FeatureContract.Presenter<Student> {

    private val repository: FeatureContract.Model<Student> by lazy { AppRepository }

    override fun onSave2PrefsClick(data: List<Student>?) {
        data?.let {
            repository.add2Prefs(data = data, callback = { msg ->
                view?.showToastMessage(msg)
            })
        }
    }

    override fun onSave2DbClick(data: List<Student>?) {
        data?.let {
            repository.add2Db(data = data, callback = { msg ->
                view?.showToastMessage(msg)
            })
        }
    }

    override fun loadPersistedData(data: List<Student>, featureType: ClassSection) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onViewDestroyed() {
        view = null
    }
}
