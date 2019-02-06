package edu.cascadia.mobas.gybitg.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import edu.cascadia.mobas.gybitg.db.typeconverters.DateTypeConverter;
import edu.cascadia.mobas.gybitg.models.Stat;

@Database(entities = {Stat.class}, version = 1)
@TypeConverters({DateTypeConverter.class})
public abstract class gybitgDatabase extends RoomDatabase {

    private static gybitgDatabase INSTANCE;
    private static final String DB_NAME = "gybitg.db";

    public abstract StatDao statDao();

    public static gybitgDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (gybitgDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                gybitgDatabase.class, DB_NAME)
                                .allowMainThreadQueries()   // should not be used in production
                                .build();
                }
            }
        }
        return INSTANCE;
    }
}
