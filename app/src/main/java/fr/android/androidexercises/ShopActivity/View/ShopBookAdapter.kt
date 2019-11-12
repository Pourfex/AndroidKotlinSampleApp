package fr.android.androidexercises.ShopActivity.View

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import fr.android.androidexercises.Global.Book
import fr.android.androidexercises.R

class ShopBookAdapter(context: Context, private val books: List<Book>, val sharedPref: SharedPreferences) : BaseAdapter() {

    val inflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return books.count()
    }

    override fun getItem(position: Int): Book {
        return books[position]
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).hashCode().toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var view = convertView

        if (view == null) {
            view = inflater.inflate(R.layout.item_view_shop_book, parent, false)
        }

        var bookView = view as ShopBookItemView
        bookView.bindView(getItem(position), getNumberItems(getItem(position).isbn) )


        return view
    }

    fun getNumberItems(isbn: String) : Int{
        return sharedPref.getInt(isbn, 0)
    }

}