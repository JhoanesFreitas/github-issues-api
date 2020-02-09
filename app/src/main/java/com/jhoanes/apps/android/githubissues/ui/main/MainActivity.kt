package com.jhoanes.apps.android.githubissues.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jhoanes.apps.android.githubissues.R
import com.jhoanes.apps.android.githubissues.objects.ApiCallbackImpl
import com.jhoanes.apps.android.githubissues.services.ControllerService
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val mRecyclerView by lazy { recycler_view }
    private val mPresenter by inject<ControllerService>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mPresenter.getIssues(ApiCallbackImpl)
    }
}
