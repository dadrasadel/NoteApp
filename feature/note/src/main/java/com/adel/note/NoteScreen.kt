package com.adel.note

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.adel.data.model.note.NoteEntity
import com.adel.data.model.note.TabHeaderItem
import com.adel.note.state.NotesState
import com.adel.shared_ui.R
import com.adel.shared_ui.navigation.NavigationScreen


@Composable
fun NoteScreen(
    navController: NavController,
) {
    NoteScreenImpl(navController)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteScreenImpl(
    navController: NavController?,
    noteViewModel: NoteViewModel = hiltViewModel(),
) {
    val noteStateList by noteViewModel.noteList.collectAsStateWithLifecycle()
    var tabList by remember {
        mutableStateOf(emptyList<TabHeaderItem>())
    }
    var noteList by remember {
        mutableStateOf(emptyList<NoteEntity>())
    }
    var selectedTab by remember {
        mutableIntStateOf(0)
    }
    val pagerState = rememberPagerState { 3 }
    var isGrid by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(pagerState.currentPage) {
        selectedTab = pagerState.currentPage
    }
    LaunchedEffect(selectedTab) {
        pagerState.animateScrollToPage(selectedTab)
    }
    val screenWidth= with(LocalDensity.current){
        LocalConfiguration.current.screenWidthDp.dp.toPx()
    }

    Scaffold(
        modifier = Modifier.padding(bottom = 64.dp),
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .background(MaterialTheme.colorScheme.surface),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier
                            .size(56.dp)
                            .padding(start = 8.dp),
                        painter = painterResource(id = com.adel.shared_ui.R.drawable.avatar),
                        contentDescription = null
                    )
                    Text(
                        modifier = Modifier.padding(start = 16.dp),
                        text = "Alireza Abbasi",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Icon(
                        painter = painterResource(id = com.adel.shared_ui.R.drawable.ic_scroll),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        modifier = Modifier.padding(end = 16.dp),
                        painter = painterResource(id = com.adel.shared_ui.R.drawable.ic_notification_status),
                        contentDescription = null
                    )
                    Icon(
                        modifier = Modifier.padding(end = 16.dp),
                        painter = painterResource(id = com.adel.shared_ui.R.drawable.ic_direct_inbox),
                        contentDescription = null
                    )
                }

            }

        },

        ) {
        when(val noteState=noteStateList){
            is NotesState.Success->{
                tabList=noteState.tabList
                noteList=noteState.noteList
            }

            else -> {}
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(MaterialTheme.colorScheme.surface)
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
            ) {
                val (leftArrow, rightArrow, calendar, date, day) = createRefs()
                IconButton(onClick = { /*TODO*/ }, modifier = Modifier.constrainAs(leftArrow) {
                    end.linkTo(date.start)
                    start.linkTo(parent.start)
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_left),
                        contentDescription = "left_arrow"
                    )
                }
                IconButton(onClick = { /*TODO*/ }, modifier = Modifier.constrainAs(rightArrow) {
                    start.linkTo(date.end)
                    end.linkTo(parent.end)

                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_right),
                        contentDescription = "right_arrow"
                    )
                }
                Row {

                }
                Icon(
                    painter = painterResource(id = R.drawable.ic_calendar_2),
                    contentDescription = "right_arrow",
                    tint = Color.Unspecified,
                    modifier = Modifier.constrainAs(calendar) {
                        start.linkTo(date.start)
                    }
                )
                Text(
                    text = "ToDay",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.constrainAs(day) {
                        start.linkTo(calendar.end, margin = 4.dp)
                    })
                Text(text = "Friday, 9 March",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray,
                    modifier = Modifier.constrainAs(date) {
                        top.linkTo(calendar.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    })
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, start = 8.dp, end = 8.dp)
            ) {
                RoundedTabView(
                    modifier = Modifier.weight(0.9f),
                    tabList,
                    selectedTab
                ) {
                    selectedTab = it
                }
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .height(IntrinsicSize.Max)
                        .background(shape = CircleShape, color = Color.White)
                ) {
                    Icon(
                        modifier = Modifier,
                        painter = painterResource(id = R.drawable.ic_archive_minus),
                        contentDescription = null
                    )

                }
            }
            HorizontalPager(state = pagerState) { index ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 24.dp, start = 16.dp, end = 16.dp)

                ) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Max),
                        verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "Recent All Note", style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.weight(1f))
                        IconButton(onClick = { isGrid=!isGrid }) {
                            Icon(painter = painterResource(id=if(!isGrid)
                                R.drawable.ic_row_vertical
                                        else
                                R.drawable.ic_grid ), contentDescription = "grid_menu")
                        }
                        Divider(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(0.5.dp)
                                .padding(vertical = 12.dp)
                        )
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(painter = painterResource(id = R.drawable.ic_search_normal), contentDescription = "grid_menu")
                        }
                    }
                    DynamicGrid(screenWidth,isGrid,noteList,navController!!)
                }
            }

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoundedTabView(
    modifier: Modifier,
    tabs: List<TabHeaderItem>,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit
) {

    TabRow(
        selectedTabIndex = selectedTabIndex,
        divider = {},
        indicator = {},
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.primary,
        modifier = modifier
            .wrapContentWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(32.dp)
            )
    ) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                text = { if(index==selectedTabIndex){
                    Row(modifier=Modifier, verticalAlignment = Alignment.CenterVertically) {
                        Text(tab.title, maxLines = 1,modifier=Modifier.widthIn(max = 36.dp),
                            overflow = TextOverflow.Ellipsis)
                        Spacer(modifier = Modifier.padding(start = 4.dp))
                        BadgedBox(content = {
                            Text(text = tab.count.toString(), color = MaterialTheme.colorScheme.primary,
                                modifier= Modifier
                                    .background(shape = CircleShape, color = Color.White).size(20.dp))
                        }, badge = {})
                    }

                }else{
                    Text(tab.title)
                } },
                selected = selectedTabIndex == index,
                selectedContentColor = Color.White,
                unselectedContentColor = MaterialTheme.colorScheme.primary,
                onClick = { onTabSelected(index) },
                modifier = Modifier
                    .padding(4.dp)
                    .background(
                        shape = RoundedCornerShape(32.dp), color = if (selectedTabIndex == index) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            Color.Transparent
                        }
                    )
            )
        }
    }


}
@Composable
fun CardImpl(navController: NavController){
    Card (shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()){
            ConstraintLayout(modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()) {
                val(title,moreIcon,description,icon,edit,archive) = createRefs()
                Text(text = "Heli Wbsite Design", style = MaterialTheme.typography.titleMedium, modifier = Modifier.constrainAs(title){
                    top.linkTo(moreIcon.top)
                    bottom.linkTo(moreIcon.bottom)
                    start.linkTo(parent.start, margin = 8.dp)
                })
                IconButton(onClick = { /*TODO*/ }, modifier = Modifier.constrainAs(moreIcon){
                    end.linkTo(parent.end)
                }) {
                    Icon(painter = painterResource(id = R.drawable.ic_group), tint = Color.Unspecified, contentDescription = "ic_group")

                }

                Icon(painter = painterResource(id = R.drawable.ic_avatar_group),
                    tint = Color.Unspecified,
                    contentDescription = null, modifier = Modifier
                        .size(40.dp)
                        .constrainAs(icon) {
                            top.linkTo(description.bottom)
                            start.linkTo(title.start)
                        })
                Text(
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding(8.dp)
                        .constrainAs(description) {
                            top.linkTo(title.bottom, margin = 8.dp)
                            start.linkTo(parent.start, margin = 16.dp)
                            end.linkTo(parent.end, margin = 16.dp)
                        }
                )
                IconButton(onClick = { /*TODO*/ }, modifier = Modifier.constrainAs(archive){
                    end.linkTo(edit.start)
                    top.linkTo(edit.top, margin = 4.dp)
                    bottom.linkTo(edit.bottom, margin = 4.dp)
                }) {
                    Icon(painter = painterResource(id = R.drawable.ic_archive_minus), modifier = Modifier.size(20.dp), tint = Color.Unspecified, contentDescription = null)

                }
                IconButton(onClick = { navController.navigate(NavigationScreen.Screen.Work.route) }, modifier = Modifier.constrainAs(edit){
                    end.linkTo(moreIcon.end)
                    top.linkTo(description.bottom)
                }) {
                    Icon(painter = painterResource(id = R.drawable.ic_edit), tint = Color.Unspecified, contentDescription = "ic_group")

                }


            }
    }
}

@Composable
fun CardImplGrid(screenWidth:Float,navController: NavController){

    val itemWidth = (screenWidth / 4).dp
    Card (shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .padding(top = 16.dp, end = 16.dp)
            .width(itemWidth)
            .fillMaxHeight()){
            ConstraintLayout(modifier = Modifier
                .padding(4.dp)
                .fillMaxSize()) {
                val(title,moreIcon,description,icon,edit,archive) = createRefs()
                Text(text = "Heli Wbsite Design", style = MaterialTheme.typography.titleMedium, modifier = Modifier.constrainAs(title){
                    top.linkTo(moreIcon.top)
                    bottom.linkTo(moreIcon.bottom)
                    start.linkTo(parent.start, margin = 8.dp)
                })
                IconButton(onClick = { /*TODO*/ }, modifier = Modifier.constrainAs(moreIcon){
                    end.linkTo(parent.end)
                }) {
                    Icon(painter = painterResource(id = R.drawable.ic_group), tint = Color.Unspecified, contentDescription = "ic_group")

                }

                Icon(painter = painterResource(id = R.drawable.ic_avatar_group),
                    tint = Color.Unspecified,
                    contentDescription = null, modifier = Modifier
                        .size(40.dp)
                        .constrainAs(icon) {
                            bottom.linkTo(parent.bottom)
                            start.linkTo(title.start)
                        })
                Text(
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding(8.dp)
                        .constrainAs(description) {
                            top.linkTo(title.bottom, margin = 8.dp)
                            start.linkTo(parent.start, margin = 16.dp)
                            end.linkTo(parent.end, margin = 16.dp)
                        }
                )
                IconButton(onClick = { /*TODO*/ }, modifier = Modifier.constrainAs(archive){
                    end.linkTo(edit.start)
                    top.linkTo(edit.top, margin = 4.dp)
                    bottom.linkTo(edit.bottom, margin = 4.dp)
                }) {
                    Icon(painter = painterResource(id = R.drawable.ic_archive_minus), modifier = Modifier.size(20.dp), tint = Color.Unspecified, contentDescription = null)

                }
                IconButton(onClick = { navController.navigate(NavigationScreen.Screen.Work.route) }, modifier = Modifier.constrainAs(edit){
                    end.linkTo(moreIcon.end)
                    bottom.linkTo(parent.bottom)
                }) {
                    Icon(painter = painterResource(id = R.drawable.ic_edit), tint = Color.Unspecified, contentDescription = "ic_group")

                }


            }
    }
}

@Composable
fun DynamicGrid(screenWidth:Float,isGrid:Boolean,list: List<NoteEntity>,navController: NavController){

    Crossfade(targetState = !isGrid, label = "") { targetGrid->
        if(!targetGrid)
            LazyHorizontalGrid(
                rows = GridCells.Fixed(2),
                userScrollEnabled = true, content ={
                items(list.size+3){
                    CardImplGrid(screenWidth,navController)
                }
            } )
        else
            LazyColumn(content = {
                items(list.size+1){
                    CardImpl(navController)
                }
            })


    }
}