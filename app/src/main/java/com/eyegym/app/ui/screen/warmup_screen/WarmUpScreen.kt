package com.eyegym.app.ui.screen.warmup_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.eyegym.app.ui.theme.grey
import com.eyegym.app.ui.uikit.UiIconButton
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun WarmUpScreenRoot(
    viewModel: WarmUpScreenViewModel = koinViewModel(),
    bottomRoutes: @Composable () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    WarmUpScreenScreen(
        state = state,
        bottomRoutes = bottomRoutes,
        onAction = viewModel::onAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WarmUpScreenScreen(
    modifier: Modifier = Modifier,
    state: WarmUpScreenState,
    bottomRoutes: @Composable () -> Unit,
    onAction: (WarmUpScreenAction) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            when (state.warmUpState) {
                WarmUpState.READY -> {
                    Row (
                        modifier = modifier
                            .statusBarsPadding()
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        UiIconButton(
                            icon = Icons.Default.MoreVert,
                            onClick = {
                                showBottomSheet = true
                            }
                        )
                        Text(
                            text = stringResource(state.taskDuration.titleInt),
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    }
                }
                WarmUpState.STARTED -> {
                    Row (
                        modifier = modifier
                            .statusBarsPadding()
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            text = stringResource(state.taskDuration.titleInt),
                            style = MaterialTheme.typography.bodyLarge,
                        )
                        Spacer(modifier = modifier.weight(1f))
                        UiIconButton(
                            icon = ImageVector.vectorResource(R.drawable.baseline_pause_24),
                            onClick = {
                                onAction(WarmUpScreenAction.OnPauseTask)
                            }
                        )
                        UiIconButton(
                            icon = Icons.Default.Close,
                            onClick = {
                               // onAction(WarmUpScreenAction.OnStopTask)
                            }
                        )
                    }
                }
                WarmUpState.PAUSED -> {
                    Row (
                        modifier = modifier
                            .statusBarsPadding()
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            text = stringResource(state.taskDuration.titleInt),
                            style = MaterialTheme.typography.bodyLarge,
                        )
                        Spacer(modifier = modifier.weight(1f))
                        UiIconButton(
                            icon = ImageVector.vectorResource(R.drawable.baseline_play_arrow_24),
                            onClick = {
                                onAction(WarmUpScreenAction.OnResumeTask)
                            }
                        )
                        UiIconButton(
                            icon = Icons.Default.Close,
                            onClick = {
                                //onAction(WarmUpScreenAction.OnStopTask)
                            }
                        )
                    }
                }
                WarmUpState.COMPLETED -> {
                    Row (
                        modifier = modifier
                            .statusBarsPadding()
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ){
                        UiIconButton(
                            icon = Icons.Default.Close,
                            onClick = {
                                onAction(WarmUpScreenAction.OnReadyTask)
                            }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                containerColor = grey,
                shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
                sheetState = sheetState
            ) {
                SheetContent(
                    taskDuration = state.taskDuration,
                    onClickTaskDuration = {
                        onAction(WarmUpScreenAction.OnChangeTask(it))
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showBottomSheet = false
                            }
                        }
                    }
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    WarmUpScreenScreen(
        state = WarmUpScreenState(),
        bottomRoutes = {},
        onAction = {}
    )
}

@Preview(showSystemUi = true)
@Composable
private fun Preview2() {
    WarmUpScreenScreen(
        state = WarmUpScreenState(
            warmUpState = WarmUpState.STARTED
        ),
        bottomRoutes = {},
        onAction = {}
    )
}

@Preview(showSystemUi = true)
@Composable
private fun Preview3() {
    WarmUpScreenScreen(
        state = WarmUpScreenState(
            warmUpState = WarmUpState.PAUSED
        ),
        bottomRoutes = {},
        onAction = {}
    )
}

@Preview(showSystemUi = true)
@Composable
private fun Preview4() {
    WarmUpScreenScreen(
        state = WarmUpScreenState(
            warmUpState = WarmUpState.COMPLETED
        ),
        bottomRoutes = {},
        onAction = {}
    )
}
