package android.project.auction.presentation.ui.common

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import android.project.auction.R

@Composable
fun LoadingScreen(){

    val composition: LottieCompositionResult =  rememberLottieComposition(
       spec = LottieCompositionSpec.RawRes(
           R.raw.loading_animation
       )
     )

    val progress by animateLottieCompositionAsState(
        composition.value,
        isPlaying = true,
        iterations = LottieConstants.IterateForever,
        speed = 1.0f
    )

    LottieAnimation(
        composition = composition.value,
        progress = progress,
        modifier = Modifier.padding(all = 10.dp)
    )

}