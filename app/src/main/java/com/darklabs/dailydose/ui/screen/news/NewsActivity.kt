package com.darklabs.dailydose.ui.screen.news

import android.content.Context
import android.os.Bundle
import android.telephony.TelephonyManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnForIndexed
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
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
import com.darklabs.dailydose.ui.component.SingleSelectDialog
import com.darklabs.dailydose.ui.component.Toolbar
import com.darklabs.dailydose.util.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsActivity : BaseActivity<NewsViewModel>() {

    private val newsViewModel: NewsViewModel by viewModels()

    private val dialogState by lazy { mutableStateOf(false) }

    private var page = 0

    private lateinit var selectedOption: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectedOption = (this.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager)
                .networkCountryIso

        newsViewModel.fetchNewsHeadLines(selectedOption, page)
        setContent {
            DailyDoseTheme {
                Scaffold(topBar = {
                    Toolbar(getString(R.string.app_name)) {
                        dialogState.value = true
                    }
                }) {
                    NewsScreen()
                    CountriesDialog()
                }
            }
        }
    }

    @Composable
    fun CountriesDialog() {
        if (dialogState.value) {
            SingleSelectDialog(title = getString(R.string.title_select_country),
                    optionsList = getCountries().values.toList(),
                    defaultSelected = getCountries().keys.toList().indexOf(selectedOption),
                    submitButtonText = getString(R.string.text_apply),
                    onSubmitButtonClick = {
                        selectedOption = getCountries().keys.toList()[it]
                        page = 0
                        newsViewModel.fetchNewsHeadLines(selectedOption, page)
                    },
                    onDismissRequest = { dialogState.value = false })
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
                    page++
                    newsViewModel.fetchNewsHeadLines(selectedOption, page)
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
                Text(text = item.title ?: "", style = MaterialTheme.typography.body1)
                Spacer(modifier = Modifier.preferredHeight(10.dp))
                Row {
                    Box(Modifier.weight(2f)) {
                        Text(
                                text = item.author.takeIf { it.isNullOrEmpty().not() }
                                        ?: getString(R
                                                .string
                                                .error_name_not_available),
                                color = MaterialTheme.colors.primary,
                                fontSize = 10.sp,
                        )
                    }
                    Text(text = item.publishedAt.toDate().formatTo(DATE_FORMAT_END_USER),
                            color = MaterialTheme.colors.primary,
                            fontSize = 10.sp)

                }
            }
        }
    }
}
