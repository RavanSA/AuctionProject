package android.project.auction.presentation.auctionitemdetail.components

import android.project.auction.domain.model.item.ItemDetail
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AuctionStatusCard(
    itemDetails: ItemDetail
) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth(0.9f)
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(2.dp)
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .padding(2.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = itemDetails.endTime,
                modifier = Modifier

                    .padding(15.dp),
                overflow = TextOverflow.Clip,
                maxLines = 1,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "        Live",
                modifier = Modifier
                    .padding(15.dp),
                overflow = TextOverflow.Clip,
                maxLines = 1,
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}