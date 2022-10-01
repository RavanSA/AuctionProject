package android.project.auction.presentation.chat

import android.annotation.SuppressLint
import android.project.auction.common.Resource
import android.project.auction.domain.use_case.usecases.AuctionProjectUseCase
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.microsoft.signalr.HubConnection
import com.microsoft.signalr.HubConnectionBuilder
import com.microsoft.signalr.HubConnectionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Inject


@SuppressLint("CheckResult")
@HiltViewModel
class ChatViewModel @Inject constructor(
    private val useCase: AuctionProjectUseCase
) : ViewModel() {

    private var hubConnection: HubConnection
    private var logger: Logger

    private val _state = mutableStateOf(ChatState())
    val state: State<ChatState> = _state


    init {
        Log.d("SIGNALR", "TEST1")
        logger = LoggerFactory.getLogger(HubConnection::class.java)

        hubConnection = HubConnectionBuilder
            .create("http://auctionproject-001-site1.ftempurl.com/chathub")
            .build()

        hubConnection.start().blockingAwait()


//        hubConnection.invoke("JoinGroup",
//            state.value.itemDetail?.id)

        hubConnection.on(
            "SendMessageToUser",
            { message: String ->
                state.value.itemDetail?.id?.let { getAllMessages(it) }
                Log.d("REVEIVEMESSAGETEST222", message)
            },
            String::class.java
        )

//


//        hubConnection.

//        hubConnection.start().blockingAwait()
//
//        hubConnection.invoke("SetConversation", "B1C3C892-9DFF-4C48-1000-08DA9D8B586F")
//
//        Log.d("SIGNALR1" , "TEST" )
//        state.value.itemDetail?.let { getAllMessages(it.id) }
//
//        //TODO-PROBLEM
//        hubConnection.on("ReceiveMessage",
//            Action1 { messageRequest: MessageRequest ->
//                Log.d("messageRequest.message " , messageRequest.toString())
//                },
//            MessageRequest::class.java::class.java
//        )
//
//        Log.d("SIGNALR2" , "TEST2" )
//        hubConnection.start().doOnError( { Log.d("TESTERROR","SIGNALR") })

//        signalR = SignalRListener.getInstance()

//        getConnectState()
    }


    fun onEvent(event: ChatEvent) {
        when (event) {
            is ChatEvent.OnSendMessageButtonClicked -> {
                state.value.itemDetail?.let {
                    sendMessage(
                        message = state.value.message,
                        itemId = it.id,
                        itemOwnerId = it.userId
                    )
//                    sendMessage()
//                    getAllMessages(it.id)
                }
            }
            is ChatEvent.OnMessageInputChanged -> {
                _state.value = state.value.copy(
                    message = event.value
                )
            }
        }
    }


    private fun getAllMessages(itemId: String) {
        useCase.getAllMessageByItemId.invoke(itemId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    Log.d("MESSAGESLOADING", "TEST2")

                    _state.value = state.value.copy(
                        isMessagesLoading = true
                    )
                }
                is Resource.Error -> {
                    Log.d("MESSAGESERROR", "TEST")

                    _state.value = state.value.copy(
                        messageError = result.message ?: "Error Occured"
                    )
                }
                is Resource.Success -> {
                    Log.d("DATATEST", result.data.toString())
                    _state.value = state.value.copy(
                        messages = result.data ?: emptyList()
                    )
                }
            }
        }.launchIn(viewModelScope)
    }


    private fun sendMessage(message: String, itemId: String, itemOwnerId: String) {
        viewModelScope.launch {

            _state.value = state.value.copy(
                isMessagesLoading = true
            )

            useCase.sendMessageToUser.invoke(
                message = message,
                itemId = itemId,
                sellerId = itemOwnerId
            )

            _state.value = state.value.copy(
                isMessagesLoading = false
            )
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
        Log.d("11111", state.value.itemDetail?.userId.toString())
        Log.d("2222222222", state.value.message)


        hubConnection.invoke(
            "SendMessageToGroup",
            state.value.itemDetail?.userId,
            "81039061-9d09-4337-b19b-c214f729a7ce",
            state.value.message
        )
        hubConnection.on(
            "ReceiveMessage",
            { username: String, message: String ->
                println("$username offline")
                Log.d("RECIVEMESSAGETEST", username)
                Log.d("REVEIVEMESSAGETEST222", message)
            },
            String::class.java, String::class.java
        )

    }

//    private fun fetchMessage() {
//        val hubConnection = HubConnectionBuilder.create(Constants.BASE_URL+"/chathub").build()
//        hubConnection.start()
//
//        val itemId = state.value.itemDetail?.id
//        val userId = "testId"
//        val sellerId = state.value.itemDetail?.userId
//        val message = state.value.message
//
//        hubConnection.invoke("SetConversation", state.value.itemDetail?.id)
//
//        hubConnection.send(
//            "ReceiveMessage", MessageRequest(
//                itemId = itemId,
//                bidderId = userId,
//                sellerId = sellerId,
//                message = message
//            ) {
//
//            }, MessageRequest::class)
//    }


}