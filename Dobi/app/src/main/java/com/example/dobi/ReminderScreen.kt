@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.dobi

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*


data class MedicationReminder(val name: String, val time: String, val note: String, val id:Int = 0)
data class Alarm(val name: String, val time: String, val note: String, val id:Int = 0, val u:String = cna )

val userAlarmsRef = database.getReference("Alarms")

// Function to add an alarm
fun addAlarmToDatabase(alarmData: Alarm) {
    userAlarmsRef?.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val alarmCount = snapshot.childrenCount.toInt()

            // Generate the next alarm name using the count
            val alarmName = "alarm${alarmCount + 1}"

            // Set the alarm data in the database
            val alarmRef = userAlarmsRef?.child(alarmName)
            alarmRef?.setValue(alarmData)
        }

        override fun onCancelled(error: DatabaseError) {
            // Handle error
        }
    })
}

//fun getAlarmsForUser(user: String, callback: (List<Alarm>) -> Unit) {
//    val alarmsRef = FirebaseDatabase.getInstance().getReference("Alarms")
//
//    // Query to filter alarms where 'u' equals the specified user
//    val query = alarmsRef.orderByChild("u").equalTo(user)
//
//    query.addListenerForSingleValueEvent(object : ValueEventListener {
//        override fun onDataChange(snapshot: DataSnapshot) {
//            val alarmsList = mutableListOf<Alarm>()
//            for (alarmSnapshot in snapshot.children) {
//                val alarm = alarmSnapshot.getValue(Alarm::class.java)
//                if (alarm != null) {
//                    alarmsList.add(alarm)
//                }
//            }
//            callback(alarmsList)
//        }
//
//        override fun onCancelled(error: DatabaseError) {
//            // Handle error
//            println("Error fetching data: ${error.message}")
//        }
//    })
//}


fun parseTimeToMillis(time: String): Long {
    val dateFormat = SimpleDateFormat("HH:mm")
    val date: Date = dateFormat.parse(time)

    // Get the current time in milliseconds
    val currentTime = Calendar.getInstance()

    // Set the time from the parsed date
    val reminderTime = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, date.hours)
        set(Calendar.MINUTE, date.minutes)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)

        // If the reminder time is before the current time, schedule for the next day
        if (before(currentTime)) {
            add(Calendar.DAY_OF_MONTH, 1)
        }
    }

    return reminderTime.timeInMillis
}

fun callUserAlarms(){

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddReminderDialog(onDismiss: () -> Unit, onAdd: (String, String, String) -> Unit) {
    var name by remember { mutableStateOf(TextFieldValue()) }
    var note by remember { mutableStateOf(TextFieldValue()) }
    var time by remember { mutableStateOf(Calendar.getInstance().time) }
    val context = LocalContext.current
    var showTimePicker by remember { mutableStateOf(false) }

    if (showTimePicker) {
        val calendar = Calendar.getInstance()
        TimePickerDialog(
            context,
            { _, hourOfDay, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                calendar.set(Calendar.MINUTE, minute)
                time = calendar.time
                showTimePicker = false
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            false
        ).show()
    }

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
                    modifier = Modifier.clickable(onClick = { showTimePicker = true })
                        .border(BorderStroke(1.dp, Color.Gray))
                        .padding(34.dp),
                    text = "Selected Time: ${SimpleDateFormat("hh:mm a", Locale.getDefault()).format(time)}",
                    style = MaterialTheme.typography.labelLarge,
                    fontSize = 18.sp,
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        },
        confirmButton = {
            Button(onClick = {
                val formattedTime = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(time)
                onAdd(name.text, formattedTime, note.text)

                // Schedule notification
                val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                val intent = Intent(context, ReminderReceiver::class.java).apply {
                    putExtra("name", name.text)
                    putExtra("note", note.text)
                }
                val pendingIntent = PendingIntent.getBroadcast(
                    context,
                    System.currentTimeMillis().toInt(),
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
                alarmManager.setExact(
                    AlarmManager.RTC_WAKEUP,
                    time.time,
                    pendingIntent
                )

                onDismiss()
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
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicationReminderScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val notificationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted: Boolean ->
            if (!isGranted) {
                // Handle the case where permission is not granted
            }
        }
    )

    LaunchedEffect(Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                notificationPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }
    var reminders by remember {
        mutableStateOf(
            listOf(
                    MedicationReminder("White Pill", "09:00 PM", "note 1"),
            MedicationReminder("Blue Pill", "09:30 PM", "note 2")
        )
        )
    }
    var showDialog by remember { mutableStateOf(false) }

    // Call this function to update the reminders list
//    LaunchedEffect(Unit) {
//        getAlarmsForUser(cna) { alarms ->
//            val newReminders = alarms.map { alarm ->
//                MedicationReminder(alarm.name, alarm.time, alarm.note)
//            }
//            reminders = newReminders
//        }
//    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Beck",
                            modifier = Modifier
                                .size(48.dp)
                                .fillMaxWidth()
                                .padding(end = 8.dp),
                            tint = Color.White,
                        )
                    }
                },
                title = { Text(text = "Medication Reminder", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(139, 188, 62, 255),
                    titleContentColor = Color(255, 255, 255),
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
                    .background(Color( 254,
                        242,
                        172,
                        255))
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
                val alarmData = Alarm(name, time, note)
                addAlarmToDatabase(alarmData)
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
                Text(text = reminder.time.toString(), fontSize = 18.sp, color = Color.Gray)
                IconButton(onClick = onDelete) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
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




class ReminderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val name = intent.getStringExtra("name") ?: return
        val note = intent.getStringExtra("note") ?: return

        val channelId = "medication_reminder_channel"
        val channelName = "Medication Reminder"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = "Channel for medication reminders"
            }
            val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.drop)
            .setContentTitle(context.getString(R.string.medication_reminder))
            .setContentText(context.getString(R.string.medication_reminder_time,name,note))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        // Check for notification permission
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            with(NotificationManagerCompat.from(context)) {
                notify(System.currentTimeMillis().toInt(), builder.build())
            }
        }
    }
}