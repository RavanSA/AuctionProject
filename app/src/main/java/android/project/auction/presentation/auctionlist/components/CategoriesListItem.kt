package android.project.auction.presentation.auctionlist.components

import android.project.auction.domain.model.category.Category
import android.project.auction.presentation.auctionlist.AuctionListEvent
import android.project.auction.presentation.auctionlist.AuctionListViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CategoriesListItem(
    auctionViewModel: AuctionListViewModel = hiltViewModel(),
    category: Category
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(White)
            .clickable {
                auctionViewModel.onEvent(
                    AuctionListEvent.OnSearchQueryChange(category.id)
                )
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier
                .padding(4.dp)
                .background(White),
        ) {
            Column(
                modifier = Modifier
                    .padding(2.dp)
                    .background(White)
            ) {
                Text(
                    text = category.name,
                    modifier = Modifier
                        .background(
                            color = White,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(15.dp),
                    overflow = TextOverflow.Clip,
                    maxLines = 1
                )
            }
        }
    }
}
