package com.jhoanes.apps.android.githubissues.ui.main.views

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jhoanes.apps.android.githubissues.R
import com.jhoanes.apps.android.githubissues.models.IssueModel
import com.jhoanes.apps.android.githubissues.objects.ApiCallbackImpl
import com.jhoanes.apps.android.githubissues.services.ControllerService
import com.jhoanes.apps.android.githubissues.services.ViewCallback
import com.jhoanes.apps.android.githubissues.ui.main.adapters.IssueAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), ViewCallback<IssueModel> {

    private val mRecyclerView by lazy { recycler_view }
    private val mAdapter by inject<IssueAdapter>()
    private val mPresenter by inject<ControllerService>()
    private val mHandler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
        mRecyclerView.setHasFixedSize(true)

        ApiCallbackImpl.callback = this
    }

    override fun onStart() {
        super.onStart()

        mPresenter.getIssues(ApiCallbackImpl)
    }

    override fun result(t: IssueModel) {

    }

    override fun result(t: List<IssueModel>) {
        mHandler.post {
            mAdapter.replaceAll(t.toMutableList())
        }
    }

    override fun error() {

    }
}
