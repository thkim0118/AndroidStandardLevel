package com.terry.architecture.mvc

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test


/*
 * Created by Taehyung Kim on 2021-08-18
 */
class MvcControllerTest {

    @Test
    fun saveResult() {
        val mvcController = MvcController()
        val first = "20"
        val second = "30"

        mvcController.saveResult(first, second) { isSuccess, resultData ->
            assertThat(isSuccess.toString(), `is`(true))
            assertThat(resultData.toString(), `is`("50\n"))
        }
    }
}