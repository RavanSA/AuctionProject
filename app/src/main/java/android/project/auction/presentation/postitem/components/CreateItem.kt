package android.project.auction.presentation.postitem.components

import android.annotation.SuppressLint
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
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import kotlinx.coroutines.Job
import java.text.SimpleDateFormat
import java.util.*


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
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
                    title = "Create Auction",
                    icon = Icons.Default.ArrowBack,
                    onNavigationIconClick = {

                    },
                    leftIcon = Icons.Default.Clear
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

    var titleError by remember { mutableStateOf(true) }
    var descriptionError by remember { mutableStateOf(true) }
    var startingPriceError by remember { mutableStateOf(true) }
    var minimumIncreaseError by remember { mutableStateOf(true) }
    var imageCountError by remember { mutableStateOf(true) }
    var startDateError by remember { mutableStateOf(true) }
    var endDateError by remember { mutableStateOf(true) }
    var starTimeChecker by remember { mutableStateOf("") }
    var endTimeChecker by remember { mutableStateOf("") }
    var loadingScreenShow by remember { mutableStateOf(false) }
    val sdf = SimpleDateFormat("dd/MM/yyyy")

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


                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable {

                        },
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.Top
                ) {

//                    CategoryImage(
//                        modifier = Modifier
//                            .size(32.dp)
//                            .clip(CircleShape)
//                            .border(2.dp, Color.Gray, CircleShape),
//                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .shadow(
                                elevation = 3.dp,
                                shape = RoundedCornerShape(2.dp)
                            )
                            .padding(20.dp)
                    ) {
                        Text(
                            subAndCategory.categoryName,
                            color = Color.Black,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontSize = 15.sp
                        )

                        Text(
                            subAndCategory.subCategoryName,
                            color = Color.Black,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontSize = 15.sp
                        )

                    }
                }

                Spacer(modifier = Modifier.padding(10.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Column {
                        var titleState by remember { mutableStateOf("") }
                        state.value.title = titleState
                        val maxLength = 50
                        val lightBlue = Color.LightGray
                        val blue = Color.Black
                        Text(
                            text = "Title",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 4.dp),
                            textAlign = TextAlign.Start,
                            color = blue
                        )
                        TextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = titleState,
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = lightBlue,
                                cursorColor = Color.Black,
                                disabledLabelColor = lightBlue,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            onValueChange = {
                                if (it.length <= maxLength) titleState = it
                                if (it.isEmpty()) titleError = true
                                postItemViewModel.onEvent(
                                    PostItemEvent.TitleChanged(
                                        it
                                    )
                                )
                            },
                            shape = RoundedCornerShape(8.dp),
                            singleLine = true,
                            trailingIcon = {
                                if (titleState.isNotEmpty()) {
                                    IconButton(onClick = { titleState = "" }) {
                                        Icon(
                                            imageVector = Icons.Outlined.Close,
                                            contentDescription = null
                                        )
                                    }
                                }
                            },
                        )
                        Text(
                            text = "${titleState.length} / $maxLength",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 4.dp),
                            textAlign = TextAlign.End,
                            color = blue
                        )
                        if (titleState.length < 5 && titleState != "") {
                            titleError = true
                            Text(
                                text = "Title length must be grater than 5 ",
                                color = MaterialTheme.colors.error,
                                style = MaterialTheme.typography.caption,
                                modifier = Modifier.padding(start = 16.dp)
                            )
                        } else if (titleState.length > 5) {
                            titleError = false
                        }
                    }

                    Spacer(modifier = Modifier.padding(10.dp))

                    Column {
                        var stateDescription by remember { mutableStateOf("") }
                        state.value.description = stateDescription
                        Log.d("DESCRIPTION", state.value.description)
                        val maxLength = 5000
                        val lightBlue = Color.LightGray
                        val blue = Color.Black
                        Text(
                            text = "Description",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 4.dp),
                            textAlign = TextAlign.Start,
                            color = blue
                        )
                        TextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp),
                            value = stateDescription,
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = lightBlue,
                                cursorColor = Color.Black,
                                disabledLabelColor = lightBlue,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            onValueChange = {
                                if (it.length <= maxLength) stateDescription = it
                                if (it.isEmpty()) descriptionError = true
                                postItemViewModel.onEvent(
                                    PostItemEvent.DescriptionChanges(
                                        it
                                    )
                                )
                            },
                            shape = RoundedCornerShape(8.dp),
                            trailingIcon = {
                                if (stateDescription.isNotEmpty()) {
                                    IconButton(onClick = { stateDescription = "" }) {
                                        Icon(
                                            imageVector = Icons.Outlined.Close,
                                            contentDescription = null
                                        )
                                    }
                                }
                            }
                        )
                        Text(
                            text = "${stateDescription.length} / $maxLength",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 4.dp),
                            textAlign = TextAlign.Right,
                            color = blue
                        )
                        if (stateDescription.length < 10 && stateDescription != "") {
                            descriptionError = true
                            Text(
                                text = "Description length must be grater than 10",
                                color = MaterialTheme.colors.error,
                                style = MaterialTheme.typography.caption,
                                modifier = Modifier.padding(start = 16.dp)
                            )
                        } else if (stateDescription.length > 10) {
                            descriptionError = false
                        }
                    }

                    Spacer(modifier = Modifier.padding(10.dp))

                    Column {
                        Log.d("STARTPROCE", state.value.description)
                        val lightBlue = Color.LightGray
                        val blue = Color.Black
                        Text(
                            text = "Starting Price",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 4.dp),
                            textAlign = TextAlign.Start,
                            color = blue
                        )
                        TextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = state.value.startingPrice,
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = lightBlue,
                                cursorColor = Color.Black,
                                disabledLabelColor = lightBlue,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            ),
                            onValueChange = {
                                if (it.isEmpty()) startingPriceError = true
                                postItemViewModel.onEvent(
                                    PostItemEvent.StartingPriceChanged(
                                        it
                                    )
                                )
                            },
                            shape = RoundedCornerShape(8.dp),
                            trailingIcon = {
                                if (state.value.startingPrice.isNotEmpty()) {
                                    IconButton(onClick = { state.value.startingPrice = "" }) {
                                        Icon(
                                            imageVector = Icons.Outlined.Close,
                                            contentDescription = null
                                        )
                                    }
                                }
                            },
                        )
                        if (state.value.startingPrice != "") {
                            if (state.value.startingPrice.toDouble() < 100) {
                                startingPriceError = true
                                Text(
                                    text = "Starting Price must be greater than 100",
                                    color = MaterialTheme.colors.error,
                                    style = MaterialTheme.typography.caption,
                                    modifier = Modifier.padding(start = 16.dp)
                                )
                            } else if (state.value.startingPrice.toDouble() > 100) {
                                startingPriceError = false
                            }
                        }
                    }

                    Spacer(modifier = Modifier.padding(15.dp))


                    Column {
                        val lightBlue = Color.LightGray
                        val blue = Color.Black
                        Text(
                            text = "Minimum Increase",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 4.dp),
                            textAlign = TextAlign.Start,
                            color = blue
                        )
                        TextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = state.value.minIncrease,
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = lightBlue,
                                cursorColor = Color.Black,
                                disabledLabelColor = lightBlue,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            ),
                            onValueChange = {
                                if (state.value.minIncrease.length < 0) minimumIncreaseError = true
                                postItemViewModel.onEvent(
                                    PostItemEvent.MinIncreaseChanged(
                                        it
                                    )
                                )
                            },
                            shape = RoundedCornerShape(8.dp),
                            trailingIcon = {
                                if (state.value.minIncrease.isNotEmpty()) {
                                    IconButton(onClick = { state.value.minIncrease = "" }) {
                                        Icon(
                                            imageVector = Icons.Outlined.Close,
                                            contentDescription = null
                                        )
                                    }
                                }
                            },
                        )
                        if (state.value.startingPrice != "" && state.value.minIncrease != "") {
                            if (state.value.minIncrease.toDouble() < (state.value.startingPrice.toDouble() * 0.1)) {
                                minimumIncreaseError = true
                                Text(
                                    text = "Minimum Increase must be greater than starting price of 10 %",
                                    color = MaterialTheme.colors.error,
                                    style = MaterialTheme.typography.caption,
                                    modifier = Modifier.padding(start = 16.dp)
                                )
                            } else if (state.value.minIncrease.toDouble() > (state.value.startingPrice.toDouble() * 0.1)) {
                                minimumIncreaseError = false
                            }
                        }
                    }

                    Spacer(modifier = Modifier.padding(15.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .shadow(
                                elevation = 3.dp,
                                shape = RoundedCornerShape(2.dp)
                            )
                            .padding(10.dp)
                            .clickable {
                                galleryLauncher.launch("image/*")
                            },
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Icon(
                            Icons.Filled.AddCircle,
                            "Calendar",
                            modifier = Modifier.size(60.dp)
                        )

                        Text(
                            "Add Pictures",
                            color = Color.Black,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontSize = 18.sp
                        )
                        when {
                            selectImages.size in 1..3 -> {
                                imageCountError = true
                                Text(
                                    text = "You need to upload at least 3 pictures",
                                    color = MaterialTheme.colors.error,
                                    style = MaterialTheme.typography.caption,
                                    modifier = Modifier.padding(start = 16.dp)
                                )
                            }
                            selectImages.size > 8 -> {
                                imageCountError = true
                                Text(
                                    text = "You can upload maximum 8 images",
                                    color = MaterialTheme.colors.error,
                                    style = MaterialTheme.typography.caption,
                                    modifier = Modifier.padding(start = 16.dp)
                                )
                            }
                            selectImages.size in 3..8 -> {
                                imageCountError = false
                            }
                            selectImages.isEmpty() -> {
                                imageCountError = true
                            }
                        }
                    }
                }

                LazyRow(

                ) {
                    items(selectImages) { uri ->
                        Column(
                            modifier = Modifier
                                .size(120.dp)
                                .clip(RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp))
                                .padding(
                                    start = 20.dp, top = 5.dp,
                                    end = 5.dp, bottom = 5.dp
                                )
                                .clickable {

                                },
                            horizontalAlignment = Alignment.Start,
                        ) {
                            Image(
                                painter = rememberImagePainter(uri),
                                contentScale = ContentScale.Crop,
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(0.dp, 10.dp)
                                    .size(200.dp)
                                    .clip(RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp))
                            )
                        }
                    }

                }

                Spacer(modifier = Modifier.padding(15.dp))

                Column {
                    val lightBlue = Color.LightGray
                    val blue = Color.Black
                    Text(
                        text = "Start Time",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp),
                        textAlign = TextAlign.Start,
                        color = blue
                    )
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
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
                                                starTimeChecker = "$mDayOfMonth/${month + 1}/$year"
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
                        value = state.value.startTime,
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = lightBlue,
                            cursorColor = Color.Black,
                            disabledLabelColor = lightBlue,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        onValueChange = {
                            if (it.length < 0) startDateError = true
                            postItemViewModel.onEvent(
                                PostItemEvent.StartTimeChanged(
                                    it
                                )
                            )
                        },
                        shape = RoundedCornerShape(8.dp),
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.CalendarToday,
                                contentDescription = null
                            )
                        },
                        enabled = false
                    )
                    Log.d("TESTSTARTIME", starTimeChecker.toString())
                    if (starTimeChecker.isNotEmpty()) {
                        if (sdf.parse(sdf.format(Date())).after(sdf.parse(starTimeChecker))) {
                            startDateError = true
                            Text(
                                text = "Start time must be greater than current time",
                                color = MaterialTheme.colors.error,
                                style = MaterialTheme.typography.caption,
                                modifier = Modifier.padding(start = 16.dp)
                            )
                        } else {
                            startDateError = false
                        }
                    }
                }

                Spacer(modifier = Modifier.padding(15.dp))

                Column {
                    val lightBlue = Color.LightGray
                    val blue = Color.Black
                    Text(
                        text = "End Time",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp),
                        textAlign = TextAlign.Start,
                        color = blue
                    )
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
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
                                                endTimeChecker = "$mDayOfMonth/${month + 1}/$year"
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
                        value = state.value.endTime,
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = lightBlue,
                            cursorColor = Color.Black,
                            disabledLabelColor = lightBlue,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        onValueChange = {
                            if (it.length < 0) endDateError = true
                            postItemViewModel.onEvent(
                                PostItemEvent.EndTimeChanged(
                                    it
                                )
                            )
                        },
                        shape = RoundedCornerShape(8.dp),
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.CalendarToday,
                                contentDescription = null
                            )
                        },
                        enabled = false
                    )
                    if (endTimeChecker.isNotEmpty() && starTimeChecker.isNotEmpty()) {
                        if (sdf.parse(endTimeChecker).before(sdf.parse(starTimeChecker))) {
                            //sdf.parse(starTimeChecker)
                            endDateError = true
                            Text(
                                text = "End time must be greater than Start time",
                                color = MaterialTheme.colors.error,
                                style = MaterialTheme.typography.caption,
                                modifier = Modifier.padding(start = 16.dp)
                            )
                        } else {
                            endDateError = false
                        }
                    }
                }


                Spacer(modifier = Modifier.padding(10.dp))


            }
        }


        item {
            var hasError = false

            if (!titleError && !descriptionError &&
                !startingPriceError && !minimumIncreaseError
                && !imageCountError && !startDateError && !endDateError
            ) {
                hasError = true
            }

          Column(
                modifier = Modifier
                    .padding(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Button(
                    onClick = {
                        postItemViewModel.onEvent(
                            PostItemEvent.CreateItemClicked
                        )

                        loadingScreenShow = true
                        navController.navigate(
                            Screen.SplashScreen.route
                        )

               }, modifier = Modifier
                        .fillMaxWidth(1f)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Black,
                        contentColor = Color.White
                    ),
                    enabled = hasError
                ) {
                    Text(text = loadingItem, fontSize = dpToSp(20.dp))
                }
                Spacer(modifier = Modifier.padding(5.dp))

                Text(
                    text = "By creating an auction, you agree to the Terms and Conditions",
                    modifier = Modifier,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }


        }
    }
}

@Composable
fun WaitingScreenForCreatingItem() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        LoadingScreen()
    }

}
