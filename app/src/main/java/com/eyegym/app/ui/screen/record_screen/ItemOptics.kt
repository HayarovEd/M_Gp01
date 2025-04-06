package com.eyegym.app.ui.screen.record_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eyegym.app.R
import com.eyegym.app.domain.model.Optics
import com.eyegym.app.domain.model.mockOptics
import com.eyegym.app.ui.theme.secondColor
import com.eyegym.app.ui.theme.txfColor

@Composable
fun ItemOptics(
    modifier: Modifier = Modifier,
    optics: Optics,
    onClickOptics: () -> Unit
) {
    Column (
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClickOptics()
            },
    ) {
        Text(
            text = optics.name,
            style = MaterialTheme.typography.titleLarge,
        )
        Spacer(modifier = modifier.height(10.dp))
        Text(
            text = optics.address,
            style = MaterialTheme.typography.labelLarge,
            color = secondColor
        )
        Spacer(modifier = modifier.height(5.dp))
        Text(
            text = "${stringResource(R.string.opened)}: ${optics.openTime}-${optics.closeTime}",
            style = MaterialTheme.typography.labelLarge,
            color = secondColor
        )
        Spacer(modifier = modifier.height(3.dp))
        HorizontalDivider(
            thickness = 2.dp,
            color = txfColor
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun ItemOpticsView() {
    ItemOptics(
        optics = mockOptics.first(),
        onClickOptics = {}
    )
}