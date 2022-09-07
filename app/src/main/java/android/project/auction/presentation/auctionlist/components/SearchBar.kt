package android.project.auction.presentation.auctionlist.components

import android.project.auction.presentation.auctionlist.AuctionListEvent
import android.project.auction.presentation.auctionlist.AuctionListViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SearchBar(
    auctionViewModel: AuctionListViewModel = hiltViewModel()
) {
    val auctionItemState = auctionViewModel.stateItem

    Column(modifier = Modifier.background(Color.White)) {
        OutlinedTextField(
            value = auctionItemState.searchQuery,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "emailIcon"
                )
            },
            onValueChange = {
                auctionViewModel.onEvent(
                    AuctionListEvent.OnSearchQueryChange(it)
                )
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .background(Color.White),
            placeholder = {
                Text(text = "Search auctions...")
            },
            maxLines = 1,
            singleLine = true
        )
    }
}