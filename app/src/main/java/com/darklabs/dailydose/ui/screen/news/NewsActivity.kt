package com.darklabs.dailydose.ui.screen.news

import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import com.darklabs.businessdomain.db.database.entity.NewsArticleEntity
import com.darklabs.dailydose.base.BaseActivity
import com.darklabs.dailydose.ui.DailyDoseTheme
import com.darklabs.dailydose.util.ViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsActivity : BaseActivity<NewsViewModel>() {

    private val newsViewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DailyDoseTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val state: ViewState<List<NewsArticleEntity>>? by newsViewModel.newsArticle.observeAsState()
                    DrawLayout(state)
                }
            }
        }
    }

    @Composable
    fun DrawLayout(state: ViewState<List<NewsArticleEntity>>?) {
        when (state) {
            is ViewState.Success -> DrawList(state.data)
            is ViewState.Loading -> LoadingState()
            is ViewState.Error -> ErrorState()
        }
    }


    @Composable
    fun DrawList(data: List<NewsArticleEntity>) {
        LazyColumnFor(items = data) { item ->
            Box(
                modifier = Modifier.padding(10.dp).background(color = Color.Gray)
            ) {
                Text(text = item.content ?: "")
            }
        }
    }

    @Composable
    fun LoadingState(loadingText: String = "Loading") {
        Column {
            Text(text = loadingText)
        }
    }

    @Composable
    fun ErrorState(errorText: String = "Error") {
        Column {
            Text(text = errorText)
        }
    }
}
