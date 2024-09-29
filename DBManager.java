import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.widget.Toast;

public class DBManager {

    private SQLiteDatabase sqlDB;
    private static final String DB_NAME = "Students";
    private static final String TABLE_NAME = "Logins";
    private static final String COL_USER_NAME = "UserName";
    private static final String COL_PASSWORD = "Password";
    private static final String COL_ID = "ID";
    private static final int DB_VERSION = 3;

    // SQL statement to create the Logins table
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
            "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_USER_NAME + " TEXT, " +
            COL_PASSWORD + " TEXT);";

    // Database helper class
    private static class DatabaseHelper extends SQLiteOpenHelper {
        private final Context context;

        DatabaseHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
            Toast.makeText(context, "Table created", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    // DBManager constructor
    public DBManager(Context context) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        sqlDB = dbHelper.getWritableDatabase();
    }

    // Insert new record into the Logins table
    public long insert(ContentValues values) {
        return sqlDB.insert(TABLE_NAME, null, values);
    }

    // Query the Logins table
    public Cursor query(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(TABLE_NAME);
        return qb.query(sqlDB, projection, selection, selectionArgs, null, null, sortOrder);
    }

    // Delete records from the Logins table
    public int delete(String selection, String[] selectionArgs) {
        return sqlDB.delete(TABLE_NAME, selection, selectionArgs);
    }

    // Update records in the Logins table
    public int update(ContentValues values, String selection, String[] selectionArgs) {
        return sqlDB.update(TABLE_NAME, values, selection, selectionArgs);
    }
}

// Imtiaz
