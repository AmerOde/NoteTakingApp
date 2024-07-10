package com.app.amerodeh.notetakingappkotlin.room



import android.content.Context
import android.content.LocusId
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase(){

    abstract fun getNoteDao() : NoteDao

    // Singleton Design Pattern
    // @Volatile : This annotation in JVM backing field of the annotated property as volatile, that right to this field made visible to other threads ,so any change to this instance or any rewrite to this instance would be immediately made visible to other threads
    // Any() : is a function that it is added as an extension to iterable and map interfaces which take a HOF as param to predicate to condition and return boolean as true , if any  of the item in the list confirms that condition otherwise it will false
    companion object{
        @Volatile
        private var instance: NoteDatabase ?= null
        private var LOCK =Any()
        operator fun invoke(context: Context) = instance?:
        synchronized(LOCK){
            instance ?:
                    createDataBase(context).also {
                        instance =it
                    }

            }
        private fun createDataBase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                NoteDatabase::class.java,name = "note_db"
            ).build()


    }





    }
