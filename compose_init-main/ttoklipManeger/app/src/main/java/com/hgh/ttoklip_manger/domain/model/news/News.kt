package com.hgh.ttoklip_manger.domain.model.news

data class News(
    val mainImageUrl: String,
    val newsletterId: Int,
    val title: String,
    val writtenTime: String
){
    constructor() : this("",0,"","")
}