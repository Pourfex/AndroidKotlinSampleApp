package fr.android.androidexercises.ShopActivity

import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuItemCompat
import fr.android.androidexercises.Global.Book
import fr.android.androidexercises.MainBookActivity.LibraryPresenter
import fr.android.androidexercises.R
import fr.android.androidexercises.ShopActivity.View.ShopBookAdapter

class ShopActivity :AppCompatActivity(), LibraryPresenter.BookReceiver {

    var textCartItemCount: TextView? = null
    private var shoppingNumbers : Int = 0

    private var PRIVATE_MODE = 0
    private val PREF_NAME = "library_cart"
    lateinit var sharedPref: SharedPreferences

    private var books: List<Book>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        LibraryPresenter().init(this, this)

        sharedPref = getSharedPreferences(PREF_NAME, PRIVATE_MODE)
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

    override fun OnBookReceived(books: List<Book>){
        this.books = books

        val listView = findViewById<ListView>(R.id.book_list_view)
        listView.adapter = ShopBookAdapter(this, books, sharedPref)

        val priceView : TextView = findViewById(R.id.price_shop)


        //get the number of books bought by user in userPrefs
        var buyCounter =0
        var priceCounter = 0
        for (book in books){
            buyCounter += sharedPref.getInt(book.isbn, 0)
            priceCounter += book.price.toInt() * sharedPref.getInt(book.isbn, 0)
        }
        shoppingNumbers = buyCounter
        textCartItemCount!!.text = "" + shoppingNumbers
        priceView.text = priceCounter.toString()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.action_home -> {
                Toast.makeText(this, "Go at home", Toast.LENGTH_SHORT).show()
                onBackPressed()
            }
            R.id.action_shop -> {
                Toast.makeText(this, "AlreadyAtShop", Toast.LENGTH_LONG).show()
            }
        }
        return true
    }
}