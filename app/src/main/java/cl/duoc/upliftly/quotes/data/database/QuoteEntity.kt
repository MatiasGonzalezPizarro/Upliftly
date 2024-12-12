package cl.duoc.upliftly.quotes.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "quotes")
data class QuoteEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val quote: String,
)
