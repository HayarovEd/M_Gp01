package com.eyegym.app.ui.screen.survey_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.eyegym.app.R
import com.eyegym.app.ui.theme.violet
import com.eyegym.app.ui.uikit.UiIconButton
import com.eyegym.app.ui.uikit.UiTextField
import org.koin.androidx.compose.koinViewModel

@Composable
fun SurveyScreenRoot(
    viewModel: SurveyScreenViewModel = koinViewModel(),
    onBackPressed: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SurveyScreenScreen(
        state = state,
        onAction = viewModel::onAction,
        onBackPressed = onBackPressed
    )
}

@Composable
private fun SurveyScreenScreen(
    modifier: Modifier = Modifier,
    state: SurveyScreenState,
    onAction: (SurveyScreenAction) -> Unit,
    onBackPressed: () -> Unit,
) {
    Scaffold(
        topBar = {
            Row(
                modifier = modifier
                    .statusBarsPadding()
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = if (state.isSending) Arrangement.End else Arrangement.SpaceBetween
            ) {
                if (state.isSending) {
                    UiIconButton(
                        icon = Icons.Filled.Close,
                        onClick = onBackPressed
                    )
                } else {
                    Text(
                        text = stringResource(R.string.work_app),
                        style = MaterialTheme.typography.titleLarge,
                    )
                    UiIconButton(
                        icon = Icons.Filled.Close,
                        onClick = onBackPressed
                    )
                }
            }
        },
    ) { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
                .navigationBarsPadding()
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = if (state.isSending) Arrangement.Center else Arrangement.Top
        ) {
            if (state.isSending) {
                Text(
                    modifier = modifier.fillMaxWidth(),
                    text = stringResource(R.string.answer),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center
                )
            } else {
                Text(
                    modifier = modifier,
                    text = stringResource(R.string.effect),
                    style = MaterialTheme.typography.bodyLarge,
                )
                Spacer(modifier = modifier.height(16.dp))
                Row(
                    modifier = modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    for (i in 1..5) {
                        Row(
                            modifier = modifier,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                modifier = modifier,
                                text = i.toString(),
                                style = MaterialTheme.typography.bodyLarge,
                            )
                            RadioButton(
                                selected = i==state.effect,
                                onClick = {
                                    onAction(SurveyScreenAction.UpdateEffect(i))
                                }
                            )
                        }
                    }
                }
                Spacer(modifier = modifier.height(16.dp))
                Text(
                    modifier = modifier,
                    text = stringResource(R.string.material),
                    style = MaterialTheme.typography.bodyLarge,
                )
                Spacer(modifier = modifier.height(16.dp))
                Row(
                    modifier = modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    for (i in 1..5) {
                        Row(
                            modifier = modifier,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                modifier = modifier,
                                text = i.toString(),
                                style = MaterialTheme.typography.bodyLarge,
                            )
                            RadioButton(
                                selected = i==state.material,
                                onClick = {
                                    onAction(SurveyScreenAction.UpdateMaterial(i))
                                }
                            )
                        }
                    }
                }
                Spacer(modifier = modifier.height(16.dp))
                Text(
                    modifier = modifier,
                    text = stringResource(R.string.record_u),
                    style = MaterialTheme.typography.bodyLarge,
                )
                Spacer(modifier = modifier.height(16.dp))
                Row(
                    modifier = modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    for (i in 1..5) {
                        Row(
                            modifier = modifier,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                modifier = modifier,
                                text = i.toString(),
                                style = MaterialTheme.typography.bodyLarge,
                            )
                            RadioButton(
                                selected = i==state.record,
                                onClick = {
                                    onAction(SurveyScreenAction.UpdateRecord(i))
                                }
                            )
                        }
                    }
                }
                Spacer(modifier = modifier.height(16.dp))
                UiTextField(
                    label = stringResource(R.string.commit),
                    content = state.commit,
                    onClickContent = {
                        onAction(SurveyScreenAction.UpdateCommit(it))
                    }
                )
                Spacer(modifier = modifier.height(16.dp))
                Button(
                    modifier = modifier,
                    contentPadding = PaddingValues(
                        vertical = 8.dp,
                        horizontal = 16.dp
                    ),
                    enabled = state.effect>0
                            && state.material>0
                            && state.record>0,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = violet
                    ),
                    onClick = {
                        onAction(SurveyScreenAction.OnSend)
                    }
                ) {
                    Text(
                        text = stringResource(R.string.to_send),
                        style = MaterialTheme.typography.labelLarge,
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    SurveyScreenScreen(
        state = SurveyScreenState(
            effect = 2
        ),
        onAction = {},
        onBackPressed = {}
    )
}

@Preview(showSystemUi = true)
@Composable
private fun Preview2() {
    SurveyScreenScreen(
        state = SurveyScreenState(
            isSending = true
        ),
        onAction = {},
        onBackPressed = {}
    )
}