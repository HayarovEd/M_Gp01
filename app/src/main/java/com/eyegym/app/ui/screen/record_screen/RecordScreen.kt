package com.eyegym.app.ui.screen.record_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.eyegym.app.R
import com.eyegym.app.ui.theme.txfColor
import com.eyegym.app.ui.theme.violet
import com.eyegym.app.ui.uikit.UiIconButton
import org.koin.androidx.compose.koinViewModel

@Composable
fun RecordScreenRoot(
    viewModel: RecordScreenViewModel = koinViewModel(),
    bottomRoutes: @Composable () -> Unit,
    onNavigateToRecordForm: (Int) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    RecordScreenScreen(
        state = state,
        onAction = viewModel::onAction,
        bottomRoutes = bottomRoutes,
        onNavigateToRecordForm = onNavigateToRecordForm
    )
}

@Composable
private fun RecordScreenScreen(
    modifier: Modifier = Modifier,
    state: RecordScreenState,
    onAction: (RecordScreenAction) -> Unit,
    bottomRoutes: @Composable () -> Unit,
    onNavigateToRecordForm: (Int) -> Unit,
) {
    var selectedId by remember { mutableIntStateOf(-1) }
    var showButtonRecord by remember { mutableStateOf(false) }

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
                    text = stringResource(R.string.record_to_optics),
                    style = MaterialTheme.typography.titleLarge,
                )
            }
        },
        bottomBar = bottomRoutes
    ) { paddingValues ->
        Box(modifier = modifier
            .padding(paddingValues)
            .fillMaxSize()
            .padding(16.dp)) {
            Column(
                modifier = modifier
                    .fillMaxSize(),
            ) {
                OutlinedTextField(
                    modifier = modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(100.dp),
                    value = state.query,
                    onValueChange = {
                        onAction(RecordScreenAction.UpdateQuery(it))
                    },
                    textStyle = MaterialTheme.typography.bodyMedium,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = txfColor,
                        unfocusedContainerColor = txfColor,
                        unfocusedBorderColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent,
                    ),
                    placeholder = {
                        Text(
                            text = stringResource(R.string.optics_ph),
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    },
                    trailingIcon = {
                        UiIconButton(
                            icon = Icons.Default.Search,
                            onClick = {
                                showButtonRecord = false
                                onAction(RecordScreenAction.OnSearch)
                            }
                        )
                    }
                )
                Spacer(modifier = modifier.height(16.dp))
                LazyColumn(
                    modifier = modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    items(state.optics) { opt->
                        ItemOptics(
                            optics = opt,
                            onClickOptics = {
                                selectedId = opt.id
                                showButtonRecord = true
                            }
                        )
                    }
                }
            }
            if (showButtonRecord) {
                Button(
                    modifier = modifier.align(Alignment.BottomCenter),
                    contentPadding = PaddingValues(
                        vertical = 8.dp,
                        horizontal = 16.dp
                    ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = violet
                    ),
                    onClick = {
                        onNavigateToRecordForm(selectedId)
                    }
                ) {
                    Text(
                        text = stringResource(R.string.full_form),
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
    RecordScreenScreen(
        state = RecordScreenState(),
        bottomRoutes = {},
        onAction = {},
        onNavigateToRecordForm = {}
    )
}