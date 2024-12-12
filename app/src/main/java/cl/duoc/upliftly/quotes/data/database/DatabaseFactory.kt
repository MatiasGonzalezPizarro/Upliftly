package cl.duoc.upliftly.quotes.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

object DatabaseFactory {
    fun getDatabaseBuilder(ctx: Context): RoomDatabase.Builder<QuoteDatabase> {
        val appContext = ctx.applicationContext
        val dbFile = appContext.getDatabasePath("my_room.db")
        return Room.databaseBuilder<QuoteDatabase>(
            context = appContext,
            name = dbFile.absolutePath,
            klass = QuoteDatabase::class.java
        )
    }
}