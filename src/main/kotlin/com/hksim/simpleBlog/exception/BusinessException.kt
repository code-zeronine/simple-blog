package com.hksim.simpleBlog.exception

sealed class BusinessException : RuntimeException {

    private var errorCode: ErrorCode

    constructor(errorCode: ErrorCode) : super(errorCode.message) {
        this.errorCode = errorCode
    }

    constructor(message: String?, errorCode: ErrorCode) : super(message) {
        this.errorCode = errorCode
    }

}