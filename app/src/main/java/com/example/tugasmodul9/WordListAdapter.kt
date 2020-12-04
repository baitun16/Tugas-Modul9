package com.example.tugasmodul9

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WordListAdapter internal constructor(context: Context) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {

    private val inflater: LayoutInflater= LayoutInflater.from(context)
    private var words = emptyList<Word>()
    var clickListener : OnClickListener? = null
    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val wordItemView: TextView = itemView.findViewById(R.id.textView)
    }//untuk membuat suatau tampilan dan mengembalikannya
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item,parent, false)
        return WordViewHolder(itemView)
    }//menghubungkan data dengan view holder pada posisi yang telah ditentukan dalam recycleview
    override fun onBindViewHolder(holder: WordViewHolder, position: Int){
        val current = words[position]
        holder.wordItemView.text = current.word
        holder.wordItemView.setOnClickListener({itemView -> clickListener?.onClick(position)})
    }//pengelolaan value words
    internal fun setWords(words: List<Word>) {
        this.words = words
        notifyDataSetChanged()
    }//kembali ke jumlah item data yang tersedia untuk ditampilkan
    override fun getItemCount() = words.size
//pengelolaan kondisi jika suatu elemen di click
    fun setOnClickListener(listener: (Int) -> Unit){
        this.clickListener = object: OnClickListener {
            override fun onClick(position: Int) {
                listener(position)
            }
        }
    }


    interface OnClickListener{
        fun onClick(position: Int)
    }
}