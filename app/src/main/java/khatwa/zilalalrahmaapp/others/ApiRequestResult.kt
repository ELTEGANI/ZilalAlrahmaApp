package khatwa.zilalalrahmaapp.others

data class ApiRequestResult<T>(var status: Status, var data: T?, val message: String?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T): ApiRequestResult<T> {
            return ApiRequestResult(
                    Status.SUCCESS,
                    data,
                    null
            )
        }

        fun <T> error(message: String, data: T? = null): ApiRequestResult<T> {
            return ApiRequestResult(
                    Status.ERROR,
                    data,
                    message
            )
        }

        fun <T> loading(data: T? = null): ApiRequestResult<T> {
            return ApiRequestResult(
                    Status.LOADING,
                    data,
                    null
            )
        }
    }
}