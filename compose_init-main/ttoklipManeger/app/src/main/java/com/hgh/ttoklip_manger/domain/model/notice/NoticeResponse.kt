package com.hgh.ttoklip_manger.domain.model.notice

data class NoticeResponse(
    val isFirst: Boolean,
    val isLast: Boolean,
    val notices: List<Notice>,
    val totalElements: Int,
    val totalPage: Int
)