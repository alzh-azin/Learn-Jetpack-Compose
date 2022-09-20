package com.example.learnjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learnjetpackcompose.ui.theme.LearnJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ComposeQuadrant()
                }
            }
        }
    }
}

@Composable
fun ComposeQuadrant() {

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(Modifier.weight(1f)) {

            ComposableCardInfo(
                modifier = Modifier.weight(1f),
                color = Color.Green,
                title = R.string.label_text_composable,
                body = R.string.label_text_composable_description
            )

            ComposableCardInfo(
                modifier = Modifier.weight(1f),
                color = Color.Yellow,
                title = R.string.label_image_composable,
                body = R.string.label_image_composable_description
            )
        }

        Row(Modifier.weight(1f)) {

            ComposableCardInfo(
                modifier = Modifier.weight(1f),
                color = Color.Cyan,
                title = R.string.label_row_composable,
                body = R.string.label_row_composable_description
            )

            ComposableCardInfo(
                modifier = Modifier.weight(1f),
                color = Color.Gray,
                title = R.string.label_column_composable,
                body = R.string.label_column_composable_description
            )
        }
    }
}

@Composable
fun ComposableCardInfo(modifier: Modifier, color: Color, title: Int, body: Int) {
    Column(
        modifier = modifier.background(color).fillMaxHeight().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(title), fontWeight = FontWeight.Bold )
        Text(text = stringResource(body)  , textAlign = TextAlign.Justify)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ComposeQuadrantPreview() {
    LearnJetpackComposeTheme {
        ComposeQuadrant()
    }
}