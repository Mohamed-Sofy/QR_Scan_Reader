package com.msofy.carpooling.qrscan

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    var QR_code=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        result.visibility = View.GONE
        textResult.visibility = View.GONE
        /// ads
        MobileAds.initialize(this,getString(R.string.admob_ID))
        val adRequest1 = AdRequest.Builder().build()

        ad1.loadAd(adRequest1)
        ad2.loadAd(adRequest1)
        ad3.loadAd(adRequest1)
        ad4.loadAd(adRequest1)


        scan_btn.setOnClickListener {
            val scanner = IntentIntegrator(this)
            scanner.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            scanner.initiateScan()
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            result.visibility = View.VISIBLE
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result != null) {
                if (result.contents == null) {
                    Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show()
                } else {
                    QR_code = result.contents.toString()
                    textResult.visibility = View.VISIBLE
                    textResult.text = QR_code

                }
            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }
}
