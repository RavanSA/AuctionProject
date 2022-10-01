package android.project.auction.domain.use_case

import android.project.auction.common.Constants
import android.project.auction.data.remote.dto.message.MessageRequest
import android.util.Log
import com.microsoft.signalr.Action1
import com.microsoft.signalr.HubConnection
import com.microsoft.signalr.HubConnectionBuilder
import com.microsoft.signalr.HubConnectionState
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class SignalRListener private constructor() {
    private var hubConnection: HubConnection
    private var logger: Logger


    init {
        Log.d("SIGNALR", "TEST1")
        logger = LoggerFactory.getLogger(HubConnection::class.java)

        hubConnection = HubConnectionBuilder.create("${Constants.BASE_URL}/chathub")
            .build()

        hubConnection.start()

        hubConnection.invoke("SetConversation", "B1C3C892-9DFF-4C48-1000-08DA9D8B586F")

        Log.d("SIGNALR1", "TEST")

        hubConnection.on(
            "ReceiveMessage",
            Action1 { messageRequest: MessageRequest ->
                Log.d("messageRequest.message ", messageRequest.toString())
            },
            MessageRequest::class.java::class.java
        )
        Log.d("SIGNALR2", "TEST2")
        hubConnection.start().doOnError({ Log.d("TESTERROR", "SIGNALR") })

    }

    private object Holder {
        val INSTANCE = SignalRListener()
    }

    companion object {
        @JvmStatic
        fun getInstance(): SignalRListener {
            Log.d("SIGNALR3", "TEST2")

            return Holder.INSTANCE
        }
    }

    fun stopHubConnection() {
        if (hubConnection.connectionState == HubConnectionState.CONNECTED) {
            hubConnection.stop()
        }
    }

    fun getConnectionState() {
        Log.d("SIGNALR4", hubConnection.connectionState.toString())
        Log.d(
            "SIGNAL5", hubConnection.connectionState
                .name
        )
        println(hubConnection.connectionState.toString())
    }

    fun log() {
        logger.info("Debug infor siganlR {}", hubConnection.connectionId)

        Log.d("Debug infor siganlR {}", hubConnection.connectionId)
    }

    fun sendMessage() {
        Log.d("SIGNALR10", "SENDMESSAGE")

        hubConnection.invoke(
            "SendMessageToUser",
            "B1C3C892-9DFF-4C48-1000-08DA9D8B586F",
            "a751182e-9f8b-48d1-b6fd-94de6510ed53",
            "81039061-9d09-4337-b19b-c214f729a7ce",
            "testmessage1"
        )
    }

}