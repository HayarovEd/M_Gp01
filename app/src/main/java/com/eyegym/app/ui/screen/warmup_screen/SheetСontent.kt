package com.eyegym.app.ui.screen.warmup_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eyegym.app.R
import com.eyegym.app.domain.model.TaskDuration

@Composable
fun SheetContent(
    modifier: Modifier = Modifier,
    taskDuration: TaskDuration,
    onClickTaskDuration: (TaskDuration) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.task),
            style = MaterialTheme.typography.titleLarge,
        )
        TaskDuration.entries.forEach {
            Spacer(modifier = modifier.height(10.dp))
            Text(
                modifier = modifier.clickable {
                    onClickTaskDuration(it)
                },
                text = "${stringResource(it.titleInt)} (${it.duration} ${stringResource(R.string.seconds)})",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = if (it==taskDuration) FontWeight(700) else FontWeight (400)
            )
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun SheetContentView() {
    SheetContent(
        taskDuration = TaskDuration.FAST,
        onClickTaskDuration = {}
    )
}