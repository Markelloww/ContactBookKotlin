package com.example.monilki2

object ContactRepository {
    private val contacts = mutableListOf<Contact>()
    private var currentIndex = 0

    fun getContacts(): List<Contact> = contacts

    fun getCurrentContact(): Contact? {
        return if (contacts.isNotEmpty() && currentIndex < contacts.size) {
            contacts[currentIndex]
        } else {
            null
        }
    }

    fun getCurrentIndex(): Int = currentIndex

    fun getContactsCount(): Int = contacts.size

    fun moveToNext(): Boolean {
        if (currentIndex < contacts.size - 1) {
            currentIndex++
            return true
        }
        return false
    }

    fun moveToPrevious(): Boolean {
        if (currentIndex > 0) {
            currentIndex--
            return true
        }
        return false
    }

    fun addContact(contact: Contact) {
        contacts.add(contact)
        currentIndex = contacts.size - 1
    }

    fun updateContact(contact: Contact) {
        if (contacts.isNotEmpty() && currentIndex < contacts.size) {
            contacts[currentIndex] = contact
        }
    }

    fun setCurrentIndex(index: Int) {
        if (index in 0 until contacts.size) {
            currentIndex = index
        }
    }
}