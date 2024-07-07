package nondas.pap.petcareapp.data.exception

class EmptyResponseException: Exception()

class ResponseException(val code: Int, val messages: List<String>):
        Exception("code: $code (${messages.joinToString()})")


class PayloadException(message: String): Exception(message)

class InvalidPayloadException: Exception()