package android.project.auction.presentation.auctionitemdetail.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ItemDetailDescription(
    itemDetailDescription: String
) {

    var showMore by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth(0.9f)
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(2.dp)
            ),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {

        Text(
            text = "Description",
            color = Color.Black,
            maxLines = 1,
            modifier = Modifier.padding(start = 16.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        Column(modifier = Modifier
            .animateContentSize(animationSpec = tween(100))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { showMore = !showMore }) {
            if (showMore) {
                Text(
                    text = itemDetailDescription,
                    modifier = Modifier.padding(16.dp),
                    overflow = TextOverflow.Clip,
                    maxLines = 15,
                    color = Color.Black,
                    fontSize = 15.sp
                )
            } else {
                if (itemDetailDescription.length > 20) {
                    Text(
                        text = "$itemDetailDescription....more",
                        modifier = Modifier.padding(16.dp),
                        overflow = TextOverflow.Clip,
                        maxLines = 1,
                        color = Color.Black,
                        fontSize = 15.sp
                    )
                } else {
                    Text(
                        text = itemDetailDescription,
                        modifier = Modifier.padding(16.dp),
                        overflow = TextOverflow.Clip,
                        maxLines = 1,
                        color = Color.Black,
                        fontSize = 15.sp
                    )
                }
            }
        }
    }
}