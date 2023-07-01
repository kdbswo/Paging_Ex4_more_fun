package com.loci.paging_ex4_more_fun

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModelFactory(private val str : String): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(str) as T
        }
        throw IllegalAccessException("Unknown ViewModel Class")

    }

}