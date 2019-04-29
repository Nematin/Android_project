package ru.psu.studyit.data

class CResource<T>
private constructor(
    private val status                      : Status,
    val data                                : T?,
    val message                             : String?
)
{
    val isSuccess                           : Boolean
        get()                               = status === Status.SUCCESS && data != null

    val isLoading                           : Boolean
        get()                               = status === Status.LOADING

    val isLoaded                            : Boolean
        get()                               = status !== Status.LOADING

    companion object
    {
        fun <T> success(
            data                            : T
        )                                   : CResource<T>
        {
            return CResource(Status.SUCCESS, data, null)
        }

        fun <T> error(
            msg                             : String,
            data                            : T?
        )                                   : CResource<T>
        {
            return CResource(Status.ERROR, data, msg)
        }

        fun <T> loading(
            data                            : T?
        )                                   : CResource<T>
        {
            return CResource(Status.LOADING, data, null)
        }
    }

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }
}