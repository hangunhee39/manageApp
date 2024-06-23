package com.hgh.ttoklip_manger.data.dto.notice

import com.hgh.ttoklip_manger.domain.model.notice.NoticeDetail

data class NoticeDetailDto(
    val content: String,
    val noticeId: Int,
    val title: String,
    val createdAt: String
) {
    fun toModel() = NoticeDetail(
        content = this.content,
        noticeId = this.noticeId,
        title = this.title,
        createdAt = this.createdAt
    )
}