package android.project.auction.presentation.auth.sign_in

import android.project.auction.R
import android.project.auction.common.AuthResult
import android.project.auction.presentation.Screen
import android.project.auction.presentation.auth.AuthUiEvent
import android.project.auction.presentation.auth.AuthViewModel
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController


@Composable
fun LoginPage(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
){

    val state = viewModel.state
    val context = LocalContext.current

    var showPassword by remember { mutableStateOf(false) }

    val focusRequester = remember { FocusRequester() }

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


    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
            , contentAlignment = Alignment.TopCenter) {

            Image(painterResource(R.drawable.login_image),"login_image")

        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.80f)
                .verticalScroll(rememberScrollState())
                .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp))
                .background(TextWhite)
                .padding(10.dp)
        ) {

            Text(
                text = "Sign In",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = dpToSp(2.dp)
                ),
                fontSize = dpToSp(30.dp)
            )

                Spacer(modifier = Modifier.padding(20.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    OutlinedTextField(
                        value = state.value.signInUsername,
                        onValueChange = {
                            viewModel.onEvent(AuthUiEvent.SignInUsernameChanged(it))
                        },
                        label = { Text(text = "Email Address") },
                        placeholder = { Text(text = "Email Address") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Black,
                            unfocusedBorderColor = Black)
                    )

                    OutlinedTextField(
                        value = state.value.signInPassword,
                        onValueChange = {
                            viewModel.onEvent(AuthUiEvent.SignInPasswordChanged(it))
                        },
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
                        label = { Text("Password") },
                        placeholder = { Text(text = "Password") },
                        singleLine = true,

                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .focusRequester(focusRequester = focusRequester),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Black,
                            unfocusedBorderColor = Black)
                    )

                    Spacer(modifier = Modifier.padding(10.dp))

                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.End,
                    ) {
                        Text(
                            text = "                                    Forgot Password?",
                            modifier = Modifier
                                .clickable(onClick = {
                                    navController.navigate(Screen.SignUpScreen.route)
                                })
                                .padding(start = 30.dp),
                            fontStyle = FontStyle.Italic
                        )
                        Spacer(modifier = Modifier.padding(20.dp))

                    }

                    Button(
                        onClick = {
                            viewModel.onEvent(AuthUiEvent.SignIn)
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(50.dp)
                    ) {
                        Text(text = "Sign In")
                    }

                    Spacer(modifier = Modifier.padding(20.dp))


                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Divider(color = Gray, thickness = 1.5.dp, modifier = Modifier
                            .fillMaxWidth(0.2f))

                        Text(
                            text = "      OR",
                            modifier = Modifier
                                .fillMaxWidth(0.2f),
                            fontSize = dpToSp(dp = 15.dp),
                            color = Color.Gray
                        )

                        Divider(color = Gray, thickness = 1.5.dp,modifier = Modifier
                            .fillMaxWidth(0.4f))
                    }

                    Column(
                        modifier = Modifier,
                        verticalArrangement = Arrangement.Center
                    ) {
                        
                        Spacer(modifier = Modifier.padding(15.dp))
                        Row(
                            modifier = Modifier,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "If you don't have an account,",
                                fontSize = dpToSp(dp = 15.dp)
                            )
                            Text(
                                text = " Register here",
                                modifier = Modifier.clickable(onClick = {
                                    navController.navigate(Screen.SignUpScreen.route)
                                }),
                                fontSize = dpToSp(dp = 15.dp),
                                color = Color.Blue
                            )
                        }
                    }
//                    Text(
//                        text = "Create An Account",
//                        modifier = Modifier.clickable(onClick = {
//                            navController.navigate(Screen.SignUpScreen.route)
//                        })
//                    )
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

@Composable
fun dpToSp(dp: Dp) = with(LocalDensity.current) { dp.toSp() }

