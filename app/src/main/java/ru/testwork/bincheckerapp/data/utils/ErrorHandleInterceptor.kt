package ru.testwork.bincheckerapp.data.utils

import okhttp3.Interceptor
import okhttp3.Response
import ru.testwork.bincheckerapp.data.api.networkexception.NotFountException
import ru.testwork.bincheckerapp.data.api.networkexception.TryCountLimitException

class ErrorHandleInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        if (response.code == 404) {
            throw NotFountException(message = "Данные о коде отсутствуют")
        }
        if (response.code == 429) {
            throw TryCountLimitException("Превышено количество запросов, попробуйте снова через 1 минуту")
        }
        return response

    }

}