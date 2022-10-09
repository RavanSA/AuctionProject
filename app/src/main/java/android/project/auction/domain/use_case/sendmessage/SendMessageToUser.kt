package android.project.auction.domain.use_case.sendmessage

import android.content.SharedPreferences
import android.project.auction.data.remote.dto.message.MessageRequest
import android.project.auction.domain.repository.AuctionRepository
import android.util.Log
import retrofit2.HttpException
import javax.inject.Inject

class SendMessageToUser @Inject constructor(
    private val repository: AuctionRepository,
    private val preferences: SharedPreferences
) {

    suspend operator fun invoke(
        message: String,
        itemId: String,
        sellerId: String
    ) {
        try {

            val userID = preferences.getString("USERID", null) ?: "NOT REGISTERED USER"

            repository.sendMessageToUser(
                MessageRequest(
                    message = message,
                    itemId = itemId,
                    sellerId = sellerId,
                    bidderId = userID,
                    messageOwner = userID
                )
            )

        } catch (e: HttpException) {
            if (e.code() == 401) {
                Log.d("ERROR", e.toString())
            } else {
                Log.d("ERROR", e.toString())
            }
        } catch (e: Exception) {
            Log.d("ERROR", e.toString())
        }
    }

}