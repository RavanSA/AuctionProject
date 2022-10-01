package android.project.auction.presentation.chat

import android.annotation.SuppressLint
import android.project.auction.domain.model.item.ItemDetail
import android.project.auction.presentation.auth.sign_in.dpToSp
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ChatScreen(
    navController: NavController,
    itemDetail: ItemDetail
) {


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Messages")
                },

                navigationIcon = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "")
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
            Spacer(modifier = Modifier.size(15.dp))
            ChatContent(
                itemDetail
            )
        }
    )
}


@Composable
fun ChatContent(
    itemDetail: ItemDetail,
    chatViewModel: ChatViewModel = hiltViewModel()
) {


    val message = chatViewModel.state.value.message

//    LaunchedEffect(key1 = message) {
//
//    }


    val states = chatViewModel.state.value
    chatViewModel.state.value.itemDetail = itemDetail
    val userId = states.userId

    LazyColumn() {
        items(states.messages) { item ->

            Card(
                shape = RoundedCornerShape(30.dp),
            ) {
                if (item.bidderId == userId
                    || item.sellerId == userId
                ) {
                    Column(
                        modifier = Modifier
                            .height(20.dp)
                            .padding(16.dp)
                            .background(Color.Black),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = item.message,
                            color = Color.White,
                            fontSize = 15.sp
                        )
                    }
                } else {
                    Column(
                        modifier = Modifier
                            .height(20.dp)
                            .padding(16.dp)
                            .background(Color.White),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = item.message,
                            color = Color.Black,
                            fontSize = 15.sp
                        )
                    }
                }


            }

            Log.d("ITEMMESSAGE", item.toString())
        }

        item {
            Row() {

                Column {
                    val lightBlue = Color.LightGray
                    val blue = Color.Black

                    TextField(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .padding(start = 30.dp),
                        value = states.message,
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = lightBlue,
                            cursorColor = Color.Black,
                            disabledLabelColor = lightBlue,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        onValueChange = {
                            chatViewModel.onEvent(
                                ChatEvent.OnMessageInputChanged(
                                    it
                                )
                            )
                        },
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true,
                        trailingIcon = {
                        }
                    )
                }

                Button(
                    onClick = {
                        chatViewModel.onEvent(
                            ChatEvent.OnSendMessageButtonClicked
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Black,
                        contentColor = Color.White
                    ),
                ) {
                    Text(
                        text = "SEND", fontSize = dpToSp(20.dp),
                    )
                }
            }
        }
    }

}