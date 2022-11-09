package com.example.learnjetpackcompose

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun WellnessScreen(
    modifier: Modifier = Modifier, wellnessViewModel: WellnessViewModel = viewModel()
) {
    Column(modifier) {

        WaterCounter(modifier)

        val list = remember {
            wellnessViewModel.tasks
        }
        WellnessTasksList(
            list = list,
            onCloseTask = { task ->
                wellnessViewModel.remove(task)
            },
            onCheckedTask = { task , checked ->
                wellnessViewModel.changeTaskChecked(task , checked)
            }
        )
    }
}