package com.hiskytechs.translate360.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.hiskytechs.translate360.ApiModels.ModelQuotes
import com.hiskytechs.translate360.databinding.QuoteitemBinding

class AdapterQuotes(val context: Context, var quotesList: List<String>) : RecyclerView.Adapter<AdapterQuotes.MyViewHolder>() {

    inner class MyViewHolder(val binding: QuoteitemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = QuoteitemBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val quote = quotesList[position]
        holder.binding.tvQuote.text = quote
        holder.binding.tvAuthor.text = "Albert Einstein" // Replace with actual author if available

        // Set click listener for the copy button
        holder.binding.imgCopy.setOnClickListener {
            // Get the ClipboardManager
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
            // Create a new ClipData with the quote text
            val clip = android.content.ClipData.newPlainText("Quote", quote)
            // Set the clip data to the clipboard
            clipboard.setPrimaryClip(clip)
            // Optionally, show a toast to notify the user
            Toast.makeText(context, "Quote copied to clipboard", Toast.LENGTH_SHORT).show()
        }
        holder.binding.imgFavorite.setOnClickListener()
        {
            Toast.makeText(context, "Added to favorite", Toast.LENGTH_SHORT).show()

        }
    }


    override fun getItemCount(): Int {
        return quotesList.size
    }
}
