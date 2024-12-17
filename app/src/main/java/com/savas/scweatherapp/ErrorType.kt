package com.savas.scweatherapp

sealed class ErrorType {
    data object NetworkError : ErrorType()
    data object HttpError : ErrorType()
    data class ServerError(val code: Int? = null) :
        ErrorType()

    data object NoResourceError : ErrorType()
    data object UnknownError : ErrorType()
}