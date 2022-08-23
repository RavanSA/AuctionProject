package android.project.auction.presentation.postitem.components

import android.app.DatePickerDialog
import android.net.Uri
import android.project.auction.domain.model.category.SubAndCategory
import android.project.auction.presentation.Screen
import android.project.auction.presentation.auth.sign_in.dpToSp
import android.project.auction.presentation.postitem.PostItemEvent
import android.project.auction.presentation.postitem.PostItemViewModel
import android.project.auction.presentation.ui.common.LoadingScreen
import android.project.auction.presentation.ui.common.topBar.TopBar
import android.util.Log
import android.widget.DatePicker
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import kotlinx.coroutines.Job
import java.util.*


@ExperimentalFoundationApi
@Composable
fun CreateItem(
    navController: NavController,
    subAndCategory: SubAndCategory
) {


    Scaffold(
        topBar = {
            Column(
                modifier = Modifier.background(Color.White)
            ) {
                TopBar(
                    title = "Create Item",
                    icon = Icons.Default.ArrowBack,
                    onNavigationIconClick = {

                    },
                    leftIcon = Icons.Default.Add
                )
            }
        },
        content = {
            CreateItemContent(
                subAndCategory, navController
            )
        }
    )
}

@ExperimentalFoundationApi
@Composable
fun CreateItemContent(
    subAndCategory: SubAndCategory,
    navController: NavController
) {
    Log.d("SUBANDCATEGORY", subAndCategory.toString())
//    SubAndCategoryContent(subAndCategory)
    Spacer(modifier = Modifier.size(10.dp))
    CreateItemInputsForms(subAndCategory, navController = navController)
}


@ExperimentalFoundationApi
@Composable
fun CreateItemInputsForms(
    subAndCategory: SubAndCategory,
    postItemViewModel: PostItemViewModel = hiltViewModel(),
    navController: NavController
) {

    var selectImages by remember { mutableStateOf(listOf<Uri>()) }

    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) {
            selectImages = it
        }


    val state = postItemViewModel.state

    var loadingItem by remember { mutableStateOf("Create Item") }
    var scope = rememberCoroutineScope()

    state.value.categoriesInput = subAndCategory.categoryID
    state.value.subCategoriesInput = subAndCategory.subCategoryID
    val mContext = LocalContext.current
    var delayJob: Job? = null

    val mYear: Int
    val mMonth: Int
    val mDay: Int

    val mCalendar = Calendar.getInstance()

    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    val mDate = remember { mutableStateOf("") }
    val mDate2 = remember { mutableStateOf("") }
    state.value.startTime = mDate2.value
    state.value.endTime = mDate.value

    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate.value = "$mYear-${mMonth + 1}-$mDayOfMonth"
        }, mYear, mMonth, mDay
    )

    val mDatePickerDialog2 = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate2.value = "$mYear-${mMonth + 1}-$mDayOfMonth"
        }, mYear, mMonth, mDay
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(10.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "Create Item",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = dpToSp(2.dp)
                    ),
                    fontSize = dpToSp(30.dp)
                )

                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable {

                        },
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.Top
                ) {
                    CategoryImage(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)                       // clip to the circle shape
                            .border(2.dp, Color.Gray, CircleShape)
                    )
                    Column() {
                        Text(
                            subAndCategory.categoryName,
                            color = Color.Black,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        Text(
                            subAndCategory.subCategoryName,
                            color = Color.Black,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                    }
                }

                Spacer(modifier = Modifier.padding(20.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    OutlinedTextField(
                        value = state.value.title,
                        onValueChange = {
                            postItemViewModel.onEvent(
                                PostItemEvent.TitleChanged(
                                    it
                                )
                            )
                        },
                        label = { Text(text = "Title", color = Color.Black) },
                        placeholder = { Text(text = "Title", color = Color.Black) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.Black
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email
                        ),
                    )


                    Spacer(modifier = Modifier.padding(15.dp))

                    OutlinedTextField(
                        value = state.value.description,
                        onValueChange = {
                            postItemViewModel.onEvent(
                                PostItemEvent.DescriptionChanges(
                                    it
                                )
                            )
                        },
                        label = { Text(text = "Description", color = Color.Black) },
                        placeholder = { Text(text = "Description", color = Color.Black) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.Black
                        )
                    )

                    Spacer(modifier = Modifier.padding(15.dp))

                    OutlinedTextField(
                        value = state.value.startingPrice,
                        onValueChange = {
                            postItemViewModel.onEvent(
                                PostItemEvent.StartingPriceChanged(
                                    it
                                )
                            )
                        },
                        label = { Text(text = "Starting Price", color = Color.Black) },
                        placeholder = { Text(text = "Starting Price", color = Color.Black) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                    )

                    Spacer(modifier = Modifier.padding(15.dp))

                    OutlinedTextField(
                        value = state.value.minIncrease,
                        onValueChange = {
                            postItemViewModel.onEvent(
                                PostItemEvent.MinIncreaseChanged(
                                    it
                                )
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        label = { Text(text = "Min Increase", color = Color.Black) },
                        placeholder = { Text(text = "Min INcrease", color = Color.Black) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f),
                    )
                    Button(onClick = {
                        mDatePickerDialog2.show()
                    }, colors = ButtonDefaults.buttonColors(backgroundColor = Color(0XFF0F9D58))) {
                        Text(text = "Open Date Picker", color = Color.White)
                    }


                    OutlinedTextField(
                        value = state.value.startTime,
                        onValueChange = {
                            postItemViewModel.onEvent(
                                PostItemEvent.StartTimeChanged(
                                    it
                                )
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        ),
                        label = { Text(text = "Start Time", color = Color.Black) },
                        placeholder = { Text(text = "Strat Time", color = Color.Black) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f),
                    )

                    Button(onClick = {
                        mDatePickerDialog.show()
                    }, colors = ButtonDefaults.buttonColors(backgroundColor = Color(0XFF0F9D58))) {
                        Text(text = "Open Date Picker", color = Color.White)
                    }

                    OutlinedTextField(
                        value = state.value.endTime,
                        onValueChange = {
                            postItemViewModel.onEvent(
                                PostItemEvent.EndTimeChanged(
                                    it
                                )
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        ),
                        label = { Text(text = "End Time", color = Color.Black) },
                        placeholder = { Text(text = "End Time", color = Color.Black) },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .clickable {
                                mDatePickerDialog.show()
                            },
                    )

                    Spacer(modifier = Modifier.padding(15.dp))


                    Spacer(modifier = Modifier.padding(10.dp))

                    Spacer(modifier = Modifier.padding(20.dp))
                }
            }

            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = { galleryLauncher.launch("image/*") },
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    Text(text = "Pick Image From Gallery")
                }

            }
        }

        items(selectImages) { uri ->
            Image(
                painter = rememberImagePainter(uri),
                contentScale = ContentScale.FillWidth,
                contentDescription = null,
                modifier = Modifier
                    .padding(16.dp, 8.dp)
                    .size(100.dp)
                    .clickable {

                    }
            )
        }

        item {
            Button(
                onClick = {
                    postItemViewModel.onEvent(
                        PostItemEvent.CreateItemClicked
                    )
                    navController.navigate(
                        Screen.AuctionListScreen.route
                    )
                }, modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Black,
                    contentColor = Color.White
                )
            ) {
                Text(text = loadingItem, fontSize = dpToSp(20.dp))
            }
            Spacer(modifier = Modifier.padding(20.dp))
            Text(
                text = "",
                modifier = Modifier.clickable(
                    onClick = {
                    })
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalFoundationApi
@Composable
fun SelectImages(

) {

    var selectImages by remember { mutableStateOf(listOf<Uri>()) }

    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) {
            selectImages = it
        }

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { galleryLauncher.launch("image/*") },
            modifier = Modifier
                .padding(10.dp)
        ) {
            Text(text = "Pick Image From Gallery")
        }

//                Image(
//                    painter = rememberImagePainter(uri),
//                    contentScale = ContentScale.FillWidth,
//                    contentDescription = null,
//                    modifier = Modifier
//                        .padding(16.dp, 8.dp)
//                        .size(100.dp)
//                        .clickable {
//
//                        }
//                )


    }

}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        LoadingScreen()
    }
}
