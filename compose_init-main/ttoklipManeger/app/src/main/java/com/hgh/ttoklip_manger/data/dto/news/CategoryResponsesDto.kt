package com.hgh.ttoklip_manger.data.dto.news

import com.hgh.ttoklip_manger.domain.model.news.CategoryResponses

data class CategoryResponsesDto(
    val houseWork: List<NewsDto>,
    val recipe: List<NewsDto>,
    val safeLiving: List<NewsDto>,
    val welfarePolicy: List<NewsDto>
) {
    fun toModel() = CategoryResponses(
        houseWork = this.houseWork.map { it.toModel() },
        recipe = this.recipe.map { it.toModel() },
        safeLiving = this.safeLiving.map { it.toModel() },
        welfarePolicy = this.welfarePolicy.map { it.toModel() },
    )
}