// MainActivity.kt

fun addContact() {
    val name = findViewById<EditText>(R.id.name).text.toString()
    val telephone = findViewById<EditText>(R.id.telephone).text.toString()
    val group = findViewById<EditText>(R.id.group).text.toString()

    if (name.isNotEmpty() && telephone.isNotEmpty() && group.isNotEmpty()) {
        contacts.add(Contact(name, telephone, group))
        renderContacts(contacts)
        clearForm()
    } else {
        Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
    }
}

fun renderContacts(contactsToRender: List<Contact>) {
    val contactList = findViewById<LinearLayout>(R.id.contact_list)
    contactList.removeAllViews()
    contactsToRender.forEachIndexed { index, contact ->
        val contactItem = layoutInflater.inflate(R.layout.contact_item, null)
        val contactDetails = contactItem.findViewById<TextView>(R.id.contact_details)
        contactDetails.text = "${contact.name} - ${contact.telephone} - ${contact.group}"
        val deleteButton = contactItem.findViewById<Button>(R.id.delete_button)
        deleteButton.setOnClickListener { deleteContact(index) }
        val editButton = contactItem.findViewById<Button>(R.id.edit_button)
        editButton.setOnClickListener { showEditModal(index) }
        contactList.addView(contactItem)
    }
}

fun showEditModal(index: Int) {
    val editModal = findViewById<ConstraintLayout>(R.id.edit_modal)
    editModal.visibility = View.VISIBLE

    val contact = contacts[index]
    val editName = findViewById<EditText>(R.id.edit_name)
    val editTelephone = findViewById<EditText>(R.id.edit_telephone)
    val editGroup = findViewById<EditText>(R.id.edit_group)
    editName.setText(contact.name)
    editTelephone.setText(contact.telephone)
    editGroup.setText(contact.group)

    val saveButton = findViewById<Button>(R.id.save_button)
    saveButton.setOnClickListener {
        val newName = editName.text.toString()
        val newTelephone = editTelephone.text.toString()
        val newGroup = editGroup.text.toString()
        if (newName.isNotEmpty() && newTelephone.isNotEmpty() && newGroup.isNotEmpty()) {
            contacts[index] = Contact(newName, newTelephone, newGroup)
            renderContacts(contacts)
            editModal.visibility = View.GONE
        } else {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
        }
    }
}

fun deleteContact(index: Int) {
    contacts.removeAt(index)
    renderContacts(contacts)
}

fun clearForm() {
    findViewById<EditText>(R.id.name).text.clear()
    findViewById<EditText>(R.id.telephone).text.clear()
    findViewById<EditText>(R.id.group).text.clear()
}

fun searchContact() {
    val searchName = findViewById<EditText>(R.id.search_name).text.toString().toLowerCase(Locale.getDefault())
    val filteredContacts = contacts.filter { it.name.toLowerCase(Locale.getDefault()).contains(searchName) }
    renderContacts(filteredContacts)
}

fun searchGroup() {
    val searchGroup = findViewById<EditText>(R.id.search_group).text.toString().toLowerCase(Locale.getDefault())
    val filteredContacts = contacts.filter { it.group.toLowerCase(Locale.getDefault()).contains(searchGroup) }
    renderContacts(filteredContacts)
}

fun showAllContacts() {
    renderContacts(contacts)
}

