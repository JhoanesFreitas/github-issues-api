package com.jhoanes.apps.android.githubissues.extentions

import com.jhoanes.apps.android.githubissues.models.IssueModel
import com.jhoanes.apps.android.githubissues.utils.GsonUtil

fun GsonUtil.toIssues(obj: Any): IssueModel =
    convertFromJson(obj)