package com.jhoanes.apps.android.githubissues.ui.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.jhoanes.apps.android.githubissues.R
import com.jhoanes.apps.android.githubissues.constants.Constants.Companion.AVATAR
import kotlinx.android.synthetic.main.activity_show_image.*

class ShowImageActivity : AppCompatActivity() {

    private val avatarImage by lazy { avt_image }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_image)

        Glide.with(this)
            .load(intent.extras?.get(AVATAR))
            .into(avatarImage)
    }
}