package com.paging.ex2


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paging.R

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MovieActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    lateinit var rv:RecyclerView
    lateinit var progressDialog: ProgressBar
    private val adapter = MoviePagerAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        rv = findViewById(R.id.recyclerview)
        progressDialog= findViewById(R.id.progressDialog)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        adapter.addLoadStateListener { loadState ->
            // show empty list
            if (loadState.refresh is LoadState.Loading ||
                loadState.append is LoadState.Loading)
                progressDialog.isVisible = true
            else {
                progressDialog.isVisible = false
                // If we have an error, show a toast
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error ->  loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
                    Toast.makeText(this, it.error.toString(), Toast.LENGTH_LONG).show()
                }

            }
        }


        lifecycleScope.launch {
            viewModel.getMovieList().observe(this@MovieActivity) {
                it?.let {
                    adapter.submitData(lifecycle, it)

                }
            }
        }
        /*lifecycleScope.launch {
            viewModel.getMV().collectLatest { pagedData ->
                adapter.submitData(pagedData)
                Toast.makeText(this@MovieActivity,"Size : ${adapter.itemCount}",Toast.LENGTH_LONG).show()
            }
        }*/
    }
}