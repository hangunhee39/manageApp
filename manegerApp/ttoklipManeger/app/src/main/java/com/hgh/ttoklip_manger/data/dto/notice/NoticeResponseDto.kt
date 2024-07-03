package com.hgh.ttoklip_manger.data.dto.notice

import com.hgh.ttoklip_manger.domain.model.notice.NoticeResponse

data class NoticeResponseDto(
    val isFirst: Boolean,
    val isLast: Boolean,
    val notices: List<NoticeDto>,
    val totalElements: Int,
    val totalPage: Int
) {
    fun toModel() = NoticeResponse(
        isLast = this.isLast,
        isFirst = this.isFirst,
        totalElements = this.totalElements,
        totalPage = this.totalPage,
        notices = this.notices.map { it.toModel() }
    )
}