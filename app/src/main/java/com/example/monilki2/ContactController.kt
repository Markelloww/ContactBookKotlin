package com.example.monilki2

import androidx.lifecycle.ViewModel

class ContactController : ViewModel() {
    fun getCurrentContact(): Contact? = ContactRepository.getCurrentContact()
    fun getCurrentIndex(): Int = ContactRepository.getCurrentIndex()
    fun getContactsCount(): Int = ContactRepository.getContactsCount()
    fun moveToNext(): Boolean = ContactRepository.moveToNext()
    fun moveToPrevious(): Boolean = ContactRepository.moveToPrevious()
    fun addContact(contact: Contact) = ContactRepository.addContact(contact)
    fun updateContact(contact: Contact) = ContactRepository.updateContact(contact)
}