package android.project.auction.presentation.ui.common.topBar

import android.project.auction.presentation.ui.theme.TextWhite
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun TopBar(
    title: String,
    icon: ImageVector,
    leftIcon: ImageVector,
    onNavigationIconClick: () -> Unit
) {

//    val appBarHorizontalPadding = 4.dp
//    val titleIconModifier = Modifier.fillMaxHeight()
//        .width(72.dp - appBarHorizontalPadding)

    TopAppBar(
        backgroundColor = TextWhite,
        elevation = 0.dp,
        modifier = Modifier.fillMaxWidth(),

        ) {

        //TopAppBar Content
        Row(
            modifier = Modifier.height(32.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            //Navigation Icon
            Row(verticalAlignment = Alignment.CenterVertically) {
                CompositionLocalProvider(
                    LocalContentAlpha provides ContentAlpha.high,
                ) {
                    IconButton(onClick = onNavigationIconClick) {
                        Icon(imageVector = icon, contentDescription = "Toggle Drawer")
                    }
                }
            }

            //Title
            Row(
                Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                ProvideTextStyle(value = MaterialTheme.typography.h6) {
                    CompositionLocalProvider(
                        LocalContentAlpha provides ContentAlpha.high,
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            maxLines = 1,
                            text = title
                        )
                    }
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                CompositionLocalProvider(
                    LocalContentAlpha provides ContentAlpha.high,
                ) {
                    IconButton(onClick = onNavigationIconClick) {
                        Icon(imageVector = leftIcon, contentDescription = "Toggle Drawer")
                    }
                }
            }

//            //actions
//            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
//                Row(
//                    Modifier.fillMaxHeight(),
//                    horizontalArrangement = Arrangement.End,
//                    verticalAlignment = Alignment.CenterVertically,
//                    content = actions
//                )
//            }
        }
    }
}