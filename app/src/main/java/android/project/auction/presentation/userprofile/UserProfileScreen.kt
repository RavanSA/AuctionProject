package android.project.auction.presentation.userprofile

import android.project.auction.presentation.Screen
import android.project.auction.presentation.postitem.components.CategoryImage
import android.project.auction.presentation.ui.common.bottomNav.BottomNav
import android.project.auction.presentation.userprofile.components.TabLayout
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@Composable
fun UserProfileScreen(
    navController: NavController
) {


    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Black,
                title = {
                    Row(
                        modifier = Modifier.padding(25.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Profile", color = Color.White)

                    }
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(
                            Screen.AuctionListScreen.route
                        )
                    }) {

                        Icon(Icons.Default.ArrowBack, "Menu")
                    }
                },

                )
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .height(65.dp)
                    .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp))
                    .background(Color.White),
                cutoutShape = CircleShape,
                elevation = 22.dp,
                backgroundColor = Color.White
            ) {
                BottomNav(navController = navController)
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                onClick = {
                    navController.navigate(
                        Screen.PostItemScreen.route
                    )
                },
                contentColor = Color.White,
                backgroundColor = Color.Black
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add icon")
            }
        },
        content = {
            UserProfileContent(

            )
        }
    )
}

@ExperimentalPagerApi
@Composable
fun UserProfileContent() {


    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray)
                .height(maxHeight / 3)
                .align(Alignment.TopStart)
                .padding(15.dp),
            contentAlignment = Alignment.TopStart
        ) {

            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier
                        .padding(15.dp)
                        .clickable {

                        },
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.Top
                ) {
                    CategoryImage(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.Gray, CircleShape)
                    )
                    Column(
                        modifier = Modifier.padding(start = 15.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                "Test Name",
                                color = Color.White,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontSize = 20.sp,
                                modifier = Modifier.padding(top = 25.dp)
                            )
                            Spacer(modifier = Modifier.weight(1f))

                            Icon(
                                Icons.Default.Edit,
                                contentDescription = "...",
                                tint = Color.White,
                                modifier = Modifier.padding(top = 25.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.size(20.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.80f)
                        .clip(RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp))
                        .background(Color.White)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                    ) {
                        Column(
                            modifier = Modifier.padding(10.dp)
                        ) {
                            Text(
                                "example@gmail.com",
                                color = Color.Black,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontSize = 15.sp,
                                modifier = Modifier.padding(0.dp)
                            )

                            Text(
                                "+9011111111",
                                color = Color.Gray,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(0.dp)
                            )
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        Button(
                            modifier = Modifier
                                .padding(10.dp)
                                .background(Color.Black),
                            onClick = {}
                        ) {
                            Text(text = "UPDATE", color = Color.White)
                        }
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .align(Alignment.BottomStart)
                .height(maxHeight - maxHeight / 3)
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .background(Color.White)
            ) {

                Column(
                    modifier = Modifier
                        .background(Color.White)
                        .clip(shape = RoundedCornerShape(20.dp))

                ) {
                    Spacer(modifier = Modifier.size(20.dp))


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(20.dp))
                            .padding(10.dp)
                            .background(Color.Gray)
                            .clickable { }
                    ) {

                        Text(
                            "Notifications",
                            color = Color.White,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontSize = 15.sp,
                            modifier = Modifier.padding(10.dp)
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        IconButton(modifier = Modifier,
                            onClick = { }) {
                            Icon(
                                Icons.Filled.ArrowForward,
                                "contentDescription",
                                tint = Color.White
                            )
                        }

                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(20.dp))
                            .padding(10.dp)
                            .background(Color.Gray)
                            .clickable { }
                    ) {

                        Text(
                            "Privacy Policy",
                            color = Color.White,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontSize = 15.sp,
                            modifier = Modifier.padding(10.dp)

                        )

                        Spacer(modifier = Modifier.weight(1f))

                        IconButton(modifier = Modifier,
                            onClick = { }) {
                            Icon(
                                Icons.Filled.ArrowForward,
                                "contentDescription",
                                tint = Color.White
                            )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(20.dp))
                            .padding(10.dp)
                            .background(Color.Gray)
                            .clickable { }
                    ) {

                        Text(
                            "Auction Rules",
                            color = Color.White,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontSize = 15.sp,
                            modifier = Modifier.padding(10.dp)

                        )

                        Spacer(modifier = Modifier.weight(1f))

                        IconButton(modifier = Modifier,
                            onClick = { }) {
                            Icon(
                                Icons.Filled.ArrowForward,
                                "contentDescription",
                                tint = Color.White
                            )
                        }
                    }
                }
                TabLayout()
            }
        }
    }
}


@Composable
fun ProfileImage(
    modifier: Modifier = Modifier
        .size(64.dp)
        .clip(CircleShape)
        .border(2.dp, Color.Gray, CircleShape)
) {
    Image(
        painter = painterResource(android.project.auction.R.drawable.sample_avatar),
        contentDescription = "avatar",
        contentScale = ContentScale.Crop,
        modifier = modifier
    )

}