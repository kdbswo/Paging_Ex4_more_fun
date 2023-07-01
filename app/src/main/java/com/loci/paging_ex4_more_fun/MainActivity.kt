package com.loci.paging_ex4_more_fun

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.loci.paging_ex4_more_fun.state.GithubLoadStateAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

// https://api.github.com/search/repositories?q=android&page=1&per_page=90
// https://api.github.com/search/repositories?q=abc&page=1&per_page=90

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var viewModelFactory: MainViewModelFactory

    lateinit var githubAdapter: GithubAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        githubAdapter = GithubAdapter()

        val str = intent.getStringExtra("inputText").toString()
        loadData(str)

//        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
//
//        val rv = findViewById<RecyclerView>(R.id.rv)
//        val githubAdapter = GithubAdapter()
//
//        rv.adapter = githubAdapter
//        rv.layoutManager = LinearLayoutManager(this)
//
//        lifecycleScope.launch {
//            viewModel.items.collect() {
//                githubAdapter.submitData(it)
//            }
//        }


//        val str = intent.getStringExtra("inputText").toString()
//        Log.d("main", str)
    }

    fun loadData(str: String) {

        viewModelFactory = MainViewModelFactory(str)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        val rv = findViewById<RecyclerView>(R.id.rv)

//        rv.adapter = githubAdapter
        rv.layoutManager = LinearLayoutManager(this)

        rv.adapter = githubAdapter.withLoadStateFooter(
            GithubLoadStateAdapter {
                githubAdapter.retry()
            }
        )

        lifecycleScope.launch {
            viewModel.items.collect() {
                githubAdapter.submitData(it)
            }
        }

    }

}














