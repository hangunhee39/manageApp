package com.hgh.ttoklip_manger.data.source.remote.api

import com.hgh.ttoklip_manger.data.dto.ApiResponseBody
import com.hgh.ttoklip_manger.data.dto.MessageDto
import com.hgh.ttoklip_manger.data.dto.notice.NoticeDetailDto
import com.hgh.ttoklip_manger.data.dto.notice.NoticeRequestDto
import com.hgh.ttoklip_manger.data.dto.notice.NoticeResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface NoticeService {

    @GET("/api/v1/notice")
    suspend fun getNoticePage(
        @Query("page") page: Int
    ): Response<ApiResponseBody<NoticeResponseDto>>

    @GET("/api/v1/notice/{noticeId}")
    suspend fun getNoticeDetail(
        @Path("noticeId") id: Int
    ): Response<ApiResponseBody<NoticeDetailDto>>

    @DELETE("/api/v1/notice/{noticeId}")
    suspend fun deleteNotice(
        @Path("noticeId") id: Int
    ): Response<ApiResponseBody<MessageDto>>

    @POST("/api/v1/notice/create")
    suspend fun postNotice(
        @Body request: NoticeRequestDto
    ): Response<ApiResponseBody<MessageDto>>

}