import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "ContactsDB"
        private const val TABLE_CONTACTS = "contacts"

        private const val KEY_ID = "_id"
        private const val KEY_NAME = "name"
        private const val KEY_TELEPHONE = "telephone"
        private const val KEY_GROUP = "contact_group"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = ("CREATE TABLE $TABLE_CONTACTS ($KEY_ID INTEGER PRIMARY KEY, "
                + "$KEY_NAME TEXT, $KEY_TELEPHONE TEXT, $KEY_GROUP TEXT)")
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_CONTACTS")
        onCreate(db)
    }
}

