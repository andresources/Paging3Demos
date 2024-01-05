package com.paging

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.paging.ex2.MovieActivity
import com.paging.ex3.Ex3Activity
import com.paging.ex4.Ex4Activity

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
    }

    fun clickMe(view: View) {
        //ex2,ex4 working only
        startActivity(Intent(this@TestActivity,MovieActivity::class.java))
    }
}