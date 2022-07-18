package android.project.auction.presentation.auth.sign_in

import android.project.auction.common.AuthResult
import android.project.auction.presentation.auth.AuthUiEvent
import android.project.auction.presentation.auth.AuthViewModel
import android.project.auction.presentation.ui.common.LoadingScreen
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import android.project.auction.presentation.Screen
import kotlinx.coroutines.flow.collect


@Composable
fun AuthScreen(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {

    val state = viewModel.state
    val context = LocalContext.current

    LaunchedEffect(viewModel, context ){
        viewModel.authResults.collect { authResult ->
            when(authResult) {
                is AuthResult.Authorized -> {
                    navController.navigate(Screen.SecretScreen.route)
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = state.value.signUpUsername,
            onValueChange = {
                viewModel.onEvent(AuthUiEvent.SignUpUsernameChanged(it))
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "Username")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = state.value.signUpFullName,
            onValueChange = {
                viewModel.onEvent(AuthUiEvent.SignUpFullNameChanged(it))
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "FullName")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = state.value.signUpPassword,
            onValueChange = {
                viewModel.onEvent(AuthUiEvent.SignUpPasswordChanged(it))
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "Password")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                viewModel.onEvent(AuthUiEvent.SignUp)
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Sign up")
        }

        Spacer(modifier = Modifier.height(64.dp))

        TextField(
            value = state.value.signInUsername,
            onValueChange = {
                viewModel.onEvent(AuthUiEvent.SignInUsernameChanged(it))
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "Username")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = state.value.signInPassword,
            onValueChange = {
                viewModel.onEvent(AuthUiEvent.SignInPasswordChanged(it))
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "Password")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                viewModel.onEvent(AuthUiEvent.SignIn)
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Sign in")
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