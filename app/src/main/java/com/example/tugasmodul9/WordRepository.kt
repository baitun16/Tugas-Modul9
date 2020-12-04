package com.example.tugasmodul9

import androidx.lifecycle.LiveData

class WordRepository(private val wordDao: WordDao){

    val allWords: LiveData<List<Word>> = wordDao.getAlphabetizedWords()
    //pengelolaan penyimpanan data inputan
    suspend fun insert(word: Word){
        wordDao.insert(word)
    }//pengelolaan penghapusan data
    suspend fun deleteALL(){
        wordDao.deleteALL()
    }
}