package com.example.ppp5_3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ppp5_3.databinding.ActivityMainBinding
// Profile page
class MainActivity : AppCompatActivity() {

    // Deklarasi tag log untuk debugging
    private val TAG = "MainActivity"

    // Deklarasi binding untuk mengakses elemen di layout activity_main.xml
    private lateinit var binding: ActivityMainBinding

    // Mendefinisikan launcher untuk menerima pengembalian data
    private val launcherSeconddActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data

            // mengambil data dari intent
            val name = data?.getStringExtra("EXTRA_NAME")
            val email = data?.getStringExtra("EXTRA_EMAIL")
            val phoneNumber = data?.getStringExtra("EXTRA_PHONE_NUMBER")
            val gender = data?.getStringExtra("EXTRA_GENDER")

            // Update UI with the received data
            binding.textViewName.text = name
            binding.textViewEmail.text = email
            binding.textViewPhoneNumber.text = phoneNumber
            binding.textViewSex.text = gender
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Mencatat log ketika onCreate() dipanggil
        Log.d(TAG, "onCreateDipanggil")

        // Menggunakan View Binding untuk menginflate layout activity_main.xml
        binding = ActivityMainBinding.inflate(layoutInflater)

        // Mengatur isi layout sebagai konten dari Activity
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        // Mengatur fungsi klik menggunakan binding pada tombol di layout
        with(binding) {
            // Ketika tombol ToSecondActivity ditekan, pindah ke SecondActivity
            btnToSecondActivity.setOnClickListener {


                // Membuat Objek Intent ToSecondActivity untuk memulai SecondActivity
                val intentToSecondActivity = Intent(this@MainActivity, SecondActivity::class.java)



                // Memulai aktivitas pindah ke SecondActivity

                launcherSeconddActivity.launch(intentToSecondActivity)
            }


        }
    }

}
