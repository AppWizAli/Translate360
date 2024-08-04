package com.hiskytechs.translate360.Fragments

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.Translator
import com.google.mlkit.nl.translate.TranslatorOptions
import com.hiskytechs.translate360.ApiModels.ModelLanguage
import com.hiskytechs.translate360.Interface.LangauageInterafce
import com.hiskytechs.translate360.R
import com.hiskytechs.translate360.databinding.FragmentTextTranslateBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class TextTranslateFragment : Fragment(), TextToSpeech.OnInitListener {

    private lateinit var binding: FragmentTextTranslateBinding
    private lateinit var languageMap: Map<String, String>
    private var tts: TextToSpeech? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_text_translate, container, false)

        setupLanguageSpinners()

        // Initialize TextToSpeech
        tts = TextToSpeech(requireContext(), this)

        // Set up Speak button
        binding.ivsoundFrom.setOnClickListener {
            speakOut()
        }

        binding.tvEnglish.setOnClickListener {
            val sourceLanguage = languageMap[binding.spinnerFrom.selectedItem.toString()]
            val targetLanguage = languageMap[binding.spinnerTo.selectedItem.toString()]

            if (sourceLanguage != null && targetLanguage != null) {
                val options = TranslatorOptions.Builder()
                    .setSourceLanguage(sourceLanguage)
                    .setTargetLanguage(targetLanguage)
                    .build()
                val translator = Translation.getClient(options)

                val conditions = DownloadConditions.Builder()
                    .requireWifi()
                    .build()
                translator.downloadModelIfNeeded(conditions)
                    .addOnSuccessListener {
                        translateText(translator)
                    }
                    .addOnFailureListener { exception ->
                        Toast.makeText(requireContext(), "Failed to download model: ${exception.message}", Toast.LENGTH_LONG).show()
                    }
            } else {
                Toast.makeText(requireContext(), "Please select valid languages", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts!!.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "The Language not supported!")
            } else {
                binding.ivsoundFrom.isEnabled = true
            }
        }
    }

    private fun speakOut() {
        val text = binding.fromTextTranslate.text.toString()
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    override fun onDestroy() {
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }

    private fun setupLanguageSpinners() {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://google-translate113.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LangauageInterafce::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<ModelLanguage> {
            override fun onResponse(call: Call<ModelLanguage>, response: Response<ModelLanguage>) {
                if (response.isSuccessful && response.body() != null) {
                    val languages = response.body()!!
                    languageMap = languages.associate {
                        val languageCode = TranslateLanguage.fromLanguageTag(it.code) ?: it.code
                        it.language to languageCode
                    }

                    val adapter = ArrayAdapter(
                        requireContext(),
                        R.layout.spinner_item,
                        languageMap.keys.toList()
                    )
                    adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
                    binding.spinnerFrom.adapter = adapter
                    binding.spinnerTo.adapter = adapter
                    Toast.makeText(requireActivity(), "Languages loaded successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireActivity(), "Failed to load languages", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ModelLanguage>, t: Throwable) {
                Toast.makeText(requireActivity(), "Failure: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun translateText(translator: Translator) {
        val textToTranslate = binding.fromTextTranslate.text.toString()
        if (textToTranslate.isNotEmpty()) {
            translator.translate(textToTranslate)
                .addOnSuccessListener { translatedText ->
                    binding.tvTranslatedText.text = translatedText
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(requireContext(), "Translation failed: ${exception.message}", Toast.LENGTH_LONG).show()
                }
        } else {
            Toast.makeText(requireContext(), "Please enter text to translate", Toast.LENGTH_SHORT).show()
        }
    }
}
