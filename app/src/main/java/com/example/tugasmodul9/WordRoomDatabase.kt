package com.example.tugasmodul9

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database (entities = arrayOf(Word::class), version = 1, exportSchema = false)
public abstract class WordRoomDatabase : RoomDatabase(){
    abstract fun wordDao(): WordDao

//membuat dan pengelolaan database
    private class  WordDataBaseCallBack (
        private val scope: CoroutineScope) : RoomDatabase.Callback(){
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.wordDao())
                }
            }
        }
        suspend fun populateDatabase(wordDao: WordDao){
            wordDao.deleteALL()
//penambahan word sebagi default tampilan awal
            var word = Word("Baitun")
            wordDao.insert(word)
            word = Word("Nadhiroh")
            wordDao.insert(word)
        }
    }
    companion object {
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null
//pengelolaan room database
        fun getDatabase(context: Context, scope: CoroutineScope): WordRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,WordRoomDatabase::class.java,
                    "word_database")
                    .addCallback(WordDataBaseCallBack(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}