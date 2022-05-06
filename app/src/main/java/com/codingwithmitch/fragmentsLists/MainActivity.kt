package com.codingwithmitch.fragmentsLists

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        var layoutManager = LinearLayoutManager(this)
//        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
//        recyclerView.layoutManager = layoutManager
//        var adapter = RecyclerAdapter { position, parking -> onListItemClick(position, parking) }
//        recyclerView.adapter = adapter

    }
    private fun onListItemClick(position: Int,parking: Parking) {
//        val intent=Intent(this, CardDetails::class.java)
//        intent.apply { putExtra("id",position)
//            putExtra("park",parking)
//
//        }
////        startActivity(intent)
//        setContentView(R.layout.fragment_meme)=
        println("other message")

    }

}

