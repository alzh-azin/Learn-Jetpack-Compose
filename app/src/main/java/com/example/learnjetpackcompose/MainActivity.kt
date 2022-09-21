package com.example.learnjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learnjetpackcompose.ui.theme.BusinessCardDescription
import com.example.learnjetpackcompose.ui.theme.LearnJetpackComposeTheme
import com.example.learnjetpackcompose.ui.theme.LineColor

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
                    BusinessCard()
                }
            }
        }
    }
}

@Composable
fun BusinessCard() {
    BusinessCardDesign()

}

@Composable
fun BusinessCardDesign() {

    val image = painterResource(R.drawable.android_logo)
    val name = stringResource(R.string.label_name)
    val description = stringResource(R.string.label_description)
    Column(Modifier.padding(top = 200.dp)) {
        Image(
            image,
            contentDescription = null,
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight().size(100.dp, 100.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Text(
            text = description,
            fontSize = 16.sp,
            color = BusinessCardDescription,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
    Column(verticalArrangement = Arrangement.Bottom , modifier = Modifier.padding(bottom = 48.dp)) {
        BusinessCardInfo(icon = Icons.Filled.Phone, message = R.string.label_phne)
        BusinessCardInfo(icon = Icons.Filled.Share, message = R.string.label_username)
        BusinessCardInfo(icon = Icons.Filled.Message, message = R.string.label_email)
    }


}

@Composable
fun BusinessCardInfo(icon: ImageVector, message: Int) {

    Column {
        Divider(color = LineColor, thickness = 1.dp)
        Row(modifier = Modifier.padding(start = 48.dp, top = 16.dp)) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = BusinessCardDescription
            )
            Text(
                text = stringResource(message),
                color = Color.White,
                modifier = Modifier.padding(start = 24.dp , bottom = 8.dp)
            )
        }

    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BusinessCardPreview() {
    LearnJetpackComposeTheme {
        BusinessCard()
    }
}