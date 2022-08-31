package android.project.auction.presentation.postitem.components

import android.app.DatePickerDialog
import android.app.TimePickerDialog
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
import android.widget.TimePicker
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
            postItemViewModel.onEvent(
                PostItemEvent.SelectImagesChanged(it)
            )
        }

    val state = postItemViewModel.state
    val startDate = remember { mutableStateOf("") }
    state.value.startTime = startDate.value

    val endDate = remember { mutableStateOf("") }
    state.value.endTime = endDate.value

    var loadingItem by remember { mutableStateOf("Create Item") }
    var scope = rememberCoroutineScope()

    state.value.categoriesInput = subAndCategory.categoryID
    state.value.subCategoriesInput = subAndCategory.subCategoryID

    val mContext = LocalContext.current
    var delayJob: Job? = null

    Log.d("STATEVALUE", state.value.startTime)
    Log.d("ENDTIME", state.value.endTime)

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
                        placeholder = { Text(text = "Start Time", color = Color.Black) },
                        singleLine = true,
                        enabled = false,
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .clickable {

                                val mYear: Int
                                val mMonth: Int
                                val mDay: Int
                                val mHours: Int
                                val mMinutes: Int

                                val mCalendar = Calendar.getInstance()

                                mCalendar.time = Date()

                                mYear = mCalendar.get(Calendar.YEAR)
                                mMonth = mCalendar.get(Calendar.MONTH)
                                mDay = mCalendar.get(Calendar.DAY_OF_MONTH)
                                mHours = mCalendar.get(Calendar.HOUR_OF_DAY)
                                mMinutes = mCalendar.get(Calendar.MINUTE)

                                DatePickerDialog(
                                    mContext,
                                    { _: DatePicker, year: Int, month: Int, mDayOfMonth: Int ->
                                        TimePickerDialog(
                                            mContext,
                                            { _: TimePicker, hour: Int, minute: Int ->

                                                startDate.value =
                                                    "$year-${month + 1}-$mDayOfMonth $hour:$minute"

                                            },
                                            mHours,
                                            mMinutes,
                                            false
                                        ).show()
                                    },
                                    mYear,
                                    mMonth,
                                    mDay
                                ).show()
                            },
                    )

                    Log.d("STARTDATEMETHOD", startDate.value)

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
                        enabled = false,

                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .clickable {
                                var endTime: String = ""
                                val mYear: Int
                                val mMonth: Int
                                val mDay: Int
                                val mHours: Int
                                val mMinutes: Int

                                val mCalendar = Calendar.getInstance()

                                mYear = mCalendar.get(Calendar.YEAR)
                                mMonth = mCalendar.get(Calendar.MONTH)
                                mDay = mCalendar.get(Calendar.DAY_OF_MONTH)
                                mHours = mCalendar.get(Calendar.HOUR_OF_DAY)
                                mMinutes = mCalendar.get(Calendar.MINUTE)

                                mCalendar.time = Date()


                                DatePickerDialog(
                                    mContext,
                                    { _: DatePicker, year: Int, month: Int, mDayOfMonth: Int ->
                                        TimePickerDialog(
                                            mContext,
                                            { _: TimePicker, hour: Int, minute: Int ->

                                                endDate.value =
                                                    "$year-${month + 1}-$mDayOfMonth $hour:$minute"
                                                Log.d("ENDTIME", endTime)

                                                postItemViewModel.onEvent(
                                                    PostItemEvent.EndTimeChanged(
                                                        postItemViewModel.state.value.endTime
                                                    )
                                                )
                                            },
                                            mHours,
                                            mMinutes,
                                            false
                                        ).show()
                                    },
                                    mYear,
                                    mMonth,
                                    mDay
                                ).show()
                            },
                    )

                    Spacer(modifier = Modifier.padding(15.dp))

                    Spacer(modifier = Modifier.padding(10.dp))

                    Spacer(modifier = Modifier.padding(20.dp))

                    Button(
                        onClick = { galleryLauncher.launch("image/*") },
                        modifier = Modifier
                            .padding(10.dp)
                    ) {
                        Text(text = "Pick Image From Gallery")
                    }
                }
            }
        }

        items(selectImages) { uri ->
            Image(
                painter = rememberImagePainter(uri),
                contentScale = ContentScale.Fit,
                contentDescription = null,
                modifier = Modifier
                    .padding(16.dp, 8.dp)
                    .size(70.dp)
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
