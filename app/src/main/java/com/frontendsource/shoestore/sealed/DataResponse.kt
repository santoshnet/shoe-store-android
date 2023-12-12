package com.frontendsource.shoestore.sealed

sealed class DataResponse<T>(
    var data: T? = null,
    var error: com.frontendsource.shoestore.sealed.Error? = null,
) {
    class Success<T>(data: T) : DataResponse<T>(data = data)
    class Error<T>(error: com.frontendsource.shoestore.sealed.Error) : DataResponse<T>(error = error)
}