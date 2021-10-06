package com.onion.learn.tutorial.livedata

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {
    private lateinit var timer: CountDownTimer

    /*
        for every LiveData object, should have private MutableLiveData, and public LiveData which will
        be exposed to other app components
    */
    private val _seconds = MutableLiveData<Int>()
    val seconds: LiveData<Int>
        get() = _seconds

    private val _finished = MutableLiveData<Boolean>()
    val finished: LiveData<Boolean>
        get() = _finished

    var timerValue = MutableLiveData<Long>()

    fun startTimer() {
        timer = object: CountDownTimer(timerValue.value!!, 1000) {
            override fun onFinish() {
                /*
                    setValue() - UI thread
                    postValue() - background thread
                 */
                _finished.value = true
            }

            override fun onTick(millisUntilFinished: Long) {
                val timeLeft = millisUntilFinished / 1000
                _seconds.value = timeLeft.toInt()
            }
        }.start()
    }

    fun stopTimer(){
        timer.cancel()
    }
}