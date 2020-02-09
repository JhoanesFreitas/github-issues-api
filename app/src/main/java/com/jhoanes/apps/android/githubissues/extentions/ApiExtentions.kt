package com.jhoanes.apps.android.githubissues.extentions

import com.jhoanes.apps.android.githubissues.utils.GsonUtil

fun GsonUtil.toIssues(obj: Any): Any =
    convertFromJson(obj)