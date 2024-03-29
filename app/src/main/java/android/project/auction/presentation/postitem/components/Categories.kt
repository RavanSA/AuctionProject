package android.project.auction.presentation.postitem.components

import android.project.auction.domain.model.category.SubCategories
import android.project.auction.presentation.Screen
import android.project.auction.presentation.postitem.PostItemViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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

@Composable
fun CategoriesList(
    navController: NavController
) {
    CategoriesContent(
        navController = navController
    )
}

@Composable
fun CategoriesContent(
    navController: NavController
) {
    Column(
        modifier = Modifier.padding(15.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start
    ) {
        CategoriesLazyColumn(navController)
    }
}

@Composable
fun CategoriesLazyColumn(
    navController: NavController,
    postItemViewModel: PostItemViewModel = hiltViewModel()
) {

    val subCategory = postItemViewModel.stateSubCategories.subCategories

    LazyColumn(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start
    ) {
        items(subCategory) { subCategory ->
            CategoriesItem(category = subCategory, navController = navController, postItemViewModel)
        }
    }
}

@Composable
fun CategoriesItem(
    category: SubCategories,
    navController: NavController,
    postItemViewModel: PostItemViewModel
) {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .clickable {
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    key = "subcategory",
                    value = category
                )
                navController.navigate(
                    Screen.SubCategoriesScreen.route
                )
            },
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Top
    ) {
        CategoryImage(
            modifier = Modifier
                .size(70.dp)
                .padding(0.dp),
            category.categoryImage
        )
        Column(
            modifier = Modifier
                .padding(10.dp),
        ) {
            Text(
                category.name,
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                category.description,
                color = Color.Gray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 15.sp,
            )

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
