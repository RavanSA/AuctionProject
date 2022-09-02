package android.project.auction.presentation.detailedsearch

import android.project.auction.data.local.entity.toItem
import android.project.auction.domain.model.category.SubAndCategory
import android.project.auction.presentation.Screen
import android.project.auction.presentation.auctionlist.components.ItemList
import android.project.auction.presentation.auth.sign_in.dpToSp
import android.project.auction.presentation.detailedsearch.components.OrderSection
import android.project.auction.presentation.ui.common.bottomNav.BottomNav
import android.project.auction.presentation.ui.common.topBar.TopBar
import android.util.Log
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Filter1
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun DetailedSearchScreen(
    navController: NavController,
    subAndCategory: SubAndCategory,
    detailedSearchViewModel: DetailedSearchViewModel = hiltViewModel()
) {

    detailedSearchViewModel.state.value.categoryId = subAndCategory.categoryID
    detailedSearchViewModel.state.value.subCategoryId = subAndCategory.subCategoryID

    val bottomState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )


    ModalBottomSheetLayout(
        sheetState = bottomState,
        sheetContent = {
            FilterContent(detailedSearchViewModel)
        },
        sheetShape = RoundedCornerShape(
            topStart = 30.dp,
            topEnd = 30.dp
        ),
    ) {

        Scaffold(
            topBar = {
                Column(
                    modifier = Modifier.background(Color.White)
                ) {
                    TopBar(
                        title = "${subAndCategory.categoryName}, ${subAndCategory.subCategoryName}",
                        icon = Icons.Default.Menu,
                        onNavigationIconClick = {

                        },
                        leftIcon = Icons.Default.Add
                    )
//                SearchAndFilter()
                }
            },
            bottomBar = {
                BottomAppBar(
                    modifier = Modifier
                        .height(65.dp)
                        .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp))
                        .background(Color.White),
                    cutoutShape = CircleShape,
                    //backgroundColor = Color.White,
                    elevation = 22.dp,
                    backgroundColor = Color.White
                ) {
                    BottomNav(navController = navController)
                }
            },
            floatingActionButtonPosition = FabPosition.Center,
            isFloatingActionButtonDocked = true,
            floatingActionButton = {
                FloatingActionButton(
                    shape = CircleShape,
                    onClick = {
                        navController.navigate(
                            Screen.PostItemScreen.route
                        )
                    },
                    contentColor = Color.White,
                    backgroundColor = Color.Black
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add icon")
                }
            },
            content = {
                DetailedSearchScreenContent(
                    navController,
                    subAndCategory.categoryID,
                    subAndCategory.subCategoryID,
                    bottomState
                )
            }
        )
    }
}

@Composable
fun FilterContent(
    detailedSearchViewModel: DetailedSearchViewModel
) {
    val state = detailedSearchViewModel.state

    Column(
        modifier = Modifier.height(300.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Starting Price", fontSize = 18.sp)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            OutlinedTextField(
                value = state.value.minStartingPrice,
                onValueChange = {
                    detailedSearchViewModel.onEvent(
                        DetailedSearchEvent.OnFilterMinStartingPriceChange(
                            it
                        )
                    )
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp),
                placeholder = {
                    Text(text = "Min Price")
                },
                maxLines = 1,
                singleLine = true
            )

            OutlinedTextField(
                value = state.value.maxStartingPrice,
                onValueChange = {
                    detailedSearchViewModel.onEvent(
                        DetailedSearchEvent.OnFilterMaxStartPriceChange(
                            it
                        )
                    )
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(20.dp),
                placeholder = {
                    Text(text = "Max Price")
                },
                maxLines = 1,
                singleLine = true
            )

        }

        Text(
            text = "Created Date",
            fontSize = 18.sp,
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            OutlinedTextField(
                value = state.value.fisrtCreateDate,
                onValueChange = {
                    detailedSearchViewModel.onEvent(
                        DetailedSearchEvent.OnFirstCreatedDateChanged(
                            it
                        )
                    )
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp),
                placeholder = {
                    Text(text = "Start Date")
                },
                maxLines = 1,
                singleLine = true
            )

            OutlinedTextField(
                value = state.value.secondCreatedDate,
                onValueChange = {
                    detailedSearchViewModel.onEvent(
                        DetailedSearchEvent.OnSecondCreateDateChanged(
                            it
                        )
                    )
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(20.dp),
                placeholder = {
                    Text(text = "End Date")
                },
                maxLines = 1,
                singleLine = true
            )

        }

        Button(
            onClick = {
                detailedSearchViewModel.onEvent(
                    DetailedSearchEvent.FilterButtonClicked
                )
            }, modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Black,
                contentColor = Color.White
            )
        ) {
            Text(text = "Filter Items", fontSize = dpToSp(20.dp))
        }

    }
}


@ExperimentalMaterialApi
@Composable
fun DetailedSearchScreenContent(
    navController: NavController,
    categoryId: String,
    subCategoryId: String,
    bottomState: ModalBottomSheetState
) {
    SearchAndFilter(
        navController = navController,
        categoryId = categoryId,
        subCategoryId = subCategoryId,
        bottomState = bottomState
    )
}

@ExperimentalMaterialApi
@Composable
fun SearchAndFilter(
    navController: NavController,
    categoryId: String,
    subCategoryId: String,
    detailedSearchViewModel: DetailedSearchViewModel = hiltViewModel(),
    bottomState: ModalBottomSheetState
) {
    val state = detailedSearchViewModel.state.value
    val firstOrder = remember { mutableStateOf(true) }

    val coroutineScope = rememberCoroutineScope()

    if (firstOrder.value) {
        detailedSearchViewModel.onEvent(
            DetailedSearchEvent.OnSubCategoryItemClicked(
                categoryId = categoryId,
                subCubCategoryId = subCategoryId
            )
        )
        firstOrder.value = false
    }


    Log.d("SEARCHITEM", detailedSearchViewModel.stateFilteredItemList.toString())
    val items = detailedSearchViewModel.stateFilteredItemList.filteredItemList
    Column(
        modifier = Modifier
            .padding(16.dp)
            .width(IntrinsicSize.Max)
    ) {
        OutlinedTextField(
            value = state.searchQuery,
            onValueChange = {
                detailedSearchViewModel.onEvent(
                    DetailedSearchEvent.OnSearchQueryChanged(
                        it
                    )
                )
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .background(Color.White),
            placeholder = {
//                Text(text = "Search...")
            },
            maxLines = 1,
            singleLine = true
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Row(modifier = Modifier
                .drawBehind {
                    drawRect(
                        Color.White
                    )
                }
                .padding(start = 50.dp)
                .clickable {
                    detailedSearchViewModel.onEvent(
                        DetailedSearchEvent.ToggleOrderSection
                    )
                },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(

                    imageVector = Icons.Default.Sort,
                    contentDescription = ""
                )

                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = "Sort",
                    fontSize = 20.sp
                )

            }


            Divider(
                color = Color.Black, modifier = Modifier
                    .width(1.dp)
                    .height(30.dp)
            )

            Row(modifier = Modifier
                .drawBehind {
                    drawRect(
                        Color.White
                    )
                }
                .padding(end = 50.dp)
                .clickable {
                    coroutineScope.launch {
                        bottomState.show()
                    }
                },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Filter1,
                    contentDescription = ""
                )
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "Filter",
                    fontSize = 18.sp
                )

            }
        }

        AnimatedVisibility(
            visible = state.isOrderSectionVisible,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically()
        ) {
            OrderSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                itemOrder = state.itemOrder,
                onOrderChange = {

                    detailedSearchViewModel.onEvent(
                        DetailedSearchEvent.Order(it)
                    )
                }
            )
        }

        LazyColumn(

            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items) { item ->
                ItemList(item = item.toItem(),
                    onItemClick = {
                        navController.navigate(Screen.AuctionItemDetailScreen.route + "/${item.id}")
                    }
                )
            }
        }

    }

}
