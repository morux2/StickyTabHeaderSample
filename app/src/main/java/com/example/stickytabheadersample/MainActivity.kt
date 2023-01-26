package com.example.stickytabheadersample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.stickytabheadersample.ui.theme.StickyTabHeaderSampleTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleScreen()
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SampleScreen() {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    Column {
        TabRow(
            selectedTabIndex = pagerState.currentPage
        ) {
            Screen.values().map { it.name }.forEachIndexed { index, title ->
                Tab(
                    text = { Text(text = title) },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                )
            }
        }
        HorizontalPager(
            count = Screen.values().count(),
            state = pagerState
        ) { page ->
            when (Screen.values()[page]) {
                Screen.A -> AScreen()
                Screen.B -> BScreen()
            }
        }
    }
}

enum class Screen {
    A, B
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StickyTabHeaderSampleTheme {
        SampleScreen()
    }
}

@Composable
fun AScreen() {
    Log.d("Screen A", "composed!")
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(10) {
            Text("A ${it}")
        }
    }
}

@Composable
fun BScreen() {
    Log.d("Screen B", "composed!")
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(10) {
            Text("B ${it}")
        }
    }
}