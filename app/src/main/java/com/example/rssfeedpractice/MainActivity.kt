package com.example.rssfeedpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rssfeedpractice.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var QuestionList: List<Question>

    lateinit var myAdapter : RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        QuestionList = listOf()
        // Adapter setting
        myAdapter = RecyclerViewAdapter(QuestionList)
        binding.recyclerV.adapter = myAdapter
        binding.recyclerV.layoutManager = LinearLayoutManager(this)



        RSSparse()
    }

    private fun RSSparse(){
        CoroutineScope(IO).launch {
            val data = async {
                val parser = XmlParserHandler()
                parser.parse()
            }.await()
            try{
                withContext(Main){
                    myAdapter.setQuestionsList(data)
                    Log.d("Main", data[0].title)
                }
            }catch(e: java.lang.Exception){
                Log.d("MAIN", "Unable to get data")
            }
        }
    }
}