package com.example.sharedpreference

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var name : TextView
    lateinit var emailId : TextView
    lateinit var password : TextView
    lateinit var phone : TextView
    lateinit var date : DatePicker
    lateinit var save : Button
    lateinit var get : Button
    lateinit var clear : Button
    lateinit var sharedPreferences: SharedPreferences
    val Name = "nameKey" //key1
    val Email = "emailKey"  //key2
    val Password = "passwordKey" //key3
    val Phone = "phoneKey" //key4
    val Date = "dateKey" // key5
    val myFile = "mypreFileDemo" //preferenceFile

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        name=findViewById(R.id.nameEdit)
        emailId=findViewById(R.id.emailEdit)
        password=findViewById(R.id.passEdit)
        phone=findViewById(R.id.phoneEdit)
        date=findViewById(R.id.datePicker)
        save=findViewById(R.id.saveBtn)
        get=findViewById(R.id.getBtn)
        clear=findViewById(R.id.clearBtn)
        sharedPreferences=getSharedPreferences(myFile,Context.MODE_PRIVATE)
        name.setText(sharedPreferences.getString(Name,""))
        emailId.setText(sharedPreferences.getString(Email,""))
    }

    fun save(v: View){
        val n=name.text.toString()
        val e=emailId.text.toString()
        val p=password.text.toString()
        val ph=phone.text.toString()
        val d="${date.dayOfMonth}/${date.month+1}/${date.year}"
        val editor = sharedPreferences.edit()
        editor.putString(Name,n)
        editor.putString(Email,e)
        editor.putString(Password,p)
        editor.putString(Phone,ph)
        editor.putString(Date,d)
        editor.apply()

        Toast.makeText(this,"Saved "+n+" "+e+" "+p+" "+ph+" "+d+" ",Toast.LENGTH_SHORT).show()
    }

    fun clear(v: View){
        name.setText("")
        emailId.setText("")
        password.setText("")
        phone.setText("")
        date.updateDate(0,0,0)
    }

    fun get(v: View){
        sharedPreferences=getSharedPreferences(myFile,Context.MODE_PRIVATE)
        name.setText(sharedPreferences.getString(Name,""))
        emailId.setText(sharedPreferences.getString(Email,""))
        password.setText(sharedPreferences.getString(Password,""))
        phone.setText(sharedPreferences.getString(Phone,""))
        val savedDate = sharedPreferences.getString(Date, "")
        if (savedDate != "") {
            val dateParts = savedDate?.split("/")
            val savedDayOfMonth = dateParts?.get(0)?.toInt()
            val savedMonth = (dateParts?.get(1)?.toInt())?.minus(1)
            val savedYear = dateParts?.get(2)?.toInt()
            if (savedYear != null) {
                if (savedMonth != null) {
                    if (savedDayOfMonth != null) {
                        date.updateDate(savedYear, savedMonth, savedDayOfMonth)
                    }
                }
            }
        }
    }
}
