package android.project.auction.presentation.auth.signup

import android.project.auction.R
import android.project.auction.common.AuthResult
import android.project.auction.presentation.Screen
import android.project.auction.presentation.auth.AuthUiEvent
import android.project.auction.presentation.auth.AuthViewModel
import android.project.auction.presentation.auth.sign_in.dpToSp
import android.project.auction.presentation.ui.common.LoadingScreen
import android.project.auction.presentation.ui.theme.TextWhite
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController


@Composable
fun SignUpPage(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {

    var showPassword by remember { mutableStateOf(false) }

    val state = viewModel.state
    val context = LocalContext.current

    LaunchedEffect(viewModel, context ){
        viewModel.authResults.collect { authResult ->
            when(authResult) {
                is AuthResult.Authorized -> {
                    navController.navigate(Screen.AuctionListScreen.route)
                }
                is AuthResult.UnAuthorized -> {
                    Toast.makeText(context,
                        "YOU ARE NOT AUTHORIZED",
                        Toast.LENGTH_LONG).show()
                }
                is AuthResult.UnknownError -> {
                    Toast.makeText(context,
                        "UNKNOWN ERROR OCCURED",
                        Toast.LENGTH_LONG).show()
                }
            }
        }
    }


    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(painterResource(R.drawable.login_image),"login_image")
        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.80f)
                .verticalScroll(rememberScrollState())
                .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp))
                .background(TextWhite)
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Sign Up",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = dpToSp(2.dp)
                ),
                fontSize = dpToSp(30.dp)
            )

                Spacer(modifier = Modifier.padding(20.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    OutlinedTextField(
                        value = state.value.signUpUsername,
                        onValueChange = {
                            viewModel.onEvent(AuthUiEvent.SignUpUsernameChanged(it))
                        },
                        label = { Text(text = "Email Address") },
                        placeholder = { Text(text = "Email Address") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.Black
                        )
                    )

                    Spacer(modifier = Modifier.padding(15.dp))

                    OutlinedTextField(
                        value = state.value.signUpFullName,
                        onValueChange = {
                            viewModel.onEvent(AuthUiEvent.SignUpFullNameChanged(it))
                        },
                        label = { Text(text = "Full Name") },
                        placeholder = { Text(text = "Full Name") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.Black
                        )
                    )

//                    OutlinedTextField(
//                        label = { Text(text = "Phone Number") },
//                        placeholder = { Text(text = "Phone Number") },
//                        singleLine = true,
//                        modifier = Modifier.fillMaxWidth(0.8f),
//                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
//                    )
                    Spacer(modifier = Modifier.padding(15.dp))

                    OutlinedTextField(
                        value = state.value.signUpPassword,
                        onValueChange = {
                            viewModel.onEvent(AuthUiEvent.SignUpPasswordChanged(it))
                        },
                        label = { Text(text = "Password") },
                        placeholder = { Text(text = "Password") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f),
                        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            val image = if (showPassword)
                                Icons.Filled.KeyboardArrowUp
                            else Icons.Filled.KeyboardArrowDown
                            val description = if (showPassword) "Hide password" else "Show password"

                            IconButton(onClick = {showPassword = !showPassword}){
                                Icon(imageVector  = image, description)
                            }
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.Black
                        )
                    )

                    Spacer(modifier = Modifier.padding(15.dp))

//                    OutlinedTextField(
//                        value = confirmPasswordValue.value,
//                        onValueChange = { confirmPasswordValue.value = it },
//                        label = { Text(text = "Confirm Password") },
//                        placeholder = { Text(text = "Confirm Password") },
//                        singleLine = true,
//                        modifier = Modifier.fillMaxWidth(0.8f),
//                        trailingIcon = {
//                            IconButton(onClick = {
//                                confirmPasswordVisibility.value = !confirmPasswordVisibility.value
//                            }) {
//                                Icon(
//                                    imageVector = vectorResource(id = R.drawable.password_eye),
//                                    tint = if (confirmPasswordVisibility.value) primaryColor else Color.Gray
//                                )
//                            }
//                        },
//                        visualTransformation = if (confirmPasswordVisibility.value) VisualTransformation.None
//                        else PasswordVisualTransformation()
//                    )


                    Spacer(modifier = Modifier.padding(10.dp))
                    Button(onClick = {
                        viewModel.onEvent(AuthUiEvent.SignUp)
                    }, modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(50.dp)) {
                        Text(text = "Sign Up", fontSize = dpToSp(20.dp))
                    }
                    Spacer(modifier = Modifier.padding(20.dp))
                    Text(
                        text = "Login Instead",
                        modifier = Modifier.clickable(
                            onClick = {
                                navController.navigate(Screen.LoginScreen.route)
                            })
                    )
                    Spacer(modifier = Modifier.padding(20.dp))
                }
            }
        }

    if (state.value.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            LoadingScreen()
        }
    }

    }
