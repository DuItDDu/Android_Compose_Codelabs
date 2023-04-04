package com.duitddu.android.codelabs.compose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.duitddu.android.codelabs.compose.codelab.CodeLabItem
import com.duitddu.android.codelabs.compose.ui.theme.AndroidComposeCodelabsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidComposeCodelabsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CodeLabItemButtons(
                        modifier = Modifier.padding(20.dp)
                    ) { launchCodeLab(it) }
                }
            }
        }
    }

    private fun launchCodeLab(item: CodeLabItem) {
        Intent(this, item.activity).apply {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }.let {
            startActivity(it)
        }
    }
}

@Composable
fun CodeLabItemButtons(
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState(),
    onClickItem: (CodeLabItem) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.verticalScroll(scrollState)
    ) {
        CodeLabItem.values().forEach {
            LaunchCodeLabItemButton(it, onClickItem)
        }
    }
}

@Composable
fun LaunchCodeLabItemButton(
    item: CodeLabItem,
    onClick: (CodeLabItem) -> Unit
) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = { onClick(item) }
    ) {
        Text(
            text = item.title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}