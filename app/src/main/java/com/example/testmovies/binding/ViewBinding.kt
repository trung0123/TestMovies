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
package com.example.testmovies.binding

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import co.lujun.androidtagview.TagContainerLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.testmovies.api.Api
import com.example.testmovies.extension.bindResource
import com.example.testmovies.extension.requestGlideListener
import com.example.testmovies.extension.visible
import com.example.testmovies.models.Keyword
import com.example.testmovies.models.Resource
import com.example.testmovies.models.entity.Movie
import com.example.testmovies.models.entity.Person
import com.example.testmovies.models.entity.Tv
import com.example.testmovies.models.network.PersonDetail
import com.example.testmovies.utils.KeywordListMapper

@BindingAdapter("visibilityByResource")
fun bindVisibilityByResource(view: View, resource: Resource<List<Any>>?) {
    view.bindResource(resource) {
        if (resource?.data?.isNotEmpty()!!) {
            view.visible()
        }
    }
}

@BindingAdapter("mapKeywordList")
fun bindMapKeywordList(view: TagContainerLayout, resource: Resource<List<Keyword>>?) {
    view.bindResource(resource) {
        view.tags = KeywordListMapper.mapToStringList(resource?.data!!)
        if (resource.data?.isNotEmpty()) {
            view.visible()
        }
    }
}

@BindingAdapter("biography")
fun bindBiography(view: TextView, resource: Resource<PersonDetail>?) {
    view.bindResource(resource) {
        view.text = resource?.data?.biography
    }
}

@BindingAdapter("nameTags")
fun bindTags(view: TagContainerLayout, resource: Resource<PersonDetail>?) {
    view.bindResource(resource) {
        view.tags = resource?.data?.also_known_as
        if (resource?.data?.also_known_as?.isNotEmpty()!!) {
            view.visible()
        }
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("bindReleaseDate")
fun bindReleaseDate(view: TextView, movie: Movie) {
    view.text = "Release Date : ${movie.release_date}"
}

@SuppressLint("SetTextI18n")
@BindingAdapter("bindAirDate")
fun bindAirDate(view: TextView, tv: Tv) {
    view.text = "First Air Date : ${tv.first_air_date}"
}

@BindingAdapter("bindBackDrop")
fun bindBackDrop(view: ImageView, movie: Movie) {
    if (movie.backdrop_path != null) {
        Glide.with(view.context).load(Api.getBackdropPath(movie.backdrop_path))
            .listener(view.requestGlideListener())
            .into(view)
    } else {
        Glide.with(view.context).load(Api.getBackdropPath(movie.poster_path!!))
            .listener(view.requestGlideListener())
            .into(view)
    }
}

@BindingAdapter("bindBackDrop")
fun bindBackDrop(view: ImageView, tv: Tv) {
    if (tv.backdrop_path != null) {
        Glide.with(view.context).load(Api.getBackdropPath(tv.backdrop_path))
            .listener(view.requestGlideListener())
            .into(view)
    } else {
        Glide.with(view.context).load(Api.getBackdropPath(tv.poster_path))
            .listener(view.requestGlideListener())
            .into(view)
    }
}

@BindingAdapter("bindBackDrop")
fun bindBackDrop(view: ImageView, person: Person) {
    if (person.profile_path != null) {
        Glide.with(view.context).load(Api.getBackdropPath(person.profile_path))
            .apply(RequestOptions().circleCrop())
            .into(view)
    }
}
