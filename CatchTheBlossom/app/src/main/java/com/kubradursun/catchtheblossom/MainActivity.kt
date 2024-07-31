package com.kubradursun.catchtheblossom

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kubradursun.catchtheblossom.databinding.LayoutBinding
import com.kubradursun.catchtheblossom.ui.theme.CatchTheBlossomTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    private lateinit var binding: LayoutBinding
    var score= 0
    var imageArray = ArrayList<ImageView>()
    var runnable = Runnable{}
    var handler = Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= LayoutBinding.inflate(layoutInflater)
        val view= binding.root
        setContentView(view)

        imageArray.add(binding.imageView)
        imageArray.add(binding.imageView2)
        imageArray.add(binding.imageView3)
        imageArray.add(binding.imageView4)
        imageArray.add(binding.imageView5)
        imageArray.add(binding.imageView6)
        imageArray.add(binding.imageView7)
        imageArray.add(binding.imageView8)
        imageArray.add(binding.imageView9)
        hideImages()


            object  : CountDownTimer(15500,1000){
            override fun onTick(millisUntilFinished: Long) {
                binding.timetext.text = "Time:${millisUntilFinished/1000}"
            }

            override fun onFinish() {
                binding.timetext.text = "Time:0"
                handler.removeCallbacks(runnable)
                for(image in imageArray){
                    image.visibility = View.INVISIBLE
                }
                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over")
                alert.setMessage("Restart The Game?")
                alert.setPositiveButton("Yes", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                      val intentfromMain = intent
                        finish()
                        startActivity(intentfromMain)
                    }
                })
                alert.setNegativeButton("No") { dialog,which ->
                    Toast.makeText(this@MainActivity, " Game Over!", Toast.LENGTH_LONG).show()


                }
                alert.show()
            }

        }.start()




    }
    fun hideImages() {
        runnable = object : Runnable{
            override fun run() {
                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }
                val random = Random
                val randomIndex = random.nextInt(9)
                imageArray[randomIndex].visibility = View.VISIBLE
                handler.postDelayed(runnable,500)
            }
        }
    handler.post(runnable)
    }

    fun increaseScore(view: View) {
        score += 1
        binding.scoreText.text = "Score: $score"
    }
}

