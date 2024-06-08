package nondas.pap.petcareapp.data.network

import org.json.JSONObject
import retrofit2.Response

sealed class ApiResponse <T> {

    companion object {

        fun<T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                if (body == null || response.code() == 204) {
                    ApiEmptyResponse()
                }
                else {
                    ApiSuccessResponse(body)
                }
            }
            else {
                val errorMessages = try {
                    val errorBodyJson = response.errorBody()?.string()
                    val jObjError = JSONObject(errorBodyJson)
                    jObjError.getString("message").split("\n")
                }
                catch (ex: Exception) {
                    listOf(response.message())
                }

                ApiErrorResponse(response.code(), errorMessages)
            }
        }

        fun <T> create(errorCode: Int, error: Throwable): ApiErrorResponse<T> {
            return ApiErrorResponse(errorCode, listOf(error.message ?: "Unknown Error!"))
        }
    }

}


class ApiEmptyResponse<T> : ApiResponse<T>()

data class ApiErrorResponse<T>(val errorCode: Int, val errorMessages: List<String>) : ApiResponse<T>()

data class ApiSuccessResponse<T>(val body: T): ApiResponse<T>()

sealed class ApiException: Throwable() {
    object EmptyResponse : ApiException()

    data class ErrorResponse(val errorCode: Int, val errorMessages: List<String>): ApiException()
}



