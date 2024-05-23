// ContactDao.kt

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ContactDao {
    @Insert
    suspend fun addContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Query("SELECT * FROM contacts")
    suspend fun getAllContacts(): List<Contact>

    @Query("SELECT * FROM contacts WHERE name = :name")
    suspend fun getContactsByName(name: String): List<Contact>

    @Query("SELECT * FROM contacts WHERE contact_group = :group")
    suspend fun getContactsByGroup(group: String): List<Contact>

    @Update
    suspend fun updateContact(contact: Contact)
}
