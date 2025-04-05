package com.eyegym.app.ui.screen.warmup_screen

import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.eyegym.app.R
import com.eyegym.app.domain.utils.REPEATE_TASK
import com.eyegym.app.ui.theme.background
import com.eyegym.app.ui.theme.grey
import com.eyegym.app.ui.theme.lightBlue
import com.eyegym.app.ui.theme.violet
import com.eyegym.app.ui.uikit.UiIconButton
import kotlinx.coroutines.delay
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

    var currentImage by remember { mutableIntStateOf(0) }

    LaunchedEffect(
        key1 = state.warmUpState,
        key2 = state.taskDuration
    ) {
        val repeatTimes = state.taskDuration.duration * 1000 / REPEATE_TASK
        Log.d("Test WARM UP", "taskDuration ${state.taskDuration.duration}")
        Log.d("Test WARM UP", "repeatTimes $repeatTimes")
        if (state.warmUpState == WarmUpState.STARTED) {
            repeat(repeatTimes) {
                delay(500.toLong())
                currentImage = (currentImage + 1) % 2
                Log.d("Test WARM UP", "currentImage $currentImage")
            }
        }
    }


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
        },
        bottomBar = bottomRoutes
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
        Column(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            when (state.warmUpState) {
                WarmUpState.READY -> {
                    Image(
                        painter = painterResource(state.taskDuration.image1Int),
                        contentDescription = ""
                    )
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = stringResource(R.string.begin_task),
                            style = MaterialTheme.typography.titleLarge,
                        )
                        Spacer(modifier = modifier.height(16.dp))
                        Box(
                            modifier = modifier
                                .size(150.dp)
                                .clip(CircleShape)
                                .background(lightBlue)
                                .clickable {
                                    onAction(WarmUpScreenAction.OnStartTask)
                                },
                        ) {
                            Text(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .align(Alignment.Center),
                                text = stringResource(R.string.start),
                                style = MaterialTheme.typography.titleLarge,
                                fontSize = 40.sp,
                                color = background,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

                WarmUpState.STARTED -> {
                    AnimatedContent(
                        targetState = currentImage,
                        transitionSpec = {
                            fadeIn(animationSpec = tween(REPEATE_TASK)) togetherWith
                                    fadeOut(animationSpec = tween(REPEATE_TASK))
                        }
                    ) { target ->
                        when (target) {
                            0 -> Image(
                                painter = painterResource(state.taskDuration.image1Int),
                                contentDescription = ""
                            )

                            1 -> Image(
                                painter = painterResource(state.taskDuration.image2Int),
                                contentDescription = ""
                            )

                            else -> Image(
                                painter = painterResource(state.taskDuration.image1Int),
                                contentDescription = ""
                            )
                        }
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = stringResource(R.string.begin_task),
                            style = MaterialTheme.typography.titleLarge,
                        )
                        Spacer(modifier = modifier.height(16.dp))
                        Box(
                            modifier = modifier
                                .size(150.dp)
                                .clip(CircleShape)
                                .background(violet),
                        ) {
                            val minutes = state.remainingTime / 60
                            val seconds = state.remainingTime % 60
                            Text(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .align(Alignment.Center),
                                text = String.format("%02d:%02d", minutes, seconds),
                                style = MaterialTheme.typography.titleLarge,
                                fontSize = 40.sp,
                                color = background,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

                WarmUpState.PAUSED -> TODO()
                WarmUpState.COMPLETED -> TODO()
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
