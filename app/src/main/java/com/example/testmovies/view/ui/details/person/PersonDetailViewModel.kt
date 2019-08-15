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
package com.example.testmovies.view.ui.details.person

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.testmovies.models.Resource
import com.example.testmovies.models.network.PersonDetail
import com.example.testmovies.repository.PeopleRepository
import com.example.testmovies.utils.AbsentLiveData
import timber.log.Timber
import javax.inject.Inject

class PersonDetailViewModel @Inject
constructor(private val repository: PeopleRepository) : ViewModel() {

    private val personIdLiveData: MutableLiveData<Int> = MutableLiveData()
    val personLiveData: LiveData<Resource<PersonDetail>>

    init {
        Timber.d("Injection : PersonDetailViewModel")

        personLiveData = personIdLiveData.switchMap {
            personIdLiveData.value?.let {
                repository.loadPersonDetail(it)
            } ?: AbsentLiveData.create()
        }
    }

    fun postPersonId(id: Int) = personIdLiveData.postValue(id)
}
