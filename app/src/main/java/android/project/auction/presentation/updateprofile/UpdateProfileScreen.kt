package android.project.auction.presentation.updateprofile

import android.annotation.SuppressLint
import android.net.Uri
import android.project.auction.R
import android.project.auction.common.AuthResult
import android.project.auction.presentation.Screen
import android.project.auction.presentation.auth.AuthViewModel
import android.project.auction.presentation.ui.common.LoadingScreen
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UpdateProfileScreen(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel(),
    updateProfileViewModel: UpdateProfileViewModel = hiltViewModel()
) {

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val pictureLoading = updateProfileViewModel.state.value.pictureUploadingLoading

    if (pictureLoading) {
        LoadingScreen()
    }

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
                else -> {}
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Update Profile")
                },

                navigationIcon = {
                    IconButton(
                        onClick = {
                        }
                    ) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "")
                    }
                },

                actions = {
                    Text(
                        text = "Save",
                        fontSize = 20.sp,
                        modifier = Modifier.clickable {
                            updateProfileViewModel.onEvent(
                                UpdateProfileEvent.OnUpdateButtonClicked
                            )

                            coroutineScope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Updated Successfully"
                                )
                            }
                        }
                    )
                },

                backgroundColor = Color.White,
                elevation = AppBarDefaults.TopAppBarElevation
            )
        },
        content = {
            Spacer(modifier = Modifier.size(15.dp))
            UpdateProfileContent(
                navController
            )
        }
    )
}


@Composable
fun UpdateProfileContent(
    navController: NavController,
    updateProfileViewModel: UpdateProfileViewModel = hiltViewModel()
) {

    val state = updateProfileViewModel.state.value

    val imageUri = remember { mutableStateOf<Uri?>(null) }


    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            imageUri.value = uri
        }

    val scope = rememberCoroutineScope()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        Row(

        ) {
            Column(
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp))
                    .padding(
                        start = 20.dp, top = 5.dp,
                        end = 5.dp, bottom = 5.dp
                    )
                    .clickable {
                        galleryLauncher.launch("image/*")

                    },
                horizontalAlignment = Alignment.Start,
            ) {
                if (imageUri.value != null) {
                    Image(
                        painter = rememberImagePainter(imageUri.value),
                        contentScale = ContentScale.Crop,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(16.dp, 8.dp)
                            .size(70.dp)
                    )
                    updateProfileViewModel.onEvent(
                        UpdateProfileEvent.OnProfilePictureChange(
                            imageUri.value!!
                        )
                    )

                } else {
                    UserProfileImage(imageUrl = state.profilePicture)
                }
            }

            Column {
                val lightBlue = Color.LightGray
                val blue = Color.Black
                Text(
                    text = "Full Name",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, bottom = 4.dp, top = 10.dp),
                    textAlign = TextAlign.Start,
                    color = blue
                )
                TextField(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(start = 20.dp),
                    value = state.userName,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = lightBlue,
                        cursorColor = Color.Black,
                        disabledLabelColor = lightBlue,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    onValueChange = {
                        updateProfileViewModel.onEvent(
                            UpdateProfileEvent.OnFullNameChange(
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
        }

        Spacer(modifier = Modifier.size(20.dp))

        Column {
            val lightBlue = Color.LightGray
            val blue = Color.Black
            Text(
                text = "Title",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, bottom = 4.dp),
                textAlign = TextAlign.Start,
                color = blue
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(start = 30.dp),
                value = state.title,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = lightBlue,
                    cursorColor = Color.Black,
                    disabledLabelColor = lightBlue,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                onValueChange = {
                    updateProfileViewModel.onEvent(
                        UpdateProfileEvent.OnTitleChange(
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

        Spacer(modifier = Modifier.size(20.dp))

        Column {
            val lightBlue = Color.LightGray
            val blue = Color.Black
            Text(
                text = "Email",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, bottom = 4.dp),
                textAlign = TextAlign.Start,
                color = blue
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(start = 30.dp),
                value = state.email,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = lightBlue,
                    cursorColor = Color.Black,
                    disabledLabelColor = lightBlue,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                onValueChange = {
                    updateProfileViewModel.onEvent(
                        UpdateProfileEvent.OnEmailChange(
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

        Spacer(modifier = Modifier.size(20.dp))

        Column {
            val lightBlue = Color.LightGray
            val blue = Color.Black
            Text(
                text = "Phone Number",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, bottom = 10.dp),
                textAlign = TextAlign.Start,
                color = blue
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(start = 30.dp),
                value = state.phoneNumber,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = lightBlue,
                    cursorColor = Color.Black,
                    disabledLabelColor = lightBlue,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                onValueChange = {
                    updateProfileViewModel.onEvent(
                        UpdateProfileEvent.OnPhoneNumberChange(
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

        Spacer(modifier = Modifier.size(20.dp))

        Column {
            val lightBlue = Color.LightGray
            val blue = Color.Black
            Text(
                text = "Birthday",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, bottom = 4.dp),
                textAlign = TextAlign.Start,
                color = blue
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(start = 30.dp),
                value = state.birthday,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = lightBlue,
                    cursorColor = Color.Black,
                    disabledLabelColor = lightBlue,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                onValueChange = {
                    updateProfileViewModel.onEvent(
                        UpdateProfileEvent.OnBirthdayChange(
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

        Spacer(modifier = Modifier.size(20.dp))

        Column {
            val lightBlue = Color.LightGray
            val blue = Color.Black
            Text(
                text = "Country",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, bottom = 4.dp),
                textAlign = TextAlign.Start,
                color = blue
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(start = 30.dp),
                value = state.country,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = lightBlue,
                    cursorColor = Color.Black,
                    disabledLabelColor = lightBlue,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                onValueChange = {
                    updateProfileViewModel.onEvent(
                        UpdateProfileEvent.OnCountryChange(
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

        Spacer(modifier = Modifier.size(20.dp))

        Column {
            val lightBlue = Color.LightGray
            val blue = Color.Black
            Text(
                text = "City",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, bottom = 4.dp),
                textAlign = TextAlign.Start,
                color = blue
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(start = 30.dp),
                value = state.city,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = lightBlue,
                    cursorColor = Color.Black,
                    disabledLabelColor = lightBlue,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                onValueChange = {
                    updateProfileViewModel.onEvent(
                        UpdateProfileEvent.OnCityChange(
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


        Spacer(modifier = Modifier.size(20.dp))

        Column {
            val lightBlue = Color.LightGray
            val blue = Color.Black
            Text(
                text = "Post Code",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, bottom = 4.dp),
                textAlign = TextAlign.Start,
                color = blue
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(start = 30.dp),
                value = state.postCode,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = lightBlue,
                    cursorColor = Color.Black,
                    disabledLabelColor = lightBlue,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                onValueChange = {
                    updateProfileViewModel.onEvent(
                        UpdateProfileEvent.OnPostCodeChange(
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

        Spacer(modifier = Modifier.size(20.dp))

        Column {
            val lightBlue = Color.LightGray
            val blue = Color.Black
            Text(
                text = "Address",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, bottom = 4.dp),
                textAlign = TextAlign.Start,
                color = blue
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(start = 30.dp),
                value = state.address,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = lightBlue,
                    cursorColor = Color.Black,
                    disabledLabelColor = lightBlue,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                onValueChange = {
                    updateProfileViewModel.onEvent(
                        UpdateProfileEvent.OnAddressChange(
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

    }

}


@Composable
fun UserProfileImage(
    imageUrl: String
) {


    GlideImage(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
            .clip(RoundedCornerShape(10.dp)),
        imageModel = imageUrl,
        contentScale = ContentScale.FillBounds,
        placeHolder = ImageBitmap.imageResource(R.drawable.image_placeholder),
        error = ImageBitmap.imageResource(R.drawable.image_placeholder)
    )

}