package com.example.noteapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Note::class], version = 1)
@TypeConverters(Converters::class)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun getNoteDao(): NoteDao

    companion object {
        private const val DATABASE_NAME = "note_database"

        // For Singleton instantiation
        @Volatile    //  this annotation makes sure that the value of instance is always up-to-date
        private var instance: NoteDatabase? = null

        /*  this synchronized block is used to make sure that only one instance of the database is created
       at a time. This is necessary because creating a database instance is a heavy operation
       and we don't want to create multiple instances of the database.  */
        fun getInstance(context: Context): NoteDatabase {
            return instance ?: synchronized(this) {
                buildDatabase(context).also { instance = it }
            }
        }
//-------------------------------TEMP-------------------------------------------------------------------------------------------------------------------------------------------------
        fun getInstanceWithoutContext(): NoteDatabase {
            return instance!!
        }
//-------------------------------TEMP-------------------------------------------------------------------------------------------------------------------------------------------------

        private fun buildDatabase(context: Context): NoteDatabase {
            return Room.databaseBuilder(
                context = context, klass = NoteDatabase::class.java, name = DATABASE_NAME
            ).build()
        }
    }
}