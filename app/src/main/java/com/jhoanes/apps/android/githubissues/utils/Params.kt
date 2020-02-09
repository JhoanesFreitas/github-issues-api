package com.jhoanes.apps.android.githubissues.utils

class Params {
    companion object {

        fun defaultHeader(): Map<String, String> {
            val header = HashMap<String, String>()
            header["Content-Type"] = "application/json"
            header["Accept"] = "application/vnd.github.machine-man-preview"

            return header
        }

    }
}