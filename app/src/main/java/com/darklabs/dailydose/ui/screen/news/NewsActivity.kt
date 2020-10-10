package com.darklabs.dailydose.ui.screen.news

import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnForIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.onActive
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darklabs.businessdomain.db.database.entity.NewsArticleEntity
import com.darklabs.dailydose.R
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
                    Scaffold(topBar = { Toolbar() }) {
                        val state: ViewState<List<NewsArticleEntity>>? by newsViewModel.newsArticle.observeAsState()
                        DrawLayout(state)
                    }
                }
            }
        }
    }

    @Composable
    fun DrawLayout(state: ViewState<List<NewsArticleEntity>>?) {
        when (state) {
            is ViewState.Success -> SuccessState(state.data)
            is ViewState.Loading -> LoadingState()
            is ViewState.Error -> ErrorState()
        }
    }


    @Composable
    fun SuccessState(data: List<NewsArticleEntity>) {
        LazyColumnForIndexed(items = data) { index, item ->
            if (index == data.lastIndex) {
                onActive {
                    newsViewModel.fetchNewsHeadLines()
                }
            }

            NewsHeadLineItem(item)
        }
    }

    @Composable
    private fun NewsHeadLineItem(item: NewsArticleEntity) {
        Card(
            modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp, top = 5.dp),
            elevation = 4.dp
        ) {
            Column(modifier = Modifier.padding(10.dp).fillMaxWidth()) {
                Text(text = item.title ?: "")
                Text(
                    text = item.author ?: getString(R.string.error_name_not_available),
                    color = MaterialTheme.colors.primary,
                    fontSize = 10.sp
                )
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

    @Composable
    private fun Toolbar() {
        TopAppBar(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = MaterialTheme.colors.primaryVariant
        ) {
            Row {
                Box(alignment = Alignment.Center) {
                    Text(
                        modifier = Modifier.fillMaxSize(),
                        text = getString(R.string.app_name),
                        style = MaterialTheme.typography.body1
                    )
                }

            }
        }
    }
}
