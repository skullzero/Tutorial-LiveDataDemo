package com.onion.learn.tutorial.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

/*
    LiveData is an observable data holder class. Unlike a regular observable,
    LiveData is lifecycle-aware, meaning it respects the lifecycle of other app components,
    such as activities, fragments, or services. This awareness ensures LiveData only
    updates app component observers that are in an active lifecycle state.

    https://developer.android.com/topic/libraries/architecture/livedata
 */
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

        //LiveData is aware of lifecycle of its observer
        viewModel.seconds.observe(this, Observer {
            numberText.text = it.toString()
        })

        viewModel.finished.observe(this, Observer {
            if(it) {
                Toast.makeText(this, "Finished", Toast.LENGTH_SHORT ).show()
            }
        })
    }
}