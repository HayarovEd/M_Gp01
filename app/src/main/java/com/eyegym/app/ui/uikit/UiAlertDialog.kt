package com.eyegym.app.ui.uikit

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eyegym.app.R
import com.eyegym.app.ui.theme.background
import com.eyegym.app.ui.theme.violet

@Composable
fun UiAlertDialog(
    title: String,
    onClickConfirm: () -> Unit,
    onClickCancel: () -> Unit,
    modifier: Modifier = Modifier,
) {
    AlertDialog(
        containerColor = background,
        title = {
            Text(
                modifier = modifier
                    .padding(10.dp),
                text = title,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyLarge,
            )
        },
        onDismissRequest = onClickCancel,
        dismissButton = {
            Button(
                contentPadding = PaddingValues(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                shape = RoundedCornerShape(100.dp),
                onClick = onClickCancel
            ) {
                Text(
                    color = violet,
                    text = stringResource(id = R.string.cansel),
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        },
        confirmButton = {
            Button(
                contentPadding = PaddingValues(8.dp),
                shape = RoundedCornerShape(100.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = violet
                ),
                onClick = onClickConfirm
            ) {
                Text(
                    color = MaterialTheme.colorScheme.background,
                    text = stringResource(id = R.string.complete),
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        },
        shape = RoundedCornerShape(8.dp)
    )
}


@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun UiAlertDialogView1() {
    UiAlertDialog(
        title = "hallo",
        onClickCancel = {},
        onClickConfirm = {},
    )
}

@Preview(
    showBackground = true,
)
@Composable
private fun UiAlertDialogdView2() {
    UiAlertDialog(
        title = "hallo",
        onClickCancel = {},
        onClickConfirm = {},
    )
}