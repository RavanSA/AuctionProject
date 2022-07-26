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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
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
    var showRepeatedPassword by remember { mutableStateOf(false) }

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
                        label = { Text(text = "Email Address", color = Color.Black) },
                        placeholder = { Text(text = "Email Address", color = Color.Black) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.Black
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email
                        ),
                        isError = state.value.signUpUsernameError != null,
                    )
                    if (state.value.signUpUsernameError != null) {
                        Text(
                            text = state.value.signUpUsernameError ?: "",
                            color = MaterialTheme.colors.error,
                            modifier = Modifier.align(Alignment.End)
                        )
                    }

                    Spacer(modifier = Modifier.padding(15.dp))

                    OutlinedTextField(
                        value = state.value.signUpFullName,
                        onValueChange = {
                            viewModel.onEvent(AuthUiEvent.SignUpFullNameChanged(it))
                        },
                        label = { Text(text = "Full Name", color = Color.Black) },
                        placeholder = { Text(text = "Full Name", color = Color.Black) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.Black
                        )
                    )

                    Spacer(modifier = Modifier.padding(15.dp))

                    OutlinedTextField(
                        value = state.value.signUpPassword,
                        onValueChange = {
                            viewModel.onEvent(AuthUiEvent.SignUpPasswordChanged(it))
                        },
                        isError = state.value.signUpPasswordError != null,
                        label = { Text(text = "Password", color = Color.Black) },
                        placeholder = { Text(text = "Password", color = Color.Black) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f),
                        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            val image = if (showPassword)
                                Icons.Filled.Visibility
                            else Icons.Filled.VisibilityOff
                            val description = if (showPassword) "Hide password" else "Show password"

                            IconButton(onClick = { showPassword = !showPassword }) {
                                Icon(imageVector = image, description)
                            }
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.Black
                        )
                    )
                    if (state.value.signUpPasswordError != null) {
                        Text(
                            text = state.value.signUpPasswordError ?: "",
                            color = MaterialTheme.colors.error,
                            modifier = Modifier.align(Alignment.End)
                        )
                    }

                    Spacer(modifier = Modifier.padding(15.dp))

                    OutlinedTextField(
                        value = state.value.signUpRepeatedPassword,
                        onValueChange = {
                            viewModel.onEvent(AuthUiEvent.SignUpRepeatedPasswordChanged(it))
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        ),
                        isError = state.value.signUpRepeatedPasswordError != null,
                        label = { Text(text = "Confirm Password", color = Color.Black) },
                        placeholder = { Text(text = "Confirm Password", color = Color.Black) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f),
                        visualTransformation = if (showRepeatedPassword) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            val image = if (showRepeatedPassword)
                                Icons.Filled.Visibility
                            else Icons.Filled.VisibilityOff
                            val description =
                                if (showRepeatedPassword) "Hide password" else "Show password"

                            IconButton(onClick = { showRepeatedPassword = !showRepeatedPassword }) {
                                Icon(imageVector = image, description)
                            }
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.Black
                        )
                    )
                    if (state.value.signUpRepeatedPasswordError != null) {
                        Text(
                            text = state.value.signUpRepeatedPasswordError ?: "",
                            color = MaterialTheme.colors.error,
                            modifier = Modifier.align(Alignment.End)
                        )
                    }

                    Spacer(modifier = Modifier.padding(15.dp))

                    Row(
                        modifier = Modifier
                    ) {
                        Checkbox(
                            checked = state.value.acceptedTerms,
                            onCheckedChange = {
                                viewModel.onEvent(AuthUiEvent.AcceptTerms(it))
                            }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Accept terms")
                    }
                    if (state.value.termsError != null) {
                        Text(
                            text = state.value.termsError ?: "",
                            color = MaterialTheme.colors.error,
                        )
                    }

                    Spacer(modifier = Modifier.padding(10.dp))
                    Button(
                        onClick = {
                            viewModel.onEvent(AuthUiEvent.SignUp)
                        }, modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Black,
                            contentColor = Color.White
                        )
                    ) {
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

    if (state.value.isLoading
    ) {
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
