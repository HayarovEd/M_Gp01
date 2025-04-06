package com.eyegym.app.ui.uikit

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eyegym.app.R
import com.eyegym.app.domain.model.Trip
import com.eyegym.app.domain.model.mockTrips


@Composable
fun ItemTip(
    modifier: Modifier = Modifier,
    tip: Trip,
    isFavorite: Boolean,
    onUpdateFavorite: () -> Unit,
    onClickTip: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClickTip()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = modifier
                .weight(1f)
                .clip(
                    shape = RoundedCornerShape(8.dp)
                ),
            painter = painterResource(tip.imageInt),
            contentDescription = "",
            contentScale = ContentScale.FillWidth
        )
        Spacer(modifier = modifier.width(5.dp))
        Column(
            modifier = modifier
                .weight(3f)
        ) {
            Text(
                text = stringResource(tip.nameInt),
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = modifier.height(5.dp))
            Text(
                text = stringResource(tip.shortInt),
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
        UiIconButton(
            modifier = modifier
                .weight(1f),
            icon = if (isFavorite) ImageVector.vectorResource(R.drawable.baseline_favorite_24) else ImageVector.vectorResource(
                R.drawable.baseline_favorite_border_24
            ),
            onClick = onUpdateFavorite
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ItemTipView() {
    ItemTip(
        tip = mockTrips.first(),
        isFavorite = true,
        onClickTip = {},
        onUpdateFavorite = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun ItemTipView2() {
    ItemTip(
        tip = mockTrips.first(),
        isFavorite = false,
        onClickTip = {},
        onUpdateFavorite = {}
    )
}