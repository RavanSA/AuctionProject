package android.project.auction.presentation.ui.common.topBar

import androidx.compose.foundation.background
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector


@Composable
fun TopBar(
    title: String,
    icon: ImageVector,
    onNavigationIconClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = title)
        } ,
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
        navigationIcon = {
            IconButton(onClick =  onNavigationIconClick) {
                Icon(imageVector = icon, contentDescription = "Toggle Drawer")
            }
        }
    )
}