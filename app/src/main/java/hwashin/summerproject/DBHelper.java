package hwashin.summerproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context){
        super(context, "Join.db", null,1);
    }

    /*@Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.disableWriteAheadLogging();
    }*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table if not exists JoinTable("+
                "id text primary key, "+
                "password text not null, "+
                "name text not null, "+
                "email text not null, "+
                "phone integer not null" + ")";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
