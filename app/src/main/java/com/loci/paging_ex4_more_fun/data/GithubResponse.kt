package com.loci.paging_ex4_more_fun.data

data class GithubResponse(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<Items>
)