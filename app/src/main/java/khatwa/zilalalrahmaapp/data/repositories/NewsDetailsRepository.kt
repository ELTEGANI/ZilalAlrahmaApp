package khatwa.zilalalrahmaapp.data.repositories

import androidx.lifecycle.liveData
import khatwa.zilalalrahmaapp.data.ApiInterface
import khatwa.zilalalrahmaapp.others.ApiRequestResult
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class NewsDetailsRepository @Inject constructor(private val apiInterface: ApiInterface) {
    fun getNewsDetails(id: Int) = liveData(Dispatchers.IO) {
        emit(ApiRequestResult.loading(data = null))
        try {
            emit(ApiRequestResult.success(data = apiInterface.getNewsDetails(id)))
        } catch (exception: IOException) {
            emit(
                    ApiRequestResult.error(
                            data = null,
                            message = exception.message ?: "Error occurred!"
                    )
            )
        } catch (exception: HttpException) {
            ApiRequestResult.error(
                    data = null,
                    message = exception.message ?: "Error occurred!"
            )

        }

    }
}