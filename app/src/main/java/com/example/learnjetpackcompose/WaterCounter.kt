package com.example.learnjetpackcompose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WaterCounter(modifier: Modifier = Modifier) {
    var count by rememberSaveable {
        mutableStateOf(0)
    }
    WaterCounter(
        modifier = modifier,
        count = count,
        onIncrement = {
            count++
        }
    )
}

@Composable
fun WaterCounter(
    modifier: Modifier,
    count: Int,
    onIncrement: () -> Unit
) {
    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0) {
            Text(text = "You've had $count glasses.")
        }
        Row(Modifier.padding(8.dp)) {
            Button(
                onClick = {
                    onIncrement()
                },
                modifier = Modifier.padding(top = 8.dp),
                enabled = count < 10
            ) {
                Text("Add Me")
            }

        }
    }

}

