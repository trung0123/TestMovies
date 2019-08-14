/*
 * The MIT License (MIT)
 *
 * Designed and developed by 2018 skydoves (Jaewoong Eum)
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
package com.example.testmovies.view.adapter

import android.view.View
import com.example.testmovies.R
import com.example.testmovies.models.Resource
import com.example.testmovies.models.entity.Tv
import com.example.testmovies.view.viewholder.TvListViewHolder
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import com.skydoves.baserecyclerviewadapter.SectionRow


class TvListAdapter(private val delegate: TvListViewHolder.Delegate)
  : BaseAdapter() {

  init {
    addSection(ArrayList<Tv>())
  }

  fun addTvList(resource: Resource<List<Tv>>) {
    resource.data?.let {
      sections()[0].addAll(it)
      notifyDataSetChanged()
    }
  }

  override fun layout(sectionRow: SectionRow): Int {
    return R.layout.item_poster
  }

  override fun viewHolder(layout: Int, view: View): BaseViewHolder {
    return TvListViewHolder(view, delegate)
  }
}
