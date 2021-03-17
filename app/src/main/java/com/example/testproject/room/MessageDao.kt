package com.example.testproject.room

import androidx.room.*
import com.example.testproject.model.Message

@Dao
interface MessageDao {
    @Delete
    fun delete(message: Message?)

    @Query("DELETE FROM messages")
    fun deleteallmessages()

    @Query("SELECT * FROM messages WHERE consultation_request_id IN(:id)")
    fun getmessages(id: Int): List<Message?>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(message: Message?): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(message: Message?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(messages: List<Message?>?)

    @Query("SELECT * FROM messages")
    fun loadAll(): List<Message?>?
}