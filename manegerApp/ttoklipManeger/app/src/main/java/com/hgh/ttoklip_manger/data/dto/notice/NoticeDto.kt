package com.hgh.ttoklip_manger.data.dto.notice

import com.hgh.ttoklip_manger.domain.model.notice.Notice

data class NoticeDto(
    val content: String,
    val noticeId: Int,
    val title: String
) {
    fun toModel() = Notice(
        content = this.content,
        noticeId = this.noticeId,
        title = this.title
    )
}