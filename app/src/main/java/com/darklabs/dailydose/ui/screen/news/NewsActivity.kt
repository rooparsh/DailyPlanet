package com.darklabs.dailydose.ui.screen.news

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnForIndexed
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.onActive
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import com.darklabs.businessdomain.db.database.entity.NewsArticleEntity
import com.darklabs.dailydose.R
import com.darklabs.dailydose.base.BaseActivity
import com.darklabs.dailydose.ui.DailyDoseTheme
import com.darklabs.dailydose.ui.component.ErrorState
import com.darklabs.dailydose.ui.component.LoadingState
import com.darklabs.dailydose.ui.component.Toolbar
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
                    Scaffold(topBar = { Toolbar(getString(R.string.app_name)) }) {
                        NewsScreen()
                    }
                }
            }
        }
    }

    @Composable
    @Preview
    fun NewsScreen() {
        val state: ViewState<List<NewsArticleEntity>>? by newsViewModel.newsArticle.observeAsState()
        handleState(state)

    }

    @Composable
    private fun handleState(state: ViewState<List<NewsArticleEntity>>?) {
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

            NewsHeadLineItem(item) { clickedItem ->
                Toast.makeText(this@NewsActivity,
                        "${clickedItem.id}",
                        Toast.LENGTH_LONG).show()
            }
        }
    }

    @Composable
    private fun NewsHeadLineItem(
            item: NewsArticleEntity,
            onHeadLineClick: (NewsArticleEntity) -> Unit) {

        Card(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, top = 5.dp)
                .clickable(onClick = { onHeadLineClick(item) }),
                elevation = 4.dp) {
            Column(modifier = Modifier.padding(10.dp).fillMaxWidth()) {
                Text(text = item.title ?: "")
                Text(text = item.author ?: getString(R.string.error_name_not_available),
                        color = MaterialTheme.colors.primary,
                        fontSize = 10.sp
                )
            }
        }
    }
}
