package com.jhoanes.apps.android.githubissues.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.jhoanes.apps.android.githubissues.constants.Constants.Companion.BODY
import com.jhoanes.apps.android.githubissues.constants.Constants.Companion.CREATED_AT
import com.jhoanes.apps.android.githubissues.constants.Constants.Companion.HTML_URL
import com.jhoanes.apps.android.githubissues.constants.Constants.Companion.STATE
import com.jhoanes.apps.android.githubissues.constants.Constants.Companion.TITLE

class IssueModel : BaseModel() {

    @Expose
    @SerializedName(TITLE)
    var title: String? = null

    @Expose
    @SerializedName(STATE)
    var state: String? = null

    @Expose
    @SerializedName(BODY)
    var description: String? = null

    @Expose
    @SerializedName(CREATED_AT)
    var createdAt: String? = null

    @Expose
    @SerializedName(HTML_URL)
    var url: String? = null
}