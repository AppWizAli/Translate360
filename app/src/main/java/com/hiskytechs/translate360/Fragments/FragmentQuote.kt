package com.hiskytechs.translate360.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.hiskytechs.translate360.ApiModels.ModelQuotes
import com.hiskytechs.translate360.ApiModels.RetrofitBuilder
import com.hiskytechs.translate360.Interface.QuotesInterface
import com.hiskytechs.translate360.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentQuote : Fragment() {

    private lateinit var quoteTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_quote, container, false)
        quoteTextView = view.findViewById(R.id.tvHeloooQuotes)
        fetchQuotes("Albert-Einstein")
        return view
    }

    private fun fetchQuotes(author: String) {
        val quotesApi = RetrofitBuilder.getInstance().create(QuotesInterface::class.java)
        val call = quotesApi.getAuthorsQuotes(author)

        call.enqueue(object : Callback<ModelQuotes> {
            override fun onResponse(call: Call<ModelQuotes>, response: Response<ModelQuotes>) {
                if (response.isSuccessful) {
                    val quotes = response.body()
                    if (quotes != null) {
                        quoteTextView.text = quotes.joinToString("\n\n")
                    } else {
                        quoteTextView.text = "No quotes found."
                    }
                } else {
                    quoteTextView.text = "Failed to load quotes: ${response.errorBody()?.string()}"
                }
            }

            override fun onFailure(call: Call<ModelQuotes>, t: Throwable) {
                quoteTextView.text = "Failed to load quotes."
                Log.e("FragmentQuote", "Error fetching quotes", t)
            }
        })
    }
}
