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

        Log.d("demo", "---------------onCreate() is running")

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
            Log.d("demo", "=============seconds was changed to $it")
            numberText.text = it.toString()
        })

        viewModel.finished.observe(this, Observer {
            Log.d("demo", "=============finished was changed to $it")
            if(it) {
                Toast.makeText(this, "Finished", Toast.LENGTH_SHORT ).show()
            }
        })
    }

    override fun onStart() {
        super.onStart()
        Log.d("demo", "---------------onStart() is running")
    }

    override fun onResume() {
        super.onResume()
        Log.d("demo", "---------------onResume() is running")
    }

    override fun onPause() {
        super.onPause()
        Log.d("demo", "---------------onPause() is running")

        for(num in 1..1000) {
            Log.i("demo", "onPause()~~~~~~~~~~~~~~~~~~~~~$num")
        }
    }

    override fun onStop() {
        super.onStop()
        Log.d("demo", "---------------onStop() is running")

        for(num in 1..1000) {
            Log.i("demo", "onStop()~~~~~~~~~~~~~~~~~~~~~$num")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("demo", "---------------onDestroy() is running")
    }
}