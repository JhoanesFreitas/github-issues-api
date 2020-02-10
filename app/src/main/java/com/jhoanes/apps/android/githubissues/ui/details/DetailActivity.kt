package com.jhoanes.apps.android.githubissues.ui.details

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.jhoanes.apps.android.githubissues.R
import com.jhoanes.apps.android.githubissues.constants.Constants.Companion.AVATAR
import com.jhoanes.apps.android.githubissues.constants.Constants.Companion.ISSUE_MODEL
import com.jhoanes.apps.android.githubissues.models.IssueModel
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity() {

    private val avatarImage by lazy { avatar_image }
    private var avatarUrl: CharSequence? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)

        val data = intent.getParcelableExtra<IssueModel>(ISSUE_MODEL)

        title = data.state
        avatarUrl = data.user?.avatar

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        Glide.with(this)
            .load(data.user?.avatar)
            .into(avatarImage)
    }

    fun onClick(v: View) {
        val intent = Intent(this, ShowImageActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.putExtra(AVATAR, avatarUrl)
        startActivity(intent)
    }
}
