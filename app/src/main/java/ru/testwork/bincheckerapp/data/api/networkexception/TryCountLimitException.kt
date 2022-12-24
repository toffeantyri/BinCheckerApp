package ru.testwork.bincheckerapp.data.api.networkexception

import okio.IOException

class TryCountLimitException(message: String) : IOException(message)