package com.geteat.geteat.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import com.geteat.geteat.R
import com.geteat.geteat.RetrofitInstance
import com.geteat.geteat.models.NotificationData
import com.geteat.geteat.models.PushNotification
import com.geteat.geteat.models.SoldProduct
import com.geteat.geteat.utils.Constants
import com.geteat.geteat.utils.GlideLoader
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_sold_product_details.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

const val TOPIC = "/topics/myTopic"
class SoldProductDetailsActivity : BaseActivity() {

    val TAG = "SoldProductDetailsActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sold_product_details)

        var productDetails: SoldProduct = SoldProduct()

        if (intent.hasExtra(Constants.EXTRA_SOLD_PRODUCT_DETAILS)) {
            productDetails =
                    intent.getParcelableExtra<SoldProduct>(Constants.EXTRA_SOLD_PRODUCT_DETAILS)!!
        }

//        FirebaseInstanceId.getInstance().addOnSuccessListener{
//
//        }
        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)
        btn_notif.setOnClickListener{
            val tittle = "No Antrian Anda Adalah"
            val message = et_no_antri.text.toString()
            if (message.isNotEmpty()){
                PushNotification(
                    NotificationData(tittle, message),
                    TOPIC
                ).also {
                    sendNotification(it)
                }
            }
        }
        setupActionBar()
        setupUI(productDetails)

    }

    private fun setupActionBar() {

        setSupportActionBar(toolbar_sold_product_details_activity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24)
        }

        toolbar_sold_product_details_activity.setNavigationOnClickListener { onBackPressed() }
    }
    private fun setupUI(productDetails: SoldProduct) {

        tv_sold_product_details_id.text = productDetails.order_id

        val dateFormat = "dd MMM yyyy HH:mm"
        val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())

        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = productDetails.order_date
        tv_sold_product_details_date.text = formatter.format(calendar.time)

        GlideLoader(this@SoldProductDetailsActivity).loadProductPicture(
                productDetails.image,
                iv_product_item_image
        )
        tv_product_item_name.text = productDetails.title
        tv_product_item_price.text ="Rp${productDetails.price}"
        tv_sold_product_quantity.text = productDetails.sold_quantity

        tv_sold_details_address_type.text = productDetails.address.type
        tv_sold_details_full_name.text = productDetails.address.name
        tv_sold_details_address.text =
                "${productDetails.address.address}, ${productDetails.address.zipCode}"
        tv_sold_details_additional_note.text = productDetails.address.additionalNote

        if (productDetails.address.otherDetails.isNotEmpty()) {
            tv_sold_details_other_details.visibility = View.VISIBLE
            tv_sold_details_other_details.text = productDetails.address.otherDetails
        } else {
            tv_sold_details_other_details.visibility = View.GONE
        }
        tv_sold_details_mobile_number.text = productDetails.address.mobileNumber

        tv_sold_product_total_amount.text = productDetails.total_amount
    }

    @SuppressLint("LongLogTag")
    private fun sendNotification(notification: PushNotification) = CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = RetrofitInstance.api.postNotificationAPI(notification)
            if (response.isSuccessful){
                Log.d(TAG, "Response: ${Gson().toJson(response)}")
            }else{
                Log.e(TAG, response.errorBody().toString())
            }
        }catch (e: Exception){
            Log.e(TAG, e.toString())
        }
    }
}