package com.example.rssfeedpractice


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rssfeedpractice.databinding.RowRecyclerviewBinding


class RecyclerViewAdapter(private var questionList: List<Question>): RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {
    class RecyclerViewHolder(val binding: RowRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root)


    fun setQuestionsList(questionsList: List<Question>) {
        this.questionList = questionsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(RowRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        var currentQuestion = questionList[position]
        holder.binding.apply {
            titleTV.text = "${currentQuestion.title}"

        }
    }
    override fun getItemCount() = questionList.size
}
