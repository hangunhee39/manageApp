package com.hgh.ttoklip_manger.data.dto

import com.hgh.ttoklip_manger.domain.model.SimpleModel

data class SimpleDto(
    val id: Int,
) {
    fun toModel() = SimpleModel(
        id = this.id
    )
}
