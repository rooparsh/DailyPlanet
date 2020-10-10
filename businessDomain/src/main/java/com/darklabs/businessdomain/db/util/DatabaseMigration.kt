package com.darklabs.businessdomain.db.util

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

internal object NewsDatabaseMigration {
    const val latestVersion = 2

    val allMigrations: Array<Migration>
        get() = arrayOf(migration_1_2())

    private fun migration_1_2() = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE ${V2.NewsArticle.tableName} ADD COLUMN ${V2.NewsArticle.Column.content} TEXT DEFAULT NULL")
            database.execSQL("ALTER TABLE ${V2.NewsArticle.tableName} ADD COLUMN ${V2.NewsArticle.Column.sourceId} TEXT DEFAULT NULL")
            database.execSQL("ALTER TABLE ${V2.NewsArticle.tableName} ADD COLUMN ${V2.NewsArticle.Column.sourceName} TEXT DEFAULT \"\"")
        }
    }

    object V2 {

        object NewsArticle {
            const val tableName = "news_article"

            object Column {
                const val id = "id"
                const val author = "author"
                const val title = "title"
                const val description = "description"
                const val url = "url"
                const val urlToImage = "urlToImage"
                const val publishedAt = "publishedAt"
                const val content = "content"
                const val sourceId = "source_id"
                const val sourceName = "source_name"
            }
        }
    }

    object V1 {

        object NewsArticle {
            const val tableName = "news_article"

            object Column {
                const val id = "id"
                const val author = "author"
                const val title = "title"
                const val description = "description"
                const val url = "url"
                const val urlToImage = "urlToImage"
                const val publishedAt = "publishedAt"
            }
        }
    }
}