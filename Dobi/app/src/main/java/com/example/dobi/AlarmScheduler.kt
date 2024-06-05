package com.example.dobi

import android.app.PendingIntent

interface AlarmScheduler {
    fun createPendingIntent(reminderItem: MedicationReminder): PendingIntent

    fun schedule(reminderItem: MedicationReminder)

    fun cancel(reminderItem: MedicationReminder)
}