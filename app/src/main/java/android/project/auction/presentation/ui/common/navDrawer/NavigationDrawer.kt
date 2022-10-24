package android.project.auction.presentation.ui.common.navDrawer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@ExperimentalMaterialApi
@Composable
fun DrawerBody(
    items: List<NavDrawerItem>,
    modifier: Modifier = Modifier,
    itemTextStyle: androidx.compose.ui.text.TextStyle = androidx.compose.ui.text.TextStyle(fontSize = 18.sp),
    onItemClick: (NavDrawerItem) -> Unit
) {


    Spacer(
        modifier = Modifier
            .size(20.dp)
    )

    LazyColumn(modifier) {
        items(items) { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onItemClick(item)
                    }
                    .padding(12.dp)
            ) {


                Icon(
                    imageVector = item.icon,
                    contentDescription = item.contentDescription,
                )

                Text(
                    text = item.title,
                    style = itemTextStyle,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 10.dp)
                )

            }
            Spacer(
                modifier = Modifier
                    .size(10.dp)
            )
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color(
                            android.graphics.Color.parseColor("#" + "D3D3D3")
                        )
                    )
                    .size(1.dp)
            )


            if (item.title == "Contact Us"
                || item.title == "Help"
                || item.title == "Logout"
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(12.dp)
                        .background(Color(android.graphics.Color.parseColor("#" + "D3D3D3")))
                )
            }
        }
    }
}

