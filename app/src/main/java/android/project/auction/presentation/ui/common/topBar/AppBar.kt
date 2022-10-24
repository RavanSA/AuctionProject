package android.project.auction.presentation.ui.common.topBar

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
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

    TopAppBar(
        backgroundColor = White,
        elevation = 0.dp,
        modifier = Modifier.fillMaxWidth(),

        ) {

        Row(
            modifier = Modifier.height(32.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                CompositionLocalProvider(
                    LocalContentAlpha provides ContentAlpha.high,
                ) {
                    IconButton(onClick = onNavigationIconClick) {
                        Icon(imageVector = icon, contentDescription = "Toggle Drawer")
                    }
                }
            }

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
        }
    }
}