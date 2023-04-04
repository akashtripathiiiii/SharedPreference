package com.example.sharedpreference

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var name : EditText
    lateinit var emailId : EditText
    lateinit var save : Button
    lateinit var sharedPreferences: SharedPreferences
    val Name = "nameKey" //key1
    val Email = "emailKey"  //key2
    val myFile = "mypreFile" //preferenceFile

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        name=findViewById(R.id.nameEdit)
        emailId=findViewById(R.id.emailEdit)
        save=findViewById(R.id.saveBtn)
        sharedPreferences=getSharedPreferences(myFile,Context.MODE_PRIVATE)
    }
    fun save(v: View){
        val n=name.text.toString()
        val e=emailId.text.toString()
        val editor = sharedPreferences.edit()
        editor.putString(Name,n)
        editor.putString(Email,e)
        editor.apply()

        Toast.makeText(this,"Saved "+n+e,Toast.LENGTH_SHORT).show()
    }
}