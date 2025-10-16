package com.example.monilki2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var contactController: ContactController
    private lateinit var textView: TextView
    private lateinit var addButton: Button
    private lateinit var editButton: Button
    private lateinit var prevButton: ImageButton
    private lateinit var nextButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contactController = ViewModelProvider(this).get(ContactController::class.java)

        initViews()
        initListeners()
        updateTextView()
    }

    private fun initViews() {
        textView = findViewById(R.id.textView)
        addButton = findViewById(R.id.button)
        editButton = findViewById(R.id.button2)
        prevButton = findViewById(R.id.imageButton3)
        nextButton = findViewById(R.id.imageButton2)
    }

    private fun initListeners() {
        addButton.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)
            intent.putExtra("mode", "add")
            startActivity(intent)
        }
        editButton.setOnClickListener {
            if (contactController.getContactsCount() > 0) {
                val intent = Intent(this, EditActivity::class.java)
                intent.putExtra("mode", "edit")
                startActivity(intent)
            }
        }
        prevButton.setOnClickListener {
            if (contactController.moveToPrevious()) {
                updateTextView()
            }
        }
        nextButton.setOnClickListener {
            if (contactController.moveToNext()) {
                updateTextView()
            }
        }
    }

    private fun updateTextView() {
        val currentContact = contactController.getCurrentContact()
        val currentIndex = contactController.getCurrentIndex()
        val totalContacts = contactController.getContactsCount()

        if (currentContact != null) {
            val displayText = """
                ${currentContact.lastname} ${currentContact.firstname} ${currentContact.middlename}
                ${currentContact.phoneNumber}""".trimIndent()

            textView.text = displayText
        } else {
            textView.text = "Нет контактов"
        }

        prevButton.isEnabled = currentIndex > 0
        nextButton.isEnabled = currentIndex < totalContacts - 1
        editButton.isEnabled = totalContacts > 0
    }

    override fun onResume() {
        super.onResume()
        updateTextView()
    }
}