package com.hgh.ttoklip_manger.domain.model.news

import com.hgh.ttoklip_manger.data.dto.news.NewsDto

data class CategoryResponses(
    val houseWork: List<News>,
    val recipe: List<News>,
    val safeLiving: List<News>,
    val welfarePolicy: List<News>
)