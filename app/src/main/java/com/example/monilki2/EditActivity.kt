package com.example.monilki2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class EditActivity : AppCompatActivity() {
    private lateinit var contactController: ContactController

    private lateinit var lastNameEditText: EditText
    private lateinit var firstNameEditText: EditText
    private lateinit var middleNameEditText: EditText
    private lateinit var phoneNumberEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var cancelButton: Button

    private var mode: String = "add"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        contactController = ViewModelProvider(this).get(ContactController::class.java)

        mode = intent.getStringExtra("mode") ?: "add"

        initViews()
        initListeners()

        if (mode == "edit") {
            loadCurrentContact()
        }
    }

    private fun initViews() {
        lastNameEditText = findViewById(R.id.editTextText)
        firstNameEditText = findViewById(R.id.editTextText2)
        middleNameEditText = findViewById(R.id.editTextText3)
        phoneNumberEditText = findViewById(R.id.editTextText4)
        saveButton = findViewById(R.id.button3)
        cancelButton = findViewById(R.id.button4)
    }

    private fun initListeners() {
        saveButton.setOnClickListener {
            saveContact()
        }
        cancelButton.setOnClickListener {
            finish()
        }
    }

    private fun loadCurrentContact() {
        val currentContact = contactController.getCurrentContact()
        currentContact?.let { contact ->
            lastNameEditText.setText(contact.lastname)
            firstNameEditText.setText(contact.firstname)
            middleNameEditText.setText(contact.middlename)
            phoneNumberEditText.setText(contact.phoneNumber)
        }
    }

    private fun saveContact() {
        val contact = Contact(
            firstname = firstNameEditText.text.toString(),
            middlename = middleNameEditText.text.toString(),
            lastname = lastNameEditText.text.toString(),
            phoneNumber = phoneNumberEditText.text.toString()
        )

        if (mode == "add") {
            contactController.addContact(contact)
        } else {
            contactController.updateContact(contact)
        }

        finish()
    }
}