package com.savas.scweatherapp.util

import android.content.Context
import com.savas.scweatherapp.ErrorType
import com.savas.scweatherapp.R
import retrofit2.HttpException
import java.io.IOException

class ErrorHelper {

    companion object {
        fun getErrorType(exception: Exception): ErrorType {
            return when (exception) {
                is IOException -> ErrorType.NetworkError
                is HttpException -> {
                    val code = exception.code()
                    if (code in 500..599) {
                        ErrorType.ServerError(code)
                    } else {
                        ErrorType.HttpError
                    }
                }

                is IllegalStateException -> {
                    if (exception.message?.endsWith(
                            "response is null",
                            ignoreCase = true
                        ) == true
                    ) {
                        ErrorType.NoResourceError
                    } else {
                        ErrorType.UnknownError
                    }
                }

                else -> ErrorType.UnknownError
            }
        }

        fun getErrorMessage(context: Context, errorType: ErrorType): String {
            return when (errorType) {
                is ErrorType.NetworkError -> context.getString(R.string.network_error)
                is ErrorType.HttpError -> context.getString(R.string.http_error)
                is ErrorType.NoResourceError -> context.getString(R.string.resource_error)
                is ErrorType.ServerError -> context.getString(R.string.server_error)
                is ErrorType.UnknownError -> context.getString(R.string.unknown_error)
            }
        }
    }

}