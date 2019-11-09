package fr.android.androidexercises

import android.content.Context
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LibraryPresenter {

    private var books: List<Book>? = null

    fun init(applicationContext: Context, bookReceiver: BookReceiver) {
        val retrofit = Retrofit.Builder()
                .baseUrl("http://henri-potier.xebia.fr/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofit.create(HenriPotierService::class.java)


        //Coroutine
        CoroutineScope(Dispatchers.Main).launch {
            books = withContext(Dispatchers.IO) {
                return@withContext service.getBooks()
            }

            Log.i("books", books.toString())

            bookReceiver.OnBookReceived(books = books!!)
        }
    }

    interface BookReceiver{

        fun OnBookReceived(books : List<Book>)
        fun GetBooks(books : List<Book>)
    }
}