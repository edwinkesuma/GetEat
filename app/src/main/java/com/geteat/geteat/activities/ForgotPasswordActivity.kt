package com.geteat.geteat.activities

import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import com.geteat.geteat.R
import com.geteat.geteat.databinding.ActivityForgotPasswordBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forgot_password.*

class ForgotPasswordActivity : BaseActivity() {

    private lateinit var binding: ActivityForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //FullScreen
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else{
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        //Panggil ActionBar
        setupActionBar()

        //Submit Button
        btn_submit_forgot.setOnClickListener {

            // Get email
            val email: String = binding.etEmailForgotPw.text.toString().trim { it <= ' ' }

            // Mencegah email kosong
            if (email.isEmpty()) {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email), true)
            } else {

                // Progress dialog
                showProgressDialog(resources.getString(R.string.please_wait))

                // Mengirim reset password ke email
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                        .addOnCompleteListener { task ->

                            // Menyembunyikan progress dialog
                            hideProgressDialog()

                            if (task.isSuccessful) {
                                // Tampilkan pesan dan kembali ke login screen
                                Toast.makeText(
                                        this@ForgotPasswordActivity,
                                        resources.getString(R.string.email_sent_success),
                                        Toast.LENGTH_LONG
                                ).show()

                                finish()
                            } else {
                                showErrorSnackBar(task.exception!!.message.toString(), true)
                            }
                        }
            }
        }
        
    }


    //Mengatur Action Bar
    private fun setupActionBar() {

        setSupportActionBar(binding.toolbarForgotPasswordActivity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24)
        }

        binding.toolbarForgotPasswordActivity.setNavigationOnClickListener { onBackPressed() }
    }
}