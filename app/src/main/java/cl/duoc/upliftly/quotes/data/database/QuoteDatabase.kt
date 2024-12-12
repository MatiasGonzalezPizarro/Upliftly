package cl.duoc.upliftly.quotes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [QuoteEntity::class], version = 1, exportSchema = false)
abstract class QuoteDatabase: RoomDatabase() {
    abstract fun quoteDao(): QuoteDao


}