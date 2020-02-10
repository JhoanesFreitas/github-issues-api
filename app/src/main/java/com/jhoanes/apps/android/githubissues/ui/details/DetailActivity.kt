package com.jhoanes.apps.android.githubissues.ui.details

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.jhoanes.apps.android.githubissues.R
import com.jhoanes.apps.android.githubissues.constants.Constants.Companion.AVATAR
import com.jhoanes.apps.android.githubissues.constants.Constants.Companion.ISSUE_MODEL
import com.jhoanes.apps.android.githubissues.models.IssueModel
import com.jhoanes.apps.android.githubissues.utils.DateUtil
import com.jhoanes.apps.android.githubissues.utils.DateUtil.Companion.convertStringToDate
import com.jhoanes.apps.android.githubissues.utils.DateUtil.Companion.getDate
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*
import kotlinx.android.synthetic.main.progress_layout.*

class DetailActivity : AppCompatActivity() {

    private val mAvatarImage by lazy { avatar_image }
    private val mTitleTV by lazy { title_tv }
    private val mDescriptionTV by lazy { description }
    private val mCreatedAtTV by lazy { created_at }
    private var mAvatarUrl: CharSequence? = null
    private val mProgressBarCentral by lazy { progress_circular_central }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val data = intent.getParcelableExtra<IssueModel?>(ISSUE_MODEL)

        title = data?.title
        mAvatarUrl = data?.user?.avatar

        setData(data)
        hideProgressCentral()

        fab.setOnClickListener {
            showProgressCentral()
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(data?.url)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        hideProgressCentral()
    }

    @Suppress("UNUSED_PARAMETER")
    fun onClick(v: View) {
        showProgressCentral()
        val intent = Intent(this, ShowImageActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.putExtra(AVATAR, mAvatarUrl)
        startActivity(intent)
    }

    @SuppressLint("SetTextI18n")
    private fun setData(data: IssueModel?) {
        mTitleTV?.text = data?.title
        mDescriptionTV?.text = data?.description
        val date = data?.createdAt?.let { convertStringToDate(it) }
        mCreatedAtTV?.text =
            "${getDate(date)} " +
                    "${getString(R.string.at)} ${DateUtil.getHour(date)}"

        Glide.with(this)
            .load(data?.user?.avatar)
            .into(mAvatarImage)
    }

    private fun showProgressCentral() {
        mProgressBarCentral.visibility = View.VISIBLE
    }

    private fun hideProgressCentral() {
        mProgressBarCentral.visibility = View.GONE
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }
}
