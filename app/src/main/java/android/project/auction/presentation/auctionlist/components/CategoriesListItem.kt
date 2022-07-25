package android.project.auction.presentation.auctionlist.components

import android.project.auction.domain.model.category.Category
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun CategoriesListItem(
    category: Category,
    onItemClick: (Category) -> Unit
) {

//    Text(
//        text = category.name,
//        style = MaterialTheme.typography.body1,
//        overflow = TextOverflow.Ellipsis
//    )
    Row(
        modifier = Modifier
            .fillMaxWidth()

            // Size 100 dp
            .background(Color.White), // Background White

        // Align Items in Center
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center

    ) {
        // Text Composable which displays some
        // kind of message , text color is green
        Card(
            modifier = Modifier.padding(4.dp),

            ) {
            Column(
                modifier = Modifier.padding(2.dp)
            ) {
                Text(
                    text = category.name,
                    modifier = Modifier
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(15.dp),
                    overflow = TextOverflow.Clip,
                    maxLines = 1
                )
            }
        }
    }



    Log.d("CATEGORYNAME", category.name)
}
