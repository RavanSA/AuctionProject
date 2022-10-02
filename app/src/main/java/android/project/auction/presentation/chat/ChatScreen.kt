package android.project.auction.presentation.chat

import android.annotation.SuppressLint
import android.project.auction.domain.model.item.ItemDetail
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ChatScreen(
    chatViewModel: ChatViewModel = hiltViewModel(),
    navController: NavController,
    itemDetail: ItemDetail
) {
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Messages")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        chatViewModel.onEvent(
                            ChatEvent.StopHubConnection
                        )
                        onBackPressedDispatcher?.onBackPressed()
                    }) {
                        Icon(Icons.Default.Clear, "Menu")
                    }
                },
                actions = {
                    Text(
                        text = "",
                        fontSize = 20.sp,
                        modifier = Modifier.clickable {}
                    )
                },

                backgroundColor = Color.White,
                elevation = AppBarDefaults.TopAppBarElevation
            )
        },
        content = {
            ChatContent(itemDetail)
        }
    )
}


@Composable
fun ChatContent(
    itemDetail: ItemDetail,
    chatViewModel: ChatViewModel = hiltViewModel()
) {

    var isMessageCalled: Boolean = false
    val states = chatViewModel.state.value
    chatViewModel.state.value.itemDetail = itemDetail
    val userId = states.userId

    if (states.messages.isEmpty()) {
        isMessageCalled = true
    }

    LaunchedEffect(key1 = isMessageCalled) {
        chatViewModel.getAllMessages(itemId = itemDetail.id)
    }



    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(states.messages) { item ->
                var isMessageMine = false

                if (item.bidderId == userId
                    || item.sellerId == userId
                ) {
                    isMessageMine = true
                }
                MessageItem(
                    isMessageMine = isMessageMine,
                    message = item.message
                )
            }
        }
        MessageTextAndButtonContent()
    }
}

@Composable
fun MessageItem(
    isMessageMine: Boolean,
    message: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalAlignment = when {
            isMessageMine -> Alignment.End
            else -> Alignment.Start
        }
    ) {
        Card(
            modifier = Modifier.widthIn(max = 340.dp),
            shape = cardShapeFor(isMessageMine),
            backgroundColor = when {
                isMessageMine -> Color.Black
                else -> Color.White
            },
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = message,
                color = when {
                    isMessageMine -> Color.White
                    else -> Color.Black
                },
            )
        }
    }
}

@Composable
fun MessageTextAndButtonContent(
    chatViewModel: ChatViewModel = hiltViewModel()
) {

    val states = chatViewModel.state.value
    Row {
        TextField(
            modifier = Modifier.weight(1f),
            value = states.message,
            onValueChange = {
                chatViewModel.onEvent(
                    ChatEvent.OnMessageInputChanged(
                        it
                    )
                )
            },
            )
        Button(
            modifier = Modifier.height(56.dp),
            onClick = {
                chatViewModel.onEvent(
                    ChatEvent.OnSendMessageButtonClicked
                )
                states.message = ""
            },
        ) {
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = "Send Message"
            )
        }
    }
}


@Composable
fun cardShapeFor(isMessageMine: Boolean): Shape {
    val roundedCorners = RoundedCornerShape(16.dp)

    return when {
        isMessageMine -> roundedCorners.copy(bottomEnd = CornerSize(0))
        else -> roundedCorners.copy(bottomStart = CornerSize(0))
    }
}



