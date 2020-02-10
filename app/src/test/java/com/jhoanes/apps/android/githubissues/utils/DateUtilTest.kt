package com.jhoanes.apps.android.githubissues.utils

import com.jhoanes.apps.android.githubissues.utils.DateUtil.Companion.convertStringToDate
import com.jhoanes.apps.android.githubissues.utils.DateUtil.Companion.getDate
import com.jhoanes.apps.android.githubissues.utils.DateUtil.Companion.getHour
import org.junit.Assert
import org.junit.Test

class DateUtilTest {

    @Test
    fun mustConvertStringInDate() {
        val date = convertStringToDate("2020-02-09T11:28:02Z")
        Assert.assertNotNull(date)
    }

    @Test
    fun mustConvertDateInStringOnDateMethod() {
        val date = getDate(convertStringToDate("2020-02-09T11:28:02Z"))
        Assert.assertNotNull(date)
        Assert.assertTrue(date.isNotEmpty())
    }

    @Test
    fun mustConvertDateInStringOnHourMethod() {
        val date = getHour(convertStringToDate("2020-02-09T11:28:02Z"))
        Assert.assertNotNull(date)
        Assert.assertTrue(date.isNotEmpty())
    }

    @Test
    fun mustEqualToDatePassed() {
        val date = getDate(convertStringToDate("2020-02-09T11:28:02Z"))
        assert(date == "09/02/2020")
    }

    @Test
    fun mustEqualToTimePassed() {
        val date = getHour(convertStringToDate("2020-02-09T11:28:02Z"))
        assert(date == "11:28:02")
    }

}