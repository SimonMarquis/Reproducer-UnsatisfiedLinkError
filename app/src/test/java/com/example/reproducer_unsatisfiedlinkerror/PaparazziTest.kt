package com.example.reproducer_unsatisfiedlinkerror

import android.graphics.Color
import android.widget.LinearLayout
import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test

//@org.junit.Ignore
class PaparazziTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @Test
    fun test() {
        val view = LinearLayout(paparazzi.context).apply {
            layoutParams = LinearLayout.LayoutParams(100, 100)
            setBackgroundColor(Color.RED)
        }
        paparazzi.snapshot(view)
    }
}
