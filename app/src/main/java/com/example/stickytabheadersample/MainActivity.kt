package com.example.stickytabheadersample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stickytabheadersample.ui.theme.StickyTabHeaderSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleScreen()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SampleScreen() {
    var tabSelected by rememberSaveable { mutableStateOf(Screen.A) }
    LazyColumn {
        item {
            Text(
                modifier = Modifier.padding(vertical = 100.dp).fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "collapsed!"
            )
        }
        stickyHeader {
            TabRow(
                selectedTabIndex = tabSelected.ordinal
            ) {
                Screen.values().map { it.name }.forEachIndexed { index, title ->
                    Tab(
                        text = { Text(text = title) },
                        selected = tabSelected.ordinal == index,
                        onClick = { tabSelected = Screen.values()[index] }
                    )
                }
            }
        }
        when (tabSelected) {
            Screen.A -> aScreen(this@LazyColumn)
            Screen.B -> bScreen(this@LazyColumn)
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

fun aScreen(
    lazyListScope: LazyListScope
) {
    with(lazyListScope) {
        items(30) {
            Text(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "A $it"
            )
        }
    }
}

fun bScreen(
    lazyListScope: LazyListScope
) {
    with(lazyListScope) {
        items(30) {
            Text(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "B $it"
            )
        }
    }
}