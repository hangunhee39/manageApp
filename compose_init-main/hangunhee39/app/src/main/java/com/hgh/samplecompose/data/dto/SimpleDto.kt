package com.hgh.samplecompose.data.dto

import com.hgh.samplecompose.domain.model.SimpleModel

data class SimpleDto(
    val id: Int,
) {
    fun toModel() = SimpleModel(
        id = this.id
    )
}
