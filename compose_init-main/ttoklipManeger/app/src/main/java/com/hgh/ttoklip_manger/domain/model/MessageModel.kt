package com.hgh.ttoklip_manger.domain.model

data class MessageModel(
    val message: String
){
    fun toModel() = MessageModel(
        message= this.message
    )
}