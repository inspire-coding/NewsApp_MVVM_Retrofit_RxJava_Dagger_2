package com.inspirecoding.newsapp_mvvm_retrofit_rxjava_dagger_1.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.inspirecoding.newsapp_mvvm_retrofit_rxjava_dagger_1.model.Article
import com.inspirecoding.newsapp_mvvm_retrofit_rxjava_dagger_1.model.Resource
import com.inspirecoding.newsapp_mvvm_retrofit_rxjava_dagger_1.model.TopHeadlines
import com.inspirecoding.newsapp_mvvm_retrofit_rxjava_dagger_1.repository.TopHeadlinesRepository
import com.inspirecoding.newsapp_mvvm_retrofit_rxjava_dagger_1.utils.API_KEY
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import androidx.lifecycle.*
import io.reactivex.*
import io.reactivex.Observer
import io.reactivex.subscribers.DisposableSubscriber
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription


class MainActivityViewModelFactory @Inject constructor(
    private val topHeadlinesRepository: TopHeadlinesRepository
) : ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {

            return MainActivityViewModel(topHeadlinesRepository) as T

        }

        throw IllegalArgumentException("Unknown ViewModel class")

    }

}


class MainActivityViewModel @Inject constructor(
    private val topHeadlinesRepository: TopHeadlinesRepository
) : ViewModel()
{
    val TAG = this.javaClass.simpleName


    private var articleList: ArrayList<Article> = ArrayList()
    private lateinit var topHeadlinesObservable: Flowable<TopHeadlines>
    private var compositeDisposable: CompositeDisposable  = CompositeDisposable()

    private val _articlesResponse = MutableLiveData<Resource<ArrayList<Article>>>()
    val articlesResponse : LiveData<Resource<ArrayList<Article>>> = _articlesResponse

    init {
        subscribeObservableOfArticle()
    }

    fun subscribeObservableOfArticle() {
        topHeadlinesObservable = topHeadlinesRepository.getTopHeadlines("hu", API_KEY)

        articleList.clear()
        _articlesResponse.postValue(Resource.success(arrayListOf<Article>()))


        val result = topHeadlinesObservable
            .onBackpressureBuffer()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap {
                Flowable.fromIterable(it.articles)
            }
            .subscribeWith(createArticleObserver())

        compositeDisposable.add(result)
    }

    private fun createArticleObserver() : DisposableSubscriber<Article> {

        return object : DisposableSubscriber<Article>() {
            override fun onNext(article: Article) {
                _articlesResponse.postValue(Resource.loading())
                Log.d(TAG, "onNext()")
                if (!articleList.contains(article)) articleList.add(article)

                Log.d(TAG, "${articleList.size}")

            }

            override fun onComplete() {

                _articlesResponse.postValue(Resource.success(articleList))
                Log.d(TAG, "List: ${articleList.size}")
                Log.d(TAG, "LiveData: ${_articlesResponse.value?.data?.size}")
                Log.d(TAG, "onComplete()")

            }

            override fun onError(throwable: Throwable) {

                throwable.message?.let { _message ->
                    _articlesResponse.postValue(Resource.error(_message))
                }
                Log.d(TAG, "onError()")

            }
        }

    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}