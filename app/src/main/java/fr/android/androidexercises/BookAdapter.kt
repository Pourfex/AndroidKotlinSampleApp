package fr.android.androidexercises

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

class BookAdapter(context: Context, private val books: List<Book>) : BaseAdapter() {

    // TODO create variable with LayoutInflater.from()

    override fun getCount(): Int {
        return 0 // TODO
    }

    override fun getItem(position: Int): Book? {
        return null // TODO
    }

    override fun getItemId(position: Int): Long {
        return 0 // TODO
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        return null // TODO
    }

}
