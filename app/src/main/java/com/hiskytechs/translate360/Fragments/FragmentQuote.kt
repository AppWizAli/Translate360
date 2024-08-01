package com.hiskytechs.translate360.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hiskytechs.translate360.Adapters.AdapterQuotes
import com.hiskytechs.translate360.ApiModels.ModelQuotes
import com.hiskytechs.translate360.ApiModels.RetrofitBuilder
import com.hiskytechs.translate360.Interface.QuotesInterface
import com.hiskytechs.translate360.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentQuote : Fragment() {

    private lateinit var quoteRecyclerView: RecyclerView
    private lateinit var quoteAdapter: AdapterQuotes
    private var quotesList: MutableList<String> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_quote, container, false)
        quoteRecyclerView = view.findViewById(R.id.rvQuotes)
        quoteRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        quoteAdapter = AdapterQuotes(requireContext(), quotesList)
        quoteRecyclerView.adapter = quoteAdapter
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
                        quotesList.clear()
                        quotesList.addAll(quotes)
                        quoteAdapter.notifyDataSetChanged()
                    } else {
                        quotesList.clear()
                        quoteAdapter.notifyDataSetChanged()
                    }
                } else {
                    Log.e("FragmentQuote", "Failed to load quotes: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<ModelQuotes>, t: Throwable) {
                Log.e("FragmentQuote", "Error fetching quotes", t)
            }
        })
    }
}
