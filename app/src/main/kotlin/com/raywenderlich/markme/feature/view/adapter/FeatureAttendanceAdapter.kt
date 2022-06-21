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

package com.raywenderlich.markme.feature.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.raywenderlich.markme.databinding.CardFeatureAttendanceBinding
import com.raywenderlich.markme.model.Student

class FeatureAttendanceAdapter(private val dataList: List<Student>?) :
    RecyclerView.Adapter<FeatureAttendanceAdapter.CardAttedanceHolder>(), RwAdapter<Student> {


    override fun getItemCount(): Int = dataList?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardAttedanceHolder {
        val itemBinding =
            CardFeatureAttendanceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardAttedanceHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CardAttedanceHolder, position: Int) {
        val student: Student = dataList?.get(position)!!
        holder.bind(student)
    }

    class CardAttedanceHolder(private val itemBinding: CardFeatureAttendanceBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(student: Student) {
            itemBinding.apply {
                itemBinding.cardFeatAttendanceCheckboxStudent.text = student.name
                itemBinding.cardFeatAttendanceCheckboxStudent.isChecked = student.attendance

                val isChecked = itemBinding.cardFeatAttendanceCheckboxStudent.isChecked
                itemBinding.cardFeatAttendanceCheckboxStudent.setOnClickListener {
                    student.attendance = isChecked
                }
            }
        }
    }

    override fun getData(): List<Student>? = dataList

}