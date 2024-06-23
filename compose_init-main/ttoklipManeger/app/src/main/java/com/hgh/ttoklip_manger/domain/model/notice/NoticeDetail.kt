package com.hgh.ttoklip_manger.domain.model.notice

data class NoticeDetail(
    val content: String,
    val noticeId: Int,
    val title: String,
    val createdAt: String
){
    constructor() : this("",0,"","")
}