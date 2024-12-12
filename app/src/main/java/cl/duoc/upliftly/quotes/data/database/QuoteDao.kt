package cl.duoc.upliftly.quotes.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull

@Dao
interface QuoteDao {
    @Query("SELECT * FROM quotes")
    fun getAllQuotes(): Flow<List<QuoteEntity>>

    @Query("SELECT * FROM quotes WHERE id = :quoteId")
    suspend fun isFavoriteQuote(quoteId: Int): QuoteEntity?

    @Query("DELETE FROM quotes WHERE id = :id")
    suspend fun deleteQuote(id: Int)

    @Upsert
    suspend fun insertQuote(quoteEntity: QuoteEntity)
}
