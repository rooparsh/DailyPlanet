package com.darklabs.businessdomain.db.database

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.darklabs.businessdomain.db.database.dao.NewsArticleDao
import com.darklabs.businessdomain.db.database.entity.NewsArticleEntity
import com.darklabs.businessdomain.db.util.NewsDatabaseMigration

@Database(
    entities = [NewsArticleEntity::class],
    version = NewsDatabaseMigration.latestVersion
)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsArticleDao(): NewsArticleDao

    companion object {

        private const val databaseName = "news-db"

        fun buildDefault(context: Context) =
            Room.databaseBuilder(context, NewsDatabase::class.java, databaseName)
                .addMigrations(*NewsDatabaseMigration.allMigrations)
                .allowMainThreadQueries()
                .build()

        @VisibleForTesting
        fun buildTest(context: Context) =
            Room.inMemoryDatabaseBuilder(context, NewsDatabase::class.java)
                .build()
    }
}