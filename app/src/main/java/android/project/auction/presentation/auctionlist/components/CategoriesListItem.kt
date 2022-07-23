package android.project.auction.presentation.auctionlist.components

import android.project.auction.domain.model.category.Category
import android.util.Log
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun CategoriesListItem(
    category: Category,
    onItemClick: (Category) -> Unit
) {
    Text(
        text = "${category.name}",
        style = MaterialTheme.typography.body1,
        overflow = TextOverflow.Ellipsis
    )

    Log.d("CATEGORYNAME", category.name)
}