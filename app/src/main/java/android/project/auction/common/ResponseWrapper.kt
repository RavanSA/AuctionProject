package android.project.auction.common

import com.google.gson.annotations.SerializedName

data class WrappedResponse<T>(
    var code: Int,
    @SerializedName("title") var title: String,
    @SerializedName("status") var status: Int,
    @SerializedName("traceId") var traceId: String? = null,
    @SerializedName("error") var error: String? = null
)


//data class WrappedResponse<T> (
//    var code: Int,
//    @SerializedName("message") var message : String,
//    @SerializedName("status") var status : Boolean,
//    @SerializedName("errors") var errors : List<String>? = null,
//    @SerializedName("data") var data : T? = null
//)