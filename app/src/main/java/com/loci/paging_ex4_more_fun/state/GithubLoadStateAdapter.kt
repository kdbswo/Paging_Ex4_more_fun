package com.loci.paging_ex4_more_fun.state

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.loci.paging_ex4_more_fun.databinding.LoadStateItemBinding

class GithubLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<GithubLoadStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): GithubLoadStateViewHolder {
        val view = LoadStateItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GithubLoadStateViewHolder(view, retry)
    }


    override fun onBindViewHolder(holder: GithubLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }
}
