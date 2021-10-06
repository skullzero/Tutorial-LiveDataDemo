package com.onion.learn.tutorial.livedata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        startBtn.setOnClickListener {
            if(numberInput.text.isEmpty() || numberInput.text.length < 4) {
                Toast.makeText(this, "Invalid number", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.timerValue.value = numberInput.text.toString().toLong()
                viewModel.startTimer()
            }
        }

        stopBtn.setOnClickListener {
            numberText.text = "0"
            viewModel.stopTimer()
        }

        secondActivityBtn.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        //LiveData is aware of lifecycle of its observer
        viewModel.seconds.observe(this, Observer {
            Log.d("seconds", "=============seconds was changed...")
            numberText.text = it.toString()
        })

        viewModel.finished.observe(this, Observer {
            Log.d("finished", "=============finished was changed...")
            if(it) {
                Toast.makeText(this, "Finished", Toast.LENGTH_SHORT ).show()
            }
        })


    }

    override fun onStart() {
        super.onStart()

        Log.d("onStart", "---------------onStart() is running")
    }

    override fun onResume() {
        super.onResume()

        Log.d("onResume", "---------------onResume() is running")
    }

    override fun onPause() {
        super.onPause()

        Log.d("onPause", "---------------onPause() is running")
    }
}