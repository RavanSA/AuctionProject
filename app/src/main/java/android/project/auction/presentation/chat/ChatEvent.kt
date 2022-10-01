package android.project.auction.presentation.chat

sealed class ChatEvent {
    object OnSendMessageButtonClicked : ChatEvent()
    data class OnMessageInputChanged(val value: String) : ChatEvent()
//    object OnSendMessageButtonClicked : ChatEvent()
}