package com.example.ppp5_3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ppp5_3.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {
    // Deklarasi variabel binding untuk menghubungkan layout dengan kode
    private lateinit var binding: ActivityThirdBinding

    // Fungsi ini dipanggil saat Activity dibuat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Mengaktifkan tampilan full-screen edge-to-edge pada aktivitas ini
        enableEdgeToEdge()

        binding = ActivityThirdBinding.inflate(layoutInflater)

        // Mengatur tampilan konten Activity menggunakan root dari binding
        // root adalah root view dari layout yang di-inflate
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Menggunakan binding untuk mengakses elemen UI dan menetapkan fungsionalitasnya
        with(binding) {
            // Menambahkan event listener pada tombol "Close"
            btnRegister.setOnClickListener {
                val intentDataHasilRegistrasi = Intent()
                intentDataHasilRegistrasi.putExtra("EXTRA_NAME", regName.text.toString())
                intentDataHasilRegistrasi.putExtra("EXTRA_EMAIL", regEmail.text.toString())
                intentDataHasilRegistrasi.putExtra("EXTRA_PHONE_NUMBER", regPhoneNumber.text.toString())
                intentDataHasilRegistrasi.putExtra("EXTRA_GENDER", regGender.text.toString())
                intentDataHasilRegistrasi.putExtra("EXTRA_PASSWORD", regPassword.text.toString())

                // Mengatur hasil dari Activity ini sebagai RESULT_OK dan mengirim Intent kembali
                setResult(Activity.RESULT_OK, intentDataHasilRegistrasi)

                // Menutup Activity ini setelah mengirimkan hasil
                finish()

            }
        }
    }
}
