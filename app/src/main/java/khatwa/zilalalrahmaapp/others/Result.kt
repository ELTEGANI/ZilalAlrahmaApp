package khatwa.zilalalrahmaapp.others

data class Result<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T>  success(data: T): Result<T> {
            return Result(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String): Result<T> {
            return Result(Status.ERROR, null, msg)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR
}






/*
sealed class Result {
    data class Success(val data: Any) : Result()
    data class Error(val error: Exception) : Result()
}*/
