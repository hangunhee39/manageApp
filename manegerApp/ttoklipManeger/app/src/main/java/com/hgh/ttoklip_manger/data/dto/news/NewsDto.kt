package com.hgh.ttoklip_manger.data.dto.news

import com.hgh.ttoklip_manger.domain.model.news.News

data class NewsDto(
    val mainImageUrl: String,
    val newsletterId: Int,
    val title: String,
    val writtenTime: String
){
      fun toModel() = News(
          mainImageUrl = this.mainImageUrl,
          newsletterId = this.newsletterId,
          title = this.title,
          writtenTime = this.writtenTime
      )
}