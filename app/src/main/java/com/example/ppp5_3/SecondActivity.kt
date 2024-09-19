package com.example.ppp5_3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ppp5_3.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    // Mendefinisikan konstanta TAG untuk keperluan logging
    private val TAG = "SecondActivity"

    // View Binding untuk mengakses elemen UI di activity_second.xml
    private lateinit var binding: ActivitySecondBinding

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            // Mendapatkan data dari Intent
            name = data?.getStringExtra("EXTRA_NAME")
            email = data?.getStringExtra("EXTRA_EMAIL")
            phoneNumber = data?.getStringExtra("EXTRA_PHONE_NUMBER")
            gender = data?.getStringExtra("EXTRA_GENDER")
            password = data?.getStringExtra("EXTRA_PASSWORD")
            // Atur data untuk dikirim kembali ke MainActivity
        }
    }



    private var name: String? = "null"
    private var email: String? = "null"
    private var phoneNumber: String? = "null"
    private var gender: String? = "null"
    private var password: String? = "null"



    // Fungsi onCreate dipanggil saat aktivitas dibuat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Logging untuk menandai bahwa onCreate dipanggil
        Log.d(TAG, "onCreateDipanggil")

        enableEdgeToEdge()

        // Inisialisasi View Binding untuk mengakses elemen UI di activity_second.xml
        binding = ActivitySecondBinding.inflate(layoutInflater)
        // Menetapkan layout yang di-inflate sebagai tampilan konten aktivitas
        setContentView(binding.root)



        // Menggunakan binding untuk mengakses elemen UI dan mengatur fungsinya
        with(binding) {

            // Mengatur event handler untuk tombol yang membuka ThirdActivity/button register
            btnToThirdActivity.setOnClickListener {
                val intent = Intent(this@SecondActivity, ThirdActivity::class.java)
                launcher.launch(intent)
            }

            btnLogin.setOnClickListener{



                val emailInput = inputTextEmail.text.toString()
                val passwordInput = inputTextPassword.text.toString()

                // Validasi email dan password
                if (emailInput == email && passwordInput == password) {
                    // Validasi berhasil, kirim data ke aktivitas sebelumnya
                    val intentDataHasilRegistrasi = Intent()

                    // Menambahkan input kedalam variabel intent
                    intentDataHasilRegistrasi.putExtra("EXTRA_NAME", name)
                    intentDataHasilRegistrasi.putExtra("EXTRA_EMAIL", email)
                    intentDataHasilRegistrasi.putExtra("EXTRA_PHONE_NUMBER", phoneNumber)
                    intentDataHasilRegistrasi.putExtra("EXTRA_GENDER", gender)

                    // Mengatur hasil dari Activity ini sebagai RESULT_OK dan mengirim Intent kembali
                    setResult(Activity.RESULT_OK, intentDataHasilRegistrasi)

                    // Menutup Activity ini setelah mengirimkan hasil
                    finish()
                } else {
                    // Validasi gagal, tampilkan toast dengan pesan kesalahan
                    Toast.makeText(this@SecondActivity, "Password or email invalid", Toast.LENGTH_SHORT).show()
                }
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


}
