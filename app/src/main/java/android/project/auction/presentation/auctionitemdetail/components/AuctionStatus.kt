package android.project.auction.presentation.auctionitemdetail.components

import android.os.Handler
import android.os.Looper
import android.project.auction.domain.model.item.ItemDetail
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun AuctionStatusCard(
    itemDetails: ItemDetail
) {

    val mainHandler = Handler(Looper.getMainLooper())
    var daysState by remember { mutableStateOf("0") }
    var hoursState by remember { mutableStateOf("0") }
    var minutesState by remember { mutableStateOf("0") }
    var secondsState by remember { mutableStateOf("0") }

    mainHandler.post(object : Runnable {
        override fun run() {


            val sdf = SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss")

            val currentUtcTime = sdf.format(Date())
            val endTime = itemDetails.endTime.replace("T", " ")
            val startTime = itemDetails.startTime.replace("T", " ")

            val parsedCurrentUtcTime = sdf.parse(currentUtcTime)
            val parsedEndTime = sdf.parse(endTime)
            val parsedStartTime = sdf.parse(startTime)

            when {

//                parsedCurrentUtcTime.before(parsedStartTime) && !parsedCurrentUtcTime.after(
//                    parsedEndTime
//                )
                parsedEndTime.after(parsedCurrentUtcTime) -> {

                    var diff: Long = parsedEndTime.time - parsedCurrentUtcTime.time

                    val seconds = 1000
                    val minutes = seconds * 60
                    val hours = minutes * 60
                    val days = hours * 24


                    daysState = (diff / days).toString()
                    diff %= days
                    hoursState = (diff / hours).toString()
                    diff %= hours
                    minutesState = (diff / minutes).toString()
                    diff %= minutes
                    secondsState = (diff / seconds).toString()

                    Log.d("DAYSTEST", daysState)
                    Log.d("HOURSTEST", hoursState)
                    Log.d("MINUTESTEST", minutesState)

                }
            }
            mainHandler.postDelayed(this, 1000)
        }
    })



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

        Text(
            text = "Auction Ended in ",
            modifier = Modifier,
            overflow = TextOverflow.Clip,
            maxLines = 1,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Row(
            modifier = Modifier
                .padding(2.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(

            ) {
                Text(
                    text = daysState,
                    modifier = Modifier
                        .padding(10.dp),
                    overflow = TextOverflow.Clip,
                    color = Color.Black,
                    maxLines = 1,
                    fontSize = 18.sp
                )

                Text(
                    text = "days",
                    modifier = Modifier
                        .padding(0.dp),
                    overflow = TextOverflow.Clip,
                    maxLines = 1,
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                Text(
                    text = hoursState,
                    modifier = Modifier
                        .padding(10.dp),
                    overflow = TextOverflow.Clip,
                    maxLines = 1,
                    fontSize = 18.sp
                )

                Text(
                    text = "hours",
                    modifier = Modifier
                        .padding(0.dp),
                    overflow = TextOverflow.Clip,
                    maxLines = 1,
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                Text(
                    text = minutesState,
                    modifier = Modifier
                        .padding(10.dp),
                    overflow = TextOverflow.Clip,
                    maxLines = 1,
                    fontSize = 18.sp
                )

                Text(
                    text = "minutes",
                    modifier = Modifier
                        .padding(0.dp),
                    overflow = TextOverflow.Clip,
                    maxLines = 1,
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                Text(
                    text = secondsState,
                    modifier = Modifier.padding(10.dp),
                    overflow = TextOverflow.Clip,
                    maxLines = 1,
                    fontSize = 18.sp
                )

                Text(
                    text = "seconds",
                    modifier = Modifier
                        .padding(0.dp),
                    overflow = TextOverflow.Clip,
                    maxLines = 1,
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

        }
    }
}

fun getCurrentDateForCountDownTimer(): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss.SSS")
    return sdf.format(Date())
}