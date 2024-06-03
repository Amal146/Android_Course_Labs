package com.example.alarmreceiver

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Build.*
import android.os.Build.VERSION.*
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat.getSystemService
import com.example.alarmreceiver.ui.theme.AlarmReceiverTheme
import java.util.Calendar


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlarmReceiverTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    createNotificationChannel()
                }
            }
        }
    }
}
private fun createNotificationChannel() {
    if (SDK_INT >= VERSION_CODES.O) {
        val channelId = "notification_channel"
        val channelName = "Daily Notification"
        val channelDescription = "Daily notification reminder"

        val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
        channel.description = channelDescription
        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }
}

private fun scheduleNotification(context: Context, timeInMillis: Long) {
    val alarmManager = context.getSystemService(AlarmManager::class.java)
    val intent = Intent(context, AlarmReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    alarmManager.setRepeating(
        AlarmManager.RTC_WAKEUP,
        timeInMillis,
        AlarmManager.INTERVAL_DAY,
        pendingIntent
    )
}
}

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // Create notification
        val notificationManager = context.getSystemService(NotificationManager::class.java)
        val notificationBuilder = NotificationCompat.Builder(context, "notification_channel")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("Daily Reminder")
            .setContentText("This is your daily reminder!")
            .setAutoCancel(true)

        notificationManager.notify(1, notificationBuilder.build())
    }
}

@Composable
fun NotificationScreen() {
    val isNotificationEnabled = remember { mutableStateOf(false) }

    Column {
        Text("Daily Notification App")
        Button(onClick = {
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, 23)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)

            val timeInMillis = calendar.timeInMillis

            if (isNotificationEnabled.value) {
                scheduleNotification(applicationContext, timeInMillis)
                Toast.makeText(applicationContext, "Notification scheduled", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Notification disabled", Toast.LENGTH_SHORT).show()
            }
            isNotificationEnabled.value = !isNotificationEnabled.value
        }) {
            Text(if (isNotificationEnabled.value) "Disable Notification" else "Enable Notification")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NotificationScreen()
}

