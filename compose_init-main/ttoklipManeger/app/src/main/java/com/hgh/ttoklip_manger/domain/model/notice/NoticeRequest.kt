package com.hgh.ttoklip_manger.domain.model.notice

import com.hgh.ttoklip_manger.data.dto.notice.NoticeRequestDto

data class NoticeRequest(
    val content: String,
    val title: String
) {
    fun toDto() = NoticeRequestDto(
        content = this.content,
        title = this.title
    )
}