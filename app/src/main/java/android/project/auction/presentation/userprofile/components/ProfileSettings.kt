package android.project.auction.presentation.userprofile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileSettings(

) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {

        Column(
            modifier = Modifier
                .background(Color.White)
                .clip(shape = RoundedCornerShape(20.dp))

        ) {
            Spacer(modifier = Modifier.size(20.dp))


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(20.dp))
                    .padding(10.dp)
                    .background(Color.White)
                    .shadow(2.dp)
                    .clickable { }
            ) {

                Text(
                    "Notifications",
                    color = Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(10.dp)
                )

                Spacer(modifier = Modifier.weight(1f))

                IconButton(modifier = Modifier,
                    onClick = { }) {
                    Icon(
                        Icons.Filled.ArrowForward,
                        "contentDescription",
                        tint = Color.Black
                    )
                }

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(20.dp))
                    .padding(10.dp)
                    .background(Color.White)
                    .shadow(2.dp)
                    .clickable { }
            ) {

                Text(
                    "Privacy Policy",
                    color = Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(10.dp)

                )

                Spacer(modifier = Modifier.weight(1f))

                IconButton(modifier = Modifier,
                    onClick = { }) {
                    Icon(
                        Icons.Filled.ArrowForward,
                        "contentDescription",
                        tint = Color.Black
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(20.dp))
                    .padding(10.dp)
                    .background(Color.White)
                    .clickable { }
                    .shadow(2.dp)
            ) {

                Text(
                    "Auction Rules",
                    color = Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(10.dp)

                )

                Spacer(modifier = Modifier.weight(1f))

                IconButton(modifier = Modifier,
                    onClick = { }) {
                    Icon(
                        Icons.Filled.ArrowForward,
                        "contentDescription",
                        tint = Color.Black
                    )
                }
            }

            Spacer(modifier = Modifier.size(60.dp))

        }
    }
}
