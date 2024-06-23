package com.hgh.ttoklip_manger.data.dto

import com.hgh.ttoklip_manger.domain.model.MessageModel

data class MessageDto(
    val message: String
){
    fun toModel() = MessageModel(
        message= this.message
    )
}