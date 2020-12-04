package com.example.tugasmodul9

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class NewWordActivity : AppCompatActivity() {
    private lateinit var editWordView: EditText
    val replyIntent = Intent()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)
        editWordView = findViewById(R.id.edit_word)
//untuk pengelolaan button save
        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener{
            if (TextUtils.isEmpty(editWordView.text)){
                setResult(Activity.RESULT_CANCELED,replyIntent)
            }

            else {
                val word = editWordView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY,word)
                setResult(Activity.RESULT_OK, replyIntent)
                Toast.makeText(applicationContext,
                    "Data Tersimpan", Toast.LENGTH_LONG).show()
            }
            finish()
        }
    }//untuk mengakses member daari suatu class
    companion object{
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }
}