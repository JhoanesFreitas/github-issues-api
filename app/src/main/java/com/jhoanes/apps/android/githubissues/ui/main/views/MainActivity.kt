package com.jhoanes.apps.android.githubissues.ui.main.views

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Parcelable
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jhoanes.apps.android.githubissues.R
import com.jhoanes.apps.android.githubissues.constants.Constants.Companion.ISSUE_MODEL
import com.jhoanes.apps.android.githubissues.models.IssueModel
import com.jhoanes.apps.android.githubissues.objects.ApiCallbackImpl
import com.jhoanes.apps.android.githubissues.services.ControllerService
import com.jhoanes.apps.android.githubissues.services.ViewCallback
import com.jhoanes.apps.android.githubissues.ui.main.adapters.IssueAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_currently_unavailable.*
import kotlinx.android.synthetic.main.progress_layout.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import kotlin.reflect.KClass

class MainActivity : AppCompatActivity(), ViewCallback<IssueModel> {

    private val mRecyclerView by lazy { recycler_view }
    private val mAdapter by inject<IssueAdapter> { parametersOf(this) }
    private val mControllerService by inject<ControllerService>()
    private val mHandler = Handler(Looper.getMainLooper())
    private val mProgressBarCentral by lazy { progress_circular_central }
    private val mUnavailable by lazy { unavailable_tv }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
        mRecyclerView.setHasFixedSize(true)

        hideProgressCentral()
        hideUnavailable()

        reload()

        ApiCallbackImpl.callback = this
    }

    override fun onRestart() {
        super.onRestart()
        hideProgressCentral()
    }

    override fun onStart() {
        super.onStart()

        if (mAdapter.issues.isEmpty())
            mControllerService.getIssues(ApiCallbackImpl)
    }

    override fun result(t: IssueModel) {

    }

    override fun result(t: List<IssueModel>) {
        mAdapter.replaceAll(t.toMutableList())
        hideUnavailable()
    }

    override fun error() {
        showUnavailable()
    }

    override fun showProgressCentral() {
        mHandler.post {
            mProgressBarCentral.visibility = VISIBLE
        }
    }

    override fun hideProgressCentral() {
        mHandler.post {
            mProgressBarCentral.visibility = GONE
        }
    }

    private fun showUnavailable() {
        mUnavailable.visibility = VISIBLE
    }

    private fun hideUnavailable() {
        mUnavailable.visibility = GONE
    }

    private fun reload() {
        mUnavailable.setOnClickListener {
            mControllerService.getIssues(ApiCallbackImpl)
        }
    }

    override fun startActivity(t: IssueModel, activity: KClass<out Activity>) {
        val intent = Intent(this, activity.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.putExtra(ISSUE_MODEL, t as Parcelable)
        startActivity(intent)
    }
}
