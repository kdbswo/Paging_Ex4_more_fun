package com.loci.paging_ex4_more_fun.data

import com.google.gson.annotations.SerializedName

data class Items (
    @SerializedName("id") val id : Long,
    @SerializedName("name") val name : String,
    @SerializedName("html_url") val url : String
)