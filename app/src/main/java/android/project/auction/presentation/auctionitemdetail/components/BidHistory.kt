package android.project.auction.presentation.auctionitemdetail.components

import android.project.auction.domain.model.bids.Bids
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun BidHistoryUserDetails(
    bids: Bids
) {
    Row(
        modifier = Modifier
            .padding(8.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Top
    ) {

        Column() {
            val randomUserName = getRandomString()
            Text(
                randomUserName,
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                "${bids.amount}$",
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

        }
    }
}

private fun getRandomString(): String {
    val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
    return (1..10)
        .map { allowedChars.random() }
        .joinToString("")
}