package com.eyegym.app.ui.screen.favorite_tips_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.eyegym.app.R
import com.eyegym.app.ui.uikit.ItemTip
import com.eyegym.app.ui.uikit.UiIconButton
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoriteScreenRoot(
    viewModel: FavoriteScreenViewModel = koinViewModel(),
    onBackPressed: () -> Unit,
    onNavigateToTip: (Int) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    FavoriteScreenScreen(
        state = state,
        onAction = viewModel::onAction,
        onNavigateToTip = onNavigateToTip,
        onBackPressed = onBackPressed
    )
}

@Composable
private fun FavoriteScreenScreen(
    modifier: Modifier = Modifier,
    state: FavoriteScreenState,
    onAction: (FavoriteScreenAction) -> Unit,
    onBackPressed: () -> Unit,
    onNavigateToTip: (Int) -> Unit
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
                    text = stringResource(R.string.favorites),
                    style = MaterialTheme.typography.titleLarge,
                )
            }
        },
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(state.trips) { tip ->
                ItemTip(
                    tip = tip,
                    isFavorite = true,
                    onUpdateFavorite = {
                        onAction(FavoriteScreenAction.UpdateFavorite(tip.id))
                    },
                    onClickTip = {
                        onNavigateToTip(tip.id)
                    }
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    FavoriteScreenScreen(
        state = FavoriteScreenState(),
        onAction = {},
        onNavigateToTip = {},
        onBackPressed = {}
    )
}