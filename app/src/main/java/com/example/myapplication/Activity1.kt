package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class Activity1 : AppCompatActivity() {
    private lateinit var text: EditText
    var sheetNumber = 0
    var colors = arrayOf(0xFFAC62E1, 0xFFF4C00A, 0xA1F80101)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity1)
        sheetNumber = getIntent().getIntExtra("sheetNumber", 0)

        text = findViewById(R.id.text)
        val next: Button = findViewById(R.id.next3)
        next.setOnClickListener {
            if(sheetNumber < colors.size - 1) {
                val intent = Intent(this, this::class.java)
                intent.putExtra("sheetNumber", sheetNumber + 1)
                startActivity(intent)
            }
            else
            {
                Toast.makeText(applicationContext, (R.string.stop), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        val prefs = getPreferences(Context.MODE_PRIVATE).edit()
        prefs.putString("note"+sheetNumber, text.editableText.toString())
        prefs.apply()
    }

    override fun onResume() {
        super.onResume()
        val sheet : ConstraintLayout = findViewById(R.id.sheet)
        sheet.setBackgroundColor(colors[sheetNumber].toInt())
        val saveState = getPreferences(MODE_PRIVATE).getString("note"+sheetNumber.toString(), null)
        if (saveState != null) {
            text.setText(saveState)
        }
    }
}