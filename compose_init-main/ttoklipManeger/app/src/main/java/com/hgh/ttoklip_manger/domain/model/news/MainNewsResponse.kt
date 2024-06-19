package com.hgh.ttoklip_manger.domain.model.news

data class MainNewsResponse(
    val categoryResponses: CategoryResponses,
    val randomNews: List<Any>
)