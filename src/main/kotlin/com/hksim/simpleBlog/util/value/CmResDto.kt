package com.hksim.simpleBlog.util.value

import org.springframework.http.HttpStatus

data class CmResDto<T>(
    val resultCode: HttpStatus,
    val resultMsg: String,
    val data: T
)
