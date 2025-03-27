package com.hksim.simpleBlog.exception

import org.springframework.validation.BindingResult

class ErrorResponse(
    errorCode: ErrorCode, var errors: List<FieldError>
) {

    var code: String = errorCode.code
        private set

    var message: String = errorCode.message
        private set

    class FieldError private constructor(val field: String, val value: String, val reason: String?) {
        companion object {
            fun of(bindingResult: BindingResult): List<FieldError> {
                val fieldErrors = bindingResult.fieldErrors

                return fieldErrors.map { error ->
                    FieldError(error.field, error.rejectedValue.toString(), error.defaultMessage)
                }
            }
        }
    }

    companion object {

        fun of(errorCode: ErrorCode, bindingResult: BindingResult) =
            ErrorResponse(errorCode, FieldError.of(bindingResult))

    }

}

