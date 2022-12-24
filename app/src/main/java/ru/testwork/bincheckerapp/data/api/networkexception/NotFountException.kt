package ru.testwork.bincheckerapp.data.api.networkexception

import okio.IOException

class NotFountException(message: String) : IOException(message)