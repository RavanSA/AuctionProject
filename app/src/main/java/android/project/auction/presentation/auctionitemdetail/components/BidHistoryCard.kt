package android.project.auction.presentation.auctionitemdetail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun BidHistoryCard(
    modifier: Modifier = Modifier,
    bottomState: ModalBottomSheetState
) {

    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    )

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth(0.9f),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.size(10.dp))

        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "Bid History",
                color = Color.Gray,
                maxLines = 1,
                modifier = Modifier.padding(start = 14.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            Text(
                text = "See all",
                color = Color.Gray,
                maxLines = 1,
                modifier = Modifier
                    .padding(start = 40.dp)
                    .clickable {
                        coroutineScope.launch {
                            bottomState.show()
                        }
                    },
                fontSize = 15.sp
            )
        }
        Spacer(modifier = Modifier.size(10.dp))
    }
}