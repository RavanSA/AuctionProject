package android.project.auction.presentation.postitem.components

import android.project.auction.presentation.postitem.PostItemViewModel
import android.util.Log
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun SubCategegoriesList(
    navController: NavController,
    postItemViewModel: PostItemViewModel = hiltViewModel()
) {

    val state = postItemViewModel.state.value

    Text(text = state.subCategories.toString())

    Log.d("TEST SCREEN", state.subCategories.toString())
}