package com.example.reproducer_unsatisfiedlinkerror

import android.os.Bundle
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

//@org.junit.Ignore
@RunWith(RobolectricTestRunner::class)
class RobolectricTest {
    @Test
    fun test() {
        val bundle = Bundle()
        bundle.putString("foo", "bar")
        assertEquals("bar", bundle.getString("foo"))
    }
}
