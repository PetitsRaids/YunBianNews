package com.petits_raids.yunbiannews.data.database;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.petits_raids.yunbiannews.YunBianApplication;
import com.petits_raids.yunbiannews.data.database.dao.GuokrDao;
import com.petits_raids.yunbiannews.data.database.dao.NewsDao;
import com.petits_raids.yunbiannews.data.model.guokr.GuokrItem;
import com.petits_raids.yunbiannews.data.model.news.News;

@Database(entities = {News.class, GuokrItem.class}, version = 4, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "AppDataBase.db";
    private static final Object LOCK = "LOCK";
    private static volatile AppDatabase instance;
    private static Migration migration_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `guokr_item` (`selfId` INTEGER NOT NULL, `id` INTEGER NOT NULL, `title` TEXT, `small_image` TEXT, `summary` TEXT, `resource_url` TEXT, PRIMARY KEY(`selfId`))");
        }
    };
    private static Migration migration_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE `news` ADD COLUMN `type` INTEGER NOT NULL DEFAULT 0");
        }
    };
    private static Migration migration_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE `guokr_item` ADD COLUMN `type` INTEGER NOT NULL DEFAULT 0");
        }
    };

    public static AppDatabase getInstance() {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = Room.databaseBuilder(YunBianApplication.getContext(), AppDatabase.class, DATABASE_NAME)
                            .addMigrations(migration_1_2, migration_2_3, migration_3_4)
                            .build();
                }
            }
        }
        return instance;
    }

    public abstract NewsDao newsDao();

    public abstract GuokrDao guokrDao();
}
