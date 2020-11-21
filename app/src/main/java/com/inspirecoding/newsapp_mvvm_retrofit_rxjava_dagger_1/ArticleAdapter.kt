package com.inspirecoding.newsapp_mvvm_retrofit_rxjava_dagger_1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inspirecoding.newsapp_mvvm_retrofit_rxjava_dagger_1.model.Article
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_article.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class ArticleAdapter (
    private var articleList: ArrayList<Article>
) : RecyclerView.Adapter<ArticleAdapter.ArticlesViewHolder>()
{
    fun updateAllItems(articleList: ArrayList<Article>) {
        this.articleList.clear()
        this.articleList.addAll(articleList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
        val itemView : View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_article, parent, false)

        return ArticlesViewHolder(itemView)
    }

    override fun getItemCount(): Int = articleList.size

    override fun onBindViewHolder(articlesViewHolder: ArticlesViewHolder, position: Int) {
        articlesViewHolder.bindArticle(articleList[position])
    }

    inner class ArticlesViewHolder (private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindArticle(article: Article) {
            if(!article.urlToImage.isNullOrEmpty()) {
                view.iv_articleImage.visibility = View.VISIBLE
                Picasso.get()
                    .load(article.urlToImage)
                    .centerCrop()
                    .fit()
                    .into(view.iv_articleImage)
            } else {
                view.iv_articleImage.visibility = View.GONE
            }

            view.tv_articleSource.text = article.source?.name

            view.tv_articlePublishedAt.text = article.publishedAt?.toLocalString()

            view.tv_articleTitle.text = article.title

            view.tv_articleAuthor.text = article.author
            view.tv_articleAuthor.setVisibility(!article.author.isNullOrEmpty())

            view.tv_articleContent.text = article.content
        }
    }
}

fun String.toLocalString() : String {

    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val date : Date = simpleDateFormat.parse(this) ?: Date()
    return DateFormat.getDateInstance().format(date)

}

fun View.setVisibility(isVisible : Boolean) {
    if(isVisible) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}