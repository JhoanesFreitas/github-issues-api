package com.jhoanes.apps.android.githubissues

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.core.KoinComponent

class MainActivity : AppCompatActivity(), KoinComponent {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}
