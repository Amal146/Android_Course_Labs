package com.example.dobi

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent

class NotificationAlarmScheduler(
    private val context: Context
) : AlarmScheduler {

    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    override fun createPendingIntent(reminderItem: MedicationReminder): PendingIntent {
        val intent = Intent(context, ReminderReceiver::class.java).apply {
            putExtra("name", reminderItem.name)
            putExtra("note", reminderItem.note)
        }

        return PendingIntent.getBroadcast(
            context,
            reminderItem.id,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    override fun schedule(reminderItem: MedicationReminder) {
        val timeInMillis = parseTimeToMillis(reminderItem.time)
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            timeInMillis,
            AlarmManager.INTERVAL_DAY,
            createPendingIntent(reminderItem)
        )
    }

    override fun cancel(reminderItem: MedicationReminder) {
        alarmManager.cancel(
            createPendingIntent(reminderItem)
        )
    }
}
