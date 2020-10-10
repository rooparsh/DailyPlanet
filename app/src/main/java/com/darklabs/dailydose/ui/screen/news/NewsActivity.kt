package com.darklabs.dailydose.ui.screen.news

import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.platform.setContent
import com.darklabs.dailydose.base.BaseActivity
import com.darklabs.dailydose.ui.DailyDoseTheme
import com.darklabs.dailydose.ui.screen.Greeting
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsActivity : BaseActivity<NewsViewModel>() {

    private val newsViewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DailyDoseTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }

    override fun observeViewModel() {
    }

}
