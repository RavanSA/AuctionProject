package android.project.auction.presentation.detailedsearch.components

import android.project.auction.domain.model.category.SubCategories
import android.project.auction.presentation.Screen
import android.project.auction.presentation.detailedsearch.DetailedSearchViewModel
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter

@ExperimentalFoundationApi
@Composable
fun CategoriesLazyGrid(
    navController: NavController,
    detailedSearchViewModel: DetailedSearchViewModel = hiltViewModel()
) {
    val subCategories = detailedSearchViewModel.state.value.subCategories
    val error = detailedSearchViewModel.state.value.subCategoryError
    val loading = detailedSearchViewModel.state.value.isSubCategoriesLoading

    CategoriesContent(
        navController = navController,
        subCategories = subCategories
    )
}

@ExperimentalFoundationApi
@Composable
fun CategoriesContent(
    navController: NavController,
    subCategories: List<SubCategories>
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(3)
    ) {
        items(subCategories) { subCategory ->
            DetailedSearchCategoriesItem(
                category = subCategory,
                navController = navController,
            )
        }
    }
}


@Composable
fun DetailedSearchCategoriesItem(
    category: SubCategories,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .clickable {
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    key = "detailedSubcategory",
                    value = category
                )
                navController.navigate(
                    Screen.DetailedSearchSubCategoriesScreen.route
                )
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CategoryImage(
            modifier = Modifier
                .size(80.dp),
            imageUrl = category.categoryImage
        )
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                category.name,
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
//            Text(
//                category.name,
//                color = Color.Black,
//                maxLines = 1,
//                overflow = TextOverflow.Ellipsis
//            )
        }
    }
}


@ExperimentalCoilApi
@Composable
fun CategoryImage(
    modifier: Modifier = Modifier
        .size(64.dp)
        .clip(CircleShape)                       // clip to the circle shape
        .border(2.dp, Color.Gray, CircleShape),
    imageUrl: String
) {
    val painter = rememberImagePainter(
        data = imageUrl,
        builder = {
            crossfade(500)
        })

    val painterState = painter.state

    if (painterState is ImagePainter.State.Loading) {
        CircularProgressIndicator(
            modifier = Modifier,
            color = Color.Black
        )
    } else {
        Image(
            painter = rememberImagePainter(imageUrl),
            contentDescription = "avatar",
            contentScale = ContentScale.Crop,            // crop the image if it's not a square
            modifier = modifier  // add a border (optional)
        )
    }
}