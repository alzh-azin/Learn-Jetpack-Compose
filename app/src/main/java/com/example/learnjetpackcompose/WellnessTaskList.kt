package com.example.learnjetpackcompose

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier


@Composable
fun WellnessTasksList(
    modifier: Modifier = Modifier,
    list: List<WellnessTask>,
    onCloseTask: (WellnessTask) -> Unit,
    onCheckedTask: (WellnessTask, Boolean) -> Unit
) {

    LazyColumn(modifier) {

        items(items = list, key = { task ->
            task.id
        }) { task ->
            WellnessTaskItem(
                taskName = task.label,
                onClose = {
                    onCloseTask(task)
                },
                onCheckedChange = { checked ->
                    onCheckedTask(task , checked)
                },
                checked = task.checked
            )

        }
    }

}