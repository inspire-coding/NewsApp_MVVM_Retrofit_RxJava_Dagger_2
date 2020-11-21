package com.inspirecoding.newsapp_mvvm_retrofit_rxjava_dagger_1

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.material.snackbar.Snackbar
import com.inspirecoding.newsapp_mvvm_retrofit_rxjava_dagger_1.model.Article
import com.inspirecoding.newsapp_mvvm_retrofit_rxjava_dagger_1.utils.Status
import com.inspirecoding.newsapp_mvvm_retrofit_rxjava_dagger_1.viewmodel.MainActivityViewModel
import com.inspirecoding.newsapp_mvvm_retrofit_rxjava_dagger_1.viewmodel.MainActivityViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    val TAG = this.javaClass.simpleName


    @Inject
    lateinit var mainActivityViewModelFactory: MainActivityViewModelFactory
    private lateinit var mainActivityViewModel: MainActivityViewModel

    private lateinit var adapter : ArticleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityViewModel = ViewModelProvider(this, mainActivityViewModelFactory).get(MainActivityViewModel::class.java)

        mainActivityViewModel.articlesResponse.observe(this, Observer { _result ->
            when (_result?.status) {
                Status.SUCCESS -> {
                    _result.data?.let {

                        if(::adapter.isInitialized) {
                            Log.d(TAG, "$it")
                            val newList = it
                            adapter.updateAllItems(newList)
                        } else {

                            val newList = it
                            Log.d(TAG, it.toString())
                            Log.d(TAG, newList.toString())
                            adapter = ArticleAdapter(it)
                            initRecyclerView(it)

                        }

                        Log.d(TAG, "SUCCESS")
                    }

                    swipeRefreshLayout.isRefreshing = false
                }
                Status.LOADING -> {
                    swipeRefreshLayout.isRefreshing = true
                    Log.d(TAG, "LOADING")
                }
                Status.ERROR -> {
                    createSnackbar()
                    swipeRefreshLayout.isRefreshing = false
                    Log.d(TAG, "${_result.message}")
                }
            }
        })

        swipeRefreshLayout.setOnRefreshListener {
            mainActivityViewModel.subscribeObservableOfArticle()
        }

    }

    private fun initRecyclerView(articlesList : MutableList<Article>) {

        recyclerView.apply {
            adapter = this@MainActivity.adapter
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
        }

    }

    private fun createSnackbar() {
        val snackbar = Snackbar.make(
            constraintLayout,
            R.string.something_went_wrong,
            Snackbar.LENGTH_INDEFINITE
        )

        snackbar.setAction(
            R.string.retry,
            refreshData()
        )

        snackbar.show()
    }

    inner class refreshData : View.OnClickListener {
        override fun onClick(p0: View?) {
            mainActivityViewModel.subscribeObservableOfArticle()
        }
    }

}