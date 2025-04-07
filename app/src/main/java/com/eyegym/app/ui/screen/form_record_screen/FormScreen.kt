package com.eyegym.app.ui.screen.form_record_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.eyegym.app.R
import com.eyegym.app.domain.model.mockOptics
import com.eyegym.app.domain.utils.RUSSIAN_PHONE_MASK
import com.eyegym.app.domain.utils.toDdMmYyyy
import com.eyegym.app.ui.theme.background
import com.eyegym.app.ui.theme.secondColor
import com.eyegym.app.ui.theme.violet
import com.eyegym.app.ui.uikit.MaskVisualTransformation
import com.eyegym.app.ui.uikit.UiIconButton
import com.eyegym.app.ui.uikit.UiTextField
import org.koin.androidx.compose.koinViewModel

@Composable
fun FormScreenRoot(
    viewModel: FormScreenViewModel = koinViewModel(),
    onBackPressed: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    FormScreenScreen(
        state = state,
        onAction = viewModel::onAction,
        onBackPressed = onBackPressed,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FormScreenScreen(
    modifier: Modifier = Modifier,
    state: FormScreenState,
    onAction: (FormScreenAction) -> Unit,
    onBackPressed: () -> Unit,
) {
    var showTimePicker by remember { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val datePickerState = rememberDatePickerState()
    val timePickerState = rememberTimePickerState(
        is24Hour = true,
    )

    if (showDatePicker) {
        Popup(
            onDismissRequest = { showDatePicker = false },
            alignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DatePicker(
                    state = datePickerState,
                    showModeToggle = false
                )
                Row(
                    modifier = modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,

                    ) {
                    Button(
                        modifier = modifier.weight(1f),
                        contentPadding = PaddingValues(
                            vertical = 8.dp,
                            horizontal = 16.dp
                        ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = violet
                        ),
                        onClick = {
                            showDatePicker = false
                        }
                    ) {
                        Text(
                            text = stringResource(R.string.cansel),
                            style = MaterialTheme.typography.labelLarge,
                        )
                    }
                    Button(
                        modifier = modifier.weight(1f),
                        contentPadding = PaddingValues(
                            vertical = 8.dp,
                            horizontal = 16.dp
                        ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = violet
                        ),
                        onClick = {
                            showDatePicker = false
                            datePickerState.selectedDateMillis?.let {
                                onAction(FormScreenAction.UpdateDate(it.toDdMmYyyy()))
                            }
                        }
                    ) {
                        Text(
                            text = stringResource(R.string.ok),
                            style = MaterialTheme.typography.labelLarge,
                        )
                    }
                }
            }
        }
    }

    if (showTimePicker) {
        Popup(
            onDismissRequest = { showTimePicker = false },
            alignment = Alignment.Center
        ) {
            Column(
                modifier = modifier.background(background),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TimePicker(
                    state = timePickerState,
                )
                Row(
                    modifier = modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,

                    ) {
                    Button(
                        modifier = modifier.weight(1f),
                        contentPadding = PaddingValues(
                            vertical = 8.dp,
                        ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = violet
                        ),
                        onClick = {
                            showTimePicker = false
                        }
                    ) {
                        Text(
                            text = stringResource(R.string.cansel),
                            style = MaterialTheme.typography.labelLarge,
                        )
                    }
                    Button(
                        modifier = modifier.weight(1f),
                        contentPadding = PaddingValues(
                            vertical = 8.dp,
                        ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = violet
                        ),
                        onClick = {
                            showTimePicker = false
                            onAction(FormScreenAction.UpdateTime("${timePickerState.hour}:${timePickerState.minute}"))
                        }
                    ) {
                        Text(
                            text = stringResource(R.string.ok),
                            style = MaterialTheme.typography.labelLarge,
                        )
                    }
                }
            }
        }
    }
    Scaffold(
        topBar = {
            Row(
                modifier = modifier
                    .statusBarsPadding()
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = if (state.isSending) Arrangement.End else Arrangement.Start
            ) {
               if (state.isSending) {
                   UiIconButton(
                       icon = Icons.Filled.Close,
                       onClick = onBackPressed
                   )
               } else {
                   UiIconButton(
                       icon = Icons.AutoMirrored.Filled.ArrowBack,
                       onClick = onBackPressed
                   )
                   Text(
                       text = stringResource(R.string.complete_form),
                       style = MaterialTheme.typography.titleLarge,
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
                    text = stringResource(R.string.wait),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center
                )
            } else {
                state.optics?.let { optics ->
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
                    Spacer(modifier = modifier.height(16.dp))
                }
                UiTextField(
                    label = stringResource(R.string.fio),
                    content = state.name,
                    onClickContent = {
                        onAction(FormScreenAction.UpdateName(it))
                    }
                )
                Spacer(modifier = modifier.height(16.dp))
                UiTextField(
                    label = stringResource(R.string.phone),
                    content = state.phone,
                    isOnlyDigit = true,
                    keyboardType = KeyboardType.Number,
                    visualTransformation = MaskVisualTransformation(RUSSIAN_PHONE_MASK),
                    onClickContent = {
                        onAction(FormScreenAction.UpdatePhone(it))
                    }
                )
                Spacer(modifier = modifier.height(16.dp))
                UiTextField(
                    modifier = modifier.clickable {
                        showDatePicker = true
                        keyboardController?.hide()
                    },
                    label = stringResource(R.string.date),
                    content = state.dateRecord,
                    readOnly = true,
                    enabled = false,
                    onClickContent = {}
                )
                Spacer(modifier = modifier.height(16.dp))
                UiTextField(
                    modifier = modifier.clickable {
                        showTimePicker = true
                        keyboardController?.hide()
                    },
                    label = stringResource(R.string.time),
                    enabled = false,
                    content = state.timeRecord,
                    readOnly = true,
                    onClickContent = {}
                )
                Spacer(modifier = modifier.height(16.dp))
                UiTextField(
                    label = stringResource(R.string.reason),
                    content = state.reason,
                    onClickContent = {
                        onAction(FormScreenAction.UpdateReason(it))
                    }
                )
                Spacer(modifier = modifier.height(16.dp))
                Button(
                    modifier = modifier,
                    contentPadding = PaddingValues(
                        vertical = 8.dp,
                        horizontal = 16.dp
                    ),
                    enabled = state.phone.isNotBlank()
                            && state.name.isNotBlank()
                            && state.reason.isNotBlank()
                            && state.dateRecord != "--.--.----"
                            && state.timeRecord != "--:--",
                    colors = ButtonDefaults.buttonColors(
                        containerColor = violet
                    ),
                    onClick = {
                        onAction(FormScreenAction.OnSend)
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
    FormScreenScreen(
        state = FormScreenState(
            optics = mockOptics.first()
        ),
        onAction = {},
        onBackPressed = {},

        )
}

@Preview(showSystemUi = true)
@Composable
private fun Preview2() {
    FormScreenScreen(
        state = FormScreenState(
            optics = mockOptics.first(),
            isSending = true
        ),
        onAction = {},
        onBackPressed = {},

        )
}