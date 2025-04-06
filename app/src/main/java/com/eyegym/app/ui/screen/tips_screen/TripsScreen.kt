package com.eyegym.app.ui.screen.tips_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.eyegym.app.R
import com.eyegym.app.ui.uikit.ItemTip
import com.eyegym.app.ui.uikit.UiIconButton
import org.koin.androidx.compose.koinViewModel

@Composable
fun TripsScreenRoot(
    viewModel: TripsScreenViewModel = koinViewModel(),
    bottomRoutes: @Composable () -> Unit,
    onNavigateToFavorites: () -> Unit,
    onNavigateToTip: (Int) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    TripsScreenScreen(
        state = state,
        onAction = viewModel::onAction,
        bottomRoutes = bottomRoutes,
        onNavigateToFavorites = onNavigateToFavorites,
        onNavigateToTip = onNavigateToTip
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun TripsScreenScreen(
    modifier: Modifier = Modifier,
    state: TripsScreenState,
    bottomRoutes: @Composable () -> Unit,
    onAction: (TripsScreenAction) -> Unit,
    onNavigateToFavorites: () -> Unit,
    onNavigateToTip: (Int) -> Unit
) {

    var currentIndex by remember { mutableIntStateOf(0) }
    val lazyColumnState = rememberLazyListState()

    LaunchedEffect(currentIndex) {
        when (currentIndex) {
            0 -> lazyColumnState.animateScrollToItem(0)
            1 -> lazyColumnState.animateScrollToItem(6)
            2-> lazyColumnState.animateScrollToItem(12)
        }

    }

    Scaffold(
        topBar = {
            Row(
                modifier = modifier
                    .statusBarsPadding()
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.tips_articles),
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(modifier = modifier.weight(1f))
                UiIconButton(
                    icon = ImageVector.vectorResource(R.drawable.baseline_favorite_24),
                    onClick = onNavigateToFavorites
                )
            }
        },
        bottomBar = bottomRoutes
    ) { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            LazyRow(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                itemsIndexed(state.trips.keys.toList()) { index, it ->
                    FilterChip(
                        label = {
                            Text(
                                text = stringResource(it),
                                style = MaterialTheme.typography.labelLarge,
                            )
                        },
                        selected = currentIndex == index,
                        onClick = {
                            currentIndex = index
                        }
                    )
                }
            }
            Spacer(modifier = modifier.height(16.dp))
            LazyColumn(
                modifier = modifier.fillMaxWidth(),
                state = lazyColumnState,
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                state.trips.keys.forEach { group ->
                    stickyHeader {
                        Row(
                            modifier = modifier,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(group),
                                style = MaterialTheme.typography.titleLarge,
                            )
                            Spacer(modifier = modifier.width(5.dp))
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = ""
                            )
                        }
                    }
                    items(state.trips[group] ?: emptyList()) { tip ->
                        ItemTip(
                            tip = tip,
                            isFavorite = state.favoriteIds.contains(tip.id),
                            onUpdateFavorite = {
                                onAction(TripsScreenAction.UpdateFavorite(tip.id))
                            },
                            onClickTip = {
                                onNavigateToTip(tip.id)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    TripsScreenScreen(
        state = TripsScreenState(),
        bottomRoutes = {},
        onAction = {},
        onNavigateToTip = {},
        onNavigateToFavorites = {}
    )
}