package com.jhoanes.apps.android.githubissues.utils

import org.junit.Test

class ParamsTest {

    @Test
    fun mustContainKey() {
        assert(Params.defaultHeader().containsKey("Content-Type"))
        assert(Params.defaultHeader().containsKey("Accept"))
    }
}