package android.project.auction.presentation.postitem.components

import android.project.auction.R
import android.project.auction.domain.model.category.Category
import android.project.auction.presentation.Screen
import android.project.auction.presentation.postitem.PostItemEvent
import android.project.auction.presentation.postitem.PostItemState
import android.project.auction.presentation.postitem.PostItemViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun CategoriesList(
    category: State<PostItemState>,
    navController: NavController,
    postItemViewModel: PostItemViewModel
) {
    CategoriesContent(
        category = category,
        navController = navController,
        postItemViewModel = postItemViewModel
    )
}

@Composable
fun CategoriesContent(
    category: State<PostItemState>,
    navController: NavController,
    postItemViewModel: PostItemViewModel
) {
    Column(
        modifier = Modifier.padding(15.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start
    ) {
        CategoriesLazyColumn(category.value.categories, navController, postItemViewModel)
    }
}

@Composable
fun CategoriesLazyColumn(
    categories: List<Category>,
    navController: NavController,
    postItemViewModel: PostItemViewModel
) {
    LazyColumn(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start
    ) {
        items(categories) { category ->
            CategoriesItem(category = category, navController = navController, postItemViewModel)
        }
    }
}

@Composable
fun CategoriesItem(
    category: Category,
    navController: NavController,
    postItemViewModel: PostItemViewModel
) {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .clickable {
                postItemViewModel.onEvent(
                    PostItemEvent.OnCategoryItemClicked
                )
                navController.navigate(Screen.SubCategoriesScreen.route + "/${category.id}")
            },
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Top
    ) {
        CategoryImage(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)                       // clip to the circle shape
                .border(2.dp, Color.Gray, CircleShape)
        )
        Column() {
            Text(
                category.name,
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                category.name,
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

        }
    }
}


@Composable
fun CategoryImage(
    modifier: Modifier = Modifier
        .size(64.dp)
        .clip(CircleShape)                       // clip to the circle shape
        .border(2.dp, Color.Gray, CircleShape)
) {
    Image(
        painter = painterResource(R.drawable.sample_avatar),
        contentDescription = "avatar",
        contentScale = ContentScale.Crop,            // crop the image if it's not a square
        modifier = modifier  // add a border (optional)
    )
}
