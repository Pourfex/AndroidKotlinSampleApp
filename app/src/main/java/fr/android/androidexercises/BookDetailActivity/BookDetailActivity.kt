package fr.android.androidexercises.BookDetailActivity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuItemCompat
import com.squareup.picasso.Picasso
import fr.android.androidexercises.Global.Book
import fr.android.androidexercises.MainBookActivity.View.BookItemView
import fr.android.androidexercises.R

class BookDetailActivity : AppCompatActivity(){

    private var title: TextView? = null
    private var price: TextView? = null
    private var description: TextView? = null
    private var cover: ImageView? = null
    private var book : Book? = null

    private var shoppingNumbers : Int = 0
    var textCartItemCount: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        val toolbar = findViewById<Toolbar>(R.id.toolbar2)
        setSupportActionBar(toolbar)

        book = intent.getParcelableExtra(getString(R.string.detail_intent_key))
        shoppingNumbers = intent.getIntExtra(getString(R.string.detail_intent_items), 0)


        //Lot of boilerplate than can be erased with databinding.
        title = findViewById<TextView>(R.id.title_detail)
        price = findViewById<TextView>(R.id.price_details)
        description = findViewById<TextView>(R.id.book_description_details)
        cover = findViewById<ImageView>(R.id.cover_detail)

        title?.text = book?.title
        price?.text = book?.price
        description?.text = book?.synopsis?.get(0)
        Picasso.get().load(book?.cover).into(cover)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_library, menu)

        //For shopping cart
        val menuItem = menu.findItem(R.id.action_shop)
        val actionView = MenuItemCompat.getActionView(menuItem)
        actionView.setOnClickListener { onOptionsItemSelected(menuItem) }
        textCartItemCount = actionView.findViewById(R.id.cart_badge) as TextView
        textCartItemCount!!.text = "" + shoppingNumbers

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.action_home -> {
                Toast.makeText(this, "Go at home", Toast.LENGTH_SHORT).show()
                onBackPressed()
            }
            R.id.action_shop -> {
                Toast.makeText(this, "Shop Not Available", Toast.LENGTH_LONG).show()
            }
        }
        return true
    }

}