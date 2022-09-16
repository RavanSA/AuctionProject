package android.project.auction.presentation.userprofile

import android.project.auction.R
import android.project.auction.common.AuthResult
import android.project.auction.data.local.entity.SellerOrBidder
import android.project.auction.domain.model.userinfo.UserInfo
import android.project.auction.presentation.Screen
import android.project.auction.presentation.auth.AuthViewModel
import android.project.auction.presentation.ui.common.bottomNav.BottomNav
import android.project.auction.presentation.userprofile.components.TabLayout
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.skydoves.landscapist.glide.GlideImage

@ExperimentalPagerApi
@Composable
fun UserProfileScreen(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel(),
    userProfileViewModel: UserProfileViewModel = hiltViewModel()
) {

    val itemLists = userProfileViewModel.state.value.auctionList
    val userInfo = userProfileViewModel.state.value.userInfo


    val context = LocalContext.current

    LaunchedEffect(viewModel, context) {
        viewModel.authResults.collect { authResult ->
            when (authResult) {
                is AuthResult.UnAuthorized -> {
                    navController.navigate(Screen.LoginScreen.route)
                }
                is AuthResult.UnknownError -> {
                    Toast.makeText(
                        context,
                        "UNKNOWN ERROR OCCURED",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.White,
                title = {
                    Text(text = "Profile", color = Color.Black)
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(
                            Screen.AuctionListScreen.route
                        )
                    }) {

                        Icon(Icons.Default.Menu, "Menu")
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
                itemLists,
                navController,
                userInfo
            )
        }
    )
}

@ExperimentalPagerApi
@Composable
fun UserProfileContent(
    itemLists: List<SellerOrBidder>,
    navController: NavController,
    userInfo: UserInfo?
) {


    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .height(maxHeight / 5)
                .align(Alignment.TopStart)
                .padding(5.dp)
                .clip(RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp))
                .shadow(2.dp),
            contentAlignment = Alignment.TopStart
        ) {

            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp))
                    .shadow(0.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier
                        .padding(15.dp)
                        .clip(RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp))
                        .clickable {
                            navController.navigate(
                                Screen.UpdateProfileScreen.route
                            )
                        },
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.Top
                ) {
                    UserProfileImage(imageUrl = userInfo?.profilePicture.toString())
                    Column(
                        modifier = Modifier.padding(start = 15.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                userInfo?.fullName ?: "Error",
                                color = Color.Black,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontSize = 15.sp,
                                modifier = Modifier.padding(top = 25.dp)
                            )
                            Spacer(modifier = Modifier.weight(1f))

                            Icon(
                                Icons.Default.Edit,
                                contentDescription = "...",
                                tint = Color.Black,
                                modifier = Modifier.padding(top = 25.dp)
                            )
                        }
                    }
                }
//
//                Spacer(modifier = Modifier.size(20.dp))
//
//                Column(
//                    modifier = Modifier
//                        .fillMaxWidth(0.8f)
//                        .clip(RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp))
//                        .background(Color.White)
//                        .shadow(0.dp),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth(1f)
//                            .background(Color.White)
//                            .clip(RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp))
//                            .shadow(0.dp),
//                        horizontalArrangement = Arrangement.Center
//                    ) {
//                        Column(
//                            modifier = Modifier.padding(10.dp),
//                            horizontalAlignment = Alignment.CenterHorizontally
//
//                        ) {
//                            Text(
//                                userInfo?.email ?: "Error",
//                                color = Color.Black,
//                                maxLines = 1,
//                                overflow = TextOverflow.Ellipsis,
//                                fontSize = 15.sp,
//                                modifier = Modifier.padding(0.dp)
//                            )
//
//                            Text(
//                                userInfo?.phoneNumber ?: "Phone Number not defined",
//                                color = Color.Gray,
//                                maxLines = 1,
//                                overflow = TextOverflow.Ellipsis,
//                                fontSize = 12.sp,
//                                modifier = Modifier.padding(0.dp)
//                            )
//                        }
//
//                        Spacer(modifier = Modifier.weight(1f))
//
//                        Button(
//                            modifier = Modifier
//                                .padding(10.dp),
//                            onClick = {},
//                            colors = ButtonDefaults.buttonColors(
//                                backgroundColor = Color.Black,
//                                contentColor = Color.White
//                            )
//                        ) {
//                            Text(text = "UPDATE")
//                        }
//                    }
//                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .align(Alignment.BottomStart)
                .height(maxHeight - maxHeight / 5)
        ) {

            TabLayout(navController)
        }
    }
}

//    }
//}

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

@Composable
fun UserProfileImage(
    modifier: Modifier = Modifier
        .size(80.dp)
        .clip(CircleShape)
        .border(2.dp, Color.Gray, CircleShape),
    imageUrl: String
) {


    GlideImage(
        modifier = modifier,
        imageModel = imageUrl,
        contentScale = ContentScale.FillBounds,
        placeHolder = ImageBitmap.imageResource(R.drawable.image_placeholder),
        error = ImageBitmap.imageResource(R.drawable.image_placeholder)
    )

}