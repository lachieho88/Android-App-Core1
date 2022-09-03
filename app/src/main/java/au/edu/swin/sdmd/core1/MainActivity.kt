package au.edu.swin.sdmd.core1

import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var opPointsResult: Int =0
    var operator = ""

    override fun onStart() {
        super.onStart()
        Log.i("LIFECYCLE", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("LIFECYCLE", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("LIFECYCLE", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("LIFECYCLE", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("LIFECYCLE", "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("LIFECYCLE", "onRestart")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val points = findViewById<TextView>(R.id.points)
        val score = findViewById<Button>(R.id.score)
        val steal = findViewById<Button>(R.id.steal)
        val reset = findViewById<Button>(R.id.reset)

        //Set the initial scores to 0
        points.text = 0.toString()

        //The scores must be saved so that on rotation they do not reset. This can be done using either saveInstanceState or a ViewModel.
        savedInstanceState?.let {

            opPointsResult = savedInstanceState.getInt("points")
            points.text = opPointsResult.toString()
        }


        //When the "Score" button is clicked, the score increases by 1 point.
        score.setOnClickListener{

            //The scores should be kept between 0 ‐ 15
            if (opPointsResult < 15){
                opPointsResult++
                setColour(points)

                points.text = opPointsResult.toString()
             }

            if (opPointsResult == 15)
            {
                var mediaPlayer = MediaPlayer.create(this, R.raw.phone_alerts_and_rings)
                mediaPlayer.start()
            }
        }
        // When the "Steal" button is clicked, the score decreases by 1.
        steal.setOnClickListener {
            //The scores should be kept between 0 ‐ 15
            if (opPointsResult in 1..15)
            {
                opPointsResult--
                setColour(points)

                points.text = opPointsResult.toString()
            }
        }
        // The "Reset" button sets the score back to 0
        reset.setOnClickListener {
            opPointsResult = 0
            points.text = opPointsResult.toString()
            points.setTextColor(Color.parseColor("#666666"))

        }

    }
    fun setColour(points: TextView ) {
        if (opPointsResult in 0..4)

        {
            points.setTextColor(Color.parseColor("#666666"))
        }

        //When the score is between 5-9, change the text colour to blue
        if (opPointsResult in 5..9)
        {
            points.setTextColor(Color.parseColor("#4285F4"))
        }

        //When the score is between 10-15, change the text colour to green
        if (opPointsResult in 10..15 )
        {
          //  points.setTextColor(Color.parseColor("#A4C639"))
            points.setTextColor(Color.GREEN)
        }


    }


    //The scores must be saved so that on rotation they do not reset. This can be done using either saveInstanceState or a ViewModel.
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("points",opPointsResult)
        Log.i("LIFECYCLE", "saveInstanceState $opPointsResult")
    }
}