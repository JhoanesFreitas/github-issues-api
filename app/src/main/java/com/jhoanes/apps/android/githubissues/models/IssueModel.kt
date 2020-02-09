package com.jhoanes.apps.android.githubissues.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.jhoanes.apps.android.githubissues.constants.Constants.Companion.STATUS
import com.jhoanes.apps.android.githubissues.constants.Constants.Companion.TITLE

class IssueModel : BaseModel() {

    @Expose
    @SerializedName(TITLE)
    lateinit var title: String

    @Expose
    @SerializedName(STATUS)
    lateinit var status: String
}