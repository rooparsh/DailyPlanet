package com.darklabs.businessdomain.db.database.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.darklabs.businessdomain.db.database.entity.NewsArticleEntity.NewsArticles.tableName
import com.darklabs.businessdomain.db.database.entity.NewsArticleEntity.NewsArticles.Column

@Entity(tableName = tableName)
data class NewsArticleEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Column.id)
    val id: Int = 0,

    @ColumnInfo(name = Column.author)
    val author: String? = null,

    @ColumnInfo(name = Column.title)
    val title: String? = null,

    @ColumnInfo(name = Column.description)
    val description: String? = null,

    @ColumnInfo(name = Column.url)
    val url: String? = null,

    @ColumnInfo(name = Column.urlToImage)
    val urlToImage: String? = null,

    @ColumnInfo(name = Column.publishedAt)
    val publishedAt: String? = null,

    @Embedded(prefix = "source_")
    val source: Source,

    @ColumnInfo(name = Column.content)
    val content: String? = null
) {

    data class Source(
        @ColumnInfo(name = Column.sourceId)
        val id: String? = null,

        @ColumnInfo(name = Column.sourceName)
        val name: String? = null
    )

    object NewsArticles {
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

            const val sourceId = "id"
            const val sourceName = "name"
        }
    }
}