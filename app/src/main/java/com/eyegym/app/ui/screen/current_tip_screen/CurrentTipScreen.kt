package com.eyegym.app.ui.screen.current_tip_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.eyegym.app.R
import com.eyegym.app.domain.model.mockTrips
import com.eyegym.app.ui.uikit.UiIconButton
import org.koin.androidx.compose.koinViewModel

@Composable
fun CurrentTipScreenRoot(
    viewModel: CurrentTipScreenViewModel = koinViewModel(),
    onBackPressed: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    CurrentTipScreenScreen(
        state = state,
        onAction = viewModel::onAction,
        onBackPressed = onBackPressed
    )
}

@Composable
private fun CurrentTipScreenScreen(
    modifier: Modifier = Modifier,
    state: CurrentTipScreenState,
    onAction: (CurrentTipScreenAction) -> Unit,
    onBackPressed: () -> Unit,
) {
    Scaffold(
        topBar = {
            Row(
                modifier = modifier
                    .statusBarsPadding()
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                UiIconButton(
                    icon = Icons.AutoMirrored.Filled.ArrowBack,
                    onClick = onBackPressed
                )
                Text(
                    text = stringResource(R.string.tips_articles),
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(modifier = modifier.weight(1f))
                state.trip?.let { trip ->
                    UiIconButton(
                        icon = if (state.favoriteIds.contains(trip.id)) ImageVector.vectorResource(R.drawable.baseline_favorite_24) else ImageVector.vectorResource(
                            R.drawable.baseline_favorite_border_24
                        ),
                        onClick = {
                            onAction(CurrentTipScreenAction.UpdateFavorite)
                        }
                    )
                }
            }
        },
    ) { paddingValues ->
        state.trip?.let { tip ->
            Column(
                modifier = modifier
                    .padding(paddingValues)
                    .navigationBarsPadding()
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth(),
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
                    Text(
                        modifier = modifier
                            .weight(4f),
                        text = stringResource(tip.nameInt),
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
                Spacer(modifier = modifier.height(5.dp))
                Text(
                    text = stringResource(tip.shortInt),
                    style = MaterialTheme.typography.bodyMedium,
                )
                Spacer(modifier = modifier.height(5.dp))
                Text(
                    modifier = modifier
                        .fillMaxWidth(),
                    text = stringResource(tip.contentInt),
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Unspecified
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    CurrentTipScreenScreen(
        state = CurrentTipScreenState(
            trip = mockTrips.first()
        ),
        onAction = {},
        onBackPressed = {}
    )
}