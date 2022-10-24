package android.project.auction.presentation.ui.common.navDrawer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material.DrawerValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import kotlin.math.abs

@ExperimentalMaterialApi
@Composable
fun PushScreen() {

    val coroutineScope = rememberCoroutineScope()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    var drawerWidth by remember {
        mutableStateOf(drawerState.offset.value)
    }

    val contentOffset = remember {
        derivedStateOf {
            drawerState.offset.value
        }
    }

    val scaffoldState = rememberScaffoldState(
        drawerState = drawerState
    )

    Box(Modifier.fillMaxSize()) {
        val xPos = (abs(drawerWidth) - abs(contentOffset.value))
        Column(
            Modifier.offset(
                x = with(LocalDensity.current) {
                    max(0.dp, xPos.toDp() - 56.dp)
                }
            )
        ) {
        }
    }

    SideEffect {
        if (drawerWidth == 0f) {
            drawerWidth = drawerState.offset.value
        }
    }
}