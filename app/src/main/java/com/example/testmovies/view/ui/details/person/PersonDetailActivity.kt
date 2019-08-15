package com.example.testmovies.view.ui.details.person

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.lifecycle.ViewModelProvider
import com.example.testmovies.R
import com.example.testmovies.databinding.ActivityPersonDetailBinding
import com.example.testmovies.extension.activityBinding
import com.example.testmovies.extension.checkIsMaterialVersion
import com.example.testmovies.extension.viewModel
import com.example.testmovies.extension.vmDelegate
import com.example.testmovies.models.entity.Person
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.toolbar_default.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class PersonDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val vmDelegate by vmDelegate(PersonDetailViewModel::class)
    private val binding by activityBinding<ActivityPersonDetailBinding>(R.layout.activity_person_detail)

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        val vm = viewModel(vmDelegate, viewModelFactory)
        vm.postPersonId(getPersonFromIntent().id)
        with(binding) {
            lifecycleOwner = this@PersonDetailActivity
            viewModel = vm
            person = getPersonFromIntent()
        }
        initializeUI()
    }

    private fun initializeUI() {
        toolbar_home.setOnClickListener { onBackPressed() }
        toolbar_title.text = getPersonFromIntent().name
    }

    private fun getPersonFromIntent(): Person {
        return intent.getParcelableExtra(personId)!!
    }

    companion object {
        const val personId = "person"
        const val intent_requestCode = 1000

        fun startActivity(activity: Activity?, person: Person, view: View) {
            activity?.let {
                if (checkIsMaterialVersion()) {
                    val intent = Intent(activity, PersonDetailActivity::class.java)
                    ViewCompat.getTransitionName(view)?.let {
                        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, view, it)
                        intent.putExtra(personId, person)
                        activity.startActivityForResult(intent, intent_requestCode, options.toBundle())
                    }
                } else {
                    activity.startActivity<PersonDetailActivity>(personId to person)
                }
            }
        }
    }
}