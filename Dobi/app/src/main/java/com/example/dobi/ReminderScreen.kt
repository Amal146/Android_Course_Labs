@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.dobi

import android.app.TimePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import java.text.SimpleDateFormat
import java.util.*


@OptIn(ExperimentalMaterial3Api::class)

data class MedicationReminder(val name: String, val time: String, val note: String)


@Composable
fun EditTime(state: TimePickerState) {
    TimePicker(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 30.dp),
        state = state,
        colors = TimePickerDefaults.colors(),
        layoutType = TimePickerDefaults.layoutType()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddReminderDialog(onDismiss: () -> Unit, onAdd: (String, String, String) -> Unit) {
    var name by remember { mutableStateOf(TextFieldValue()) }
    var note by remember { mutableStateOf(TextFieldValue()) }
    val calendar = Calendar.getInstance()
    var time by remember { mutableStateOf(calendar.time) }
    val state = rememberTimePickerState()

    // State variable to control the visibility of the TimePickerDialog
    var showDialog by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Add Medication Reminder") },
        text = {
            Column {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Medication Name") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = note,
                    onValueChange = { note = it },
                    label = { Text("Note") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier.clickable(onClick = { showDialog = true }),
                    text = "Selected Time: ${SimpleDateFormat("hh:mm a", Locale.getDefault()).format(time)}",
                    style = MaterialTheme.typography.labelLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        },
        confirmButton = {
            Button(onClick = {
                val formattedTime = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(time)
                onAdd(name.text, formattedTime, note.text)
            }) {
                Text("Add")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )

    // Display the TimePickerDialog conditionally
    if (showDialog) {
        TimePickerDialog(
            state = state,
            onCancel = { showDialog = false },
            onConfirm = {
                showDialog = false
                // Update the time when confirmed
                calendar.set(Calendar.HOUR_OF_DAY, state.selectedTime.hour)
                calendar.set(Calendar.MINUTE, state.selectedTime.minute)
                time = calendar.time
            },
            toggle = { /* You can add additional UI elements here if needed */ },
            content = {
                EditTime(state = state)
            }
        )
    }
}


@Composable
fun MedicationReminderScreen(navController: NavHostController , modifier: Modifier = Modifier) {
    var reminders by remember { mutableStateOf(listOf(
        MedicationReminder("White Pill", "09:30 AM", "note 1"),
        MedicationReminder("Blue Pill", "05:30 PM", "note 2")
    )) }
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Medication Reminder", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(139, 188, 62, 255),
                    titleContentColor = Color(255,255,255) ,
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true },

                ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        },
        content = { paddingValues ->
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(Color(0xFFE0D575))
                    .padding(paddingValues)
            ) {
                LazyColumn(modifier = Modifier.padding(16.dp)) {
                    items(reminders) { reminder ->
                        MedicationReminderItem(
                            reminder = reminder,
                            onDelete = {
                                reminders = reminders.toMutableList().apply { remove(reminder) }
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    )

    if (showDialog) {
        AddReminderDialog(
            onDismiss = { showDialog = false },
            onAdd = { name, time, note ->
                reminders = reminders + MedicationReminder(name, time, note)
                showDialog = false
            }
        )
    }
}

@Composable
fun MedicationReminderItem(reminder: MedicationReminder, onDelete: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = reminder.name, fontSize = 18.sp)
                Text(text = reminder.note, fontSize = 14.sp, color = Color.Gray)
            }
            Row {
                Text(text = reminder.time, fontSize = 18.sp, color = Color.Gray)
                IconButton(onClick = onDelete) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                }
            }
        }
    }
}

@Composable
fun TimePickerDialog(
    title: String = "Select Time",
    onCancel: () -> Unit,
    onConfirm: () -> Unit,
    toggle: @Composable () -> Unit = {},
    content: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = onCancel,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
    ) {
        Surface(
            shape = MaterialTheme.shapes.extraLarge,
            tonalElevation = 6.dp,
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .height(IntrinsicSize.Min)
                .background(
                    shape = MaterialTheme.shapes.extraLarge,
                    color = MaterialTheme.colorScheme.surface
                ),
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    text = title,
                    style = MaterialTheme.typography.labelMedium
                )
                content()
                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                ) {
                    toggle()
                    Spacer(modifier = Modifier.weight(1f))
                    TextButton(
                        onClick = onCancel
                    ) { Text("Cancel") }
                    TextButton(
                        onClick = onConfirm
                    ) { Text("OK") }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MedicationReminderScreenPreview() {
    MedicationReminderScreen(navController = rememberNavController())
}

