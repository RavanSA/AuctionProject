package android.project.auction.presentation.auctionlist.components

import android.project.auction.domain.model.item.Item
import android.util.Log
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun ItemListItem(
    item: Item,
    onItemClick: (Item) -> Unit
) {
    Text(
        text = "${item.title})",
        style = MaterialTheme.typography.body1,
        overflow = TextOverflow.Ellipsis
    )

    Log.d("TESTITEMNAME", item.title)
}