// MainActivity.kt

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var contactDao: ContactDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = ContactDatabase.getDatabase(this)
        contactDao = database.contactDao()

        // Other initialization code
    }

    fun addContact() {
        val name = findViewById<EditText>(R.id.name).text.toString()
        val telephone = findViewById<EditText>(R.id.telephone).text.toString()
        val group = findViewById<EditText>(R.id.group).text.toString()

        if (name.isNotEmpty() && telephone.isNotEmpty() && group.isNotEmpty()) {
            val contact = Contact(name = name, telephone = telephone, group = group)
            lifecycleScope.launch(Dispatchers.IO) {
                contactDao.addContact(contact)
            }
            Toast.makeText(this, "Contact added successfully", Toast.LENGTH_SHORT).show()
            renderContacts()
            clearForm()
        } else {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteContact(contact: Contact) {
        lifecycleScope.launch(Dispatchers.IO) {
            contactDao.deleteContact(contact)
        }
        Toast.makeText(this, "Contact deleted successfully", Toast.LENGTH_SHORT).show()
        renderContacts()
    }

    fun searchByName(name: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            val contacts = contactDao.getContactsByName(name)
            renderContacts(contacts)
        }
    }

    fun searchByGroup(group: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            val contacts = contactDao.getContactsByGroup(group)
            renderContacts(contacts)
        }
    }

    fun updateContact(contact: Contact) {
        lifecycleScope.launch(Dispatchers.IO) {
            contactDao.updateContact(contact)
        }
        Toast.makeText(this, "Contact updated successfully", Toast.LENGTH_SHORT).show()
        renderContacts()
    }

    private fun renderContacts(contacts: List<Contact> = emptyList()) {
        
    }

    private fun clearForm() {
        findViewById<EditText>(R.id.name).text.clear()
        findViewById<EditText>(R.id.telephone).text.clear()
        findViewById<EditText>(R.id.group).text.clear()
    }
}
