package com.paging.ex4

import com.paging.ex4.RemoteDoggoImageAdapter


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paging.R
import com.paging.ex1.PassengersLoadStateAdapter
import com.paging.ex4.viewmodel.RemoteViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class Ex4Activity : AppCompatActivity() {

    lateinit var rvDoggoRemote: RecyclerView
    lateinit var remoteViewModel: RemoteViewModel
    lateinit var adapter: RemoteDoggoImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ex4)

        initMembers()
        setUpViews()
        fetchDoggoImages()
    }


    private fun fetchDoggoImages() {
        lifecycleScope.launch {
            remoteViewModel.fetchDoggoImages().distinctUntilChanged().collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun initMembers() {
        remoteViewModel = defaultViewModelProviderFactory.create(RemoteViewModel::class.java)
        adapter = RemoteDoggoImageAdapter()
    }

    private fun setUpViews() {
        rvDoggoRemote = findViewById(R.id.rvDoggoRemote)
        val gl = GridLayoutManager(this, 2)
        rvDoggoRemote.layoutManager = gl
        //rvDoggoRemote.adapter = adapter
        /*rvDoggoRemote.adapter = adapter.withLoadStateHeaderAndFooter(
            header = PassengersLoadStateAdapter { adapter.retry() },
            footer = PassengersLoadStateAdapter { adapter.retry() }
        )*/
        val footerAdapter = PassengersLoadStateAdapter{ adapter.retry() }
        val headerAdapter = PassengersLoadStateAdapter{ adapter.retry() }
        rvDoggoRemote.adapter = adapter.withLoadStateHeaderAndFooter(header = headerAdapter, footer = footerAdapter)
        gl.spanSizeLookup =  object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == adapter.itemCount  && footerAdapter.itemCount > 0) {
                    2
                } else {
                    1
                }
            }
        }
    }
}