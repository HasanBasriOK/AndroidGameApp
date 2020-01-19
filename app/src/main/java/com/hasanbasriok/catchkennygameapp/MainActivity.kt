package com.hasanbasriok.catchkennygameapp

import android.app.AlertDialog
import android.content.DialogInterface
import android.media.MediaPlayer
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var mediaPlayer: MediaPlayer?=null
    var imageList= ArrayList<ImageView>()
    var score:Int=0
    var runnable= Runnable() {  }
    var handler=Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fillImageList()
        makeInvisibleImages()

        mediaPlayer=MediaPlayer.create(this,R.raw.miyac)


        object:CountDownTimer(15000,1000)
        {
            override fun onFinish() {
                var alertBuilder= AlertDialog.Builder(this@MainActivity)
                alertBuilder.setTitle("Information")
                alertBuilder.setMessage("Game Over.Do you want to play again ?")
                alertBuilder.setPositiveButton("Yes"){dialog, which ->


                    startActivity(this@MainActivity.intent)
                  }
                alertBuilder.setNegativeButton("No"){dialog, which ->

                    Toast.makeText(this@MainActivity,"Game over. Your score is :"+score,Toast.LENGTH_LONG).show()
                    handler.removeCallbacks(runnable)
                }

                alertBuilder.create().show()
            }

            override fun onTick(millisUntilFinished: Long) {
                txtTime.text="Time :"+millisUntilFinished/1000
            }
        }.start()

        runnable= Runnable {

            var imageIndex=Random.nextInt(12)

            makeInvisibleImages()
            imageList[imageIndex].visibility=View.VISIBLE

            handler.postDelayed(runnable,1000)
        }

        runnable.run()
    }



    fun fillImageList()
    {
        imageList.add(kennyImage1)
        imageList.add(kennyImage2)
        imageList.add(kennyImage3)
        imageList.add(kennyImage4)
        imageList.add(kennyImage5)
        imageList.add(kennyImage6)
        imageList.add(kennyImage7)
        imageList.add(kennyImage8)
        imageList.add(kennyImage9)
        imageList.add(kennyImage10)
        imageList.add(kennyImage11)
        imageList.add(kennyImage12)
    }

    fun increaseScore(view:View)
    {
        score+=1
        txtScore.text="Score : ${score}"
        mediaPlayer?.start()

    }

    fun makeInvisibleImages()
    {
        for (image in imageList)
        {
            image.visibility= View.INVISIBLE
        }
    }
}
