package com.loci.paging_ex4_more_fun

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.loci.paging_ex4_more_fun.data.Items

class GithubAdapter : PagingDataAdapter<Items, GithubViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubViewHolder {
        return GithubViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: GithubViewHolder, position: Int) {
        val items = getItem(position)
        if (items != null) {
            holder.bind(items)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Items>() {
            override fun areItemsTheSame(oldItem: Items, newItem: Items): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Items, newItem: Items): Boolean {
                return oldItem == newItem
            }

        }
    }
}