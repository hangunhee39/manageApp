package com.hgh.ttoklip_manger.data.dto.news

import com.hgh.ttoklip_manger.domain.model.news.MainNewsResponse

data class MainNewsResponseDto(
    val categoryResponses: CategoryResponsesDto,
    val randomNews: List<Any>
) {
    fun toModel() = MainNewsResponse(
        categoryResponses = this.categoryResponses.toModel(),
        randomNews = listOf()
    )
}