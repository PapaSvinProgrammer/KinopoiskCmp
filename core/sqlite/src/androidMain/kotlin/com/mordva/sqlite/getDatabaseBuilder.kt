package com.mordva.sqlite

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

internal fun getDatabase(ctx: Context): AppDatabase {
    return getDatabaseBuilder(ctx).build()
}

internal fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<AppDatabase> {
    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath("kinopoisk_kmp.db")
    return Room.databaseBuilder<AppDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}