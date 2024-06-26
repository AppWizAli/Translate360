package com.hiskytechs.translate360.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.hiskytechs.translate360.ApiModels.ModelLanguage
import com.hiskytechs.translate360.Interface.LangauageInterafce
import com.hiskytechs.translate360.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TextTranslateFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val retrofitBuilder= Retrofit.Builder()
            .baseUrl("https://google-translate113.p.rapidapi.com/api/v1/translator/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LangauageInterafce::class.java)



        val retrofitData=retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<ModelLanguage> {
            override fun onResponse(call: Call<ModelLanguage>, response: Response<ModelLanguage>) {

                var result=response.body()?.get(0)!!

                Toast.makeText(requireActivity(), "Succcess", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<ModelLanguage>, t: Throwable) {
                Toast.makeText(requireActivity(), "Faiilure", Toast.LENGTH_SHORT).show()
            }
        })

        return inflater.inflate(R.layout.fragment_text_translate, container, false)
    }

}