package fr.android.androidexercises.MainBookActivity

import android.content.Context
import android.util.Log
import fr.android.androidexercises.Global.Book
import fr.android.androidexercises.MainBookActivity.Service.HenriPotierService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//Class where heavy computing is lifted for books retrieving
class LibraryPresenter {

    private var books: List<Book>? = null

    fun init(applicationContext: Context, bookReceiver: BookReceiver) {

        //Use retrofit to make http call.
        //Need converter for response (here Gson)
        val retrofit = Retrofit.Builder()
                .baseUrl("http://henri-potier.xebia.fr/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        //service interface to call using Kotlin Coroutines
        val service = retrofit.create(HenriPotierService::class.java)


        //Coroutine on Main, then on IO, retrieve on Main
        CoroutineScope(Dispatchers.Main).launch {
            books = withContext(Dispatchers.IO) {
                return@withContext service.getBooks()
            }

            Log.i("books", books.toString())

            //call anyone who want to have book when received
            bookReceiver.OnBookReceived(books = books!!)
        }
    }

    interface BookReceiver{
        fun OnBookReceived(books : List<Book>)
    }
}