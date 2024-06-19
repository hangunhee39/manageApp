package com.hgh.ttoklip_manger.data.dto

data class ApiResponseBody<T>(
    val time: String,
    val status: Int,
    val code: String,
    val message: String,
    val result: T
)