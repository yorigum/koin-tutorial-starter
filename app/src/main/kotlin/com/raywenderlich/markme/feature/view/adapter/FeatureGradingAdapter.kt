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
import com.raywenderlich.markme.databinding.CardFeatureGradingBinding
import com.raywenderlich.markme.model.Student

class FeatureGradingAdapter(val dataList: List<Student>?) :
    RecyclerView.Adapter<FeatureGradingAdapter.CardGradingeHolder>(), RwAdapter<Student> {

    override fun getItemCount(): Int = dataList?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardGradingeHolder {
        val itemBinding =
            CardFeatureGradingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardGradingeHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CardGradingeHolder, position: Int) {
        val student: Student = dataList?.get(position)!!
        holder.bind(student)
    }

    class CardGradingeHolder(private val itemBinding: CardFeatureGradingBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(student: Student) {
            itemBinding.apply {

                cardFeatGradingLabelName.text = student.name

                cardFeatGradingEdittextGrade.apply {
                    val valueGrade = if (student.grade != -1) student.grade.toString() else ""
                    setText(valueGrade)
                    setOnFocusChangeListener { _, hasFocus ->
                        if (!hasFocus) {
                            student.grade = text.toString().toInt()
                        }
                    }
                }
            }
        }
    }

    override fun getData(): List<Student>? = dataList

/*    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val labelName: TextView? = itemView.card_feat_grading__label__name
        val etGrade: TextView? = itemView.card_feat_grading__edittext__grade
    }

    override fun getItemCount(): Int = dataList?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewRow = LayoutInflater.from(parent.context).inflate(R.layout.card_feature_grading, parent, false)
        return ViewHolder(viewRow)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.labelName?.text = dataList?.let { it[position].name }
        holder.etGrade?.apply {
            dataList?.let { list ->
                val value = list[position].grade
                text = if (value != -1) value.toString() else ""
            }
            setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {
                    dataList?.get(position)?.grade = text.toString().toInt()
                }
            }
        }
    }

    override fun getData(): List<Student>? = dataList*/
}