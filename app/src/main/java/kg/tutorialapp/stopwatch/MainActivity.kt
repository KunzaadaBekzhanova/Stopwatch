package kg.tutorialapp.stopwatch

import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.Chronometer
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var textViewInfo: TextView? = null
    private var chronometer: Chronometer? = null
    private var buttonStart: Button? = null
    private var buttonStop: Button? = null
    private var buttonResetBaseTime: Button? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textViewInfo = findViewById(R.id.textView_info) as TextView?
        chronometer = findViewById(R.id.chronometerExample) as Chronometer?
        buttonStart = findViewById(R.id.button_start) as Button?
        buttonStop = findViewById(R.id.button_stop) as Button?
        buttonResetBaseTime = findViewById(R.id.button_resetBaseTime) as Button?
        buttonStop!!?.setEnabled(false)
        buttonResetBaseTime?.setEnabled(false)
        buttonStart?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                doStart()
            }
        })
        buttonStop!!.setOnClickListener(object : View.OnClickListener{
            override fun onClick(view: View?) {
                doStop()
            }
        })
        buttonResetBaseTime?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                doResetBaseTime()
            }
        })
    }

    // @totalMilliseconds: milliseconds since system boot, including time spent in sleep.
    private fun showInfo(totalMilliseconds: Long) {
        // Seconds
        val totalSecs = totalMilliseconds / 1000
        // Show Info
        val hours = totalSecs / 3600
        val minutes = totalSecs % 3600 / 60
        val seconds = totalSecs % 60
        textViewInfo?.setText("Base Time: $totalSecs ~ $hours hours $minutes minutes $seconds seconds")
    }

    private fun doStart() {
        // Returns milliseconds since system boot, including time spent in sleep.
        val elapsedRealtime: Long = SystemClock.elapsedRealtime()
        // Set the time that the count-up timer is in reference to.
        chronometer?.setBase(elapsedRealtime)
        chronometer?.start()
        showInfo(elapsedRealtime)
        //
        buttonStart?.setEnabled(false)
        buttonStop?.setEnabled(true)
        buttonResetBaseTime?.setEnabled(true)
    }

    private fun doStop() {
        chronometer?.stop()
        //
        buttonStart?.setEnabled(true)
        buttonStop?.setEnabled(false)
        buttonResetBaseTime?.setEnabled(false)
    }

    private fun doResetBaseTime() {
        // Returns milliseconds since system boot, including time spent in sleep.
        val elapsedRealtime: Long = SystemClock.elapsedRealtime()
        // Set the time that the count-up timer is in reference to.
        chronometer?.setBase(elapsedRealtime)
        showInfo(elapsedRealtime)
    }
}