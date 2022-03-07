package com.geteat.geteat.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.geteat.geteat.R
import com.geteat.geteat.adapter.CartItemsListAdapter
import com.geteat.geteat.models.Order
import com.geteat.geteat.utils.Constants
import kotlinx.android.synthetic.main.activity_my_order_details.*
import java.text.SimpleDateFormat
import java.util.*

class MyOrderDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_order_details)

        setupActionBar()

        var myOrderDetails: Order = Order()

        if (intent.hasExtra(Constants.EXTRA_MY_ORDER_DETAILS)) {
            myOrderDetails =
                    intent.getParcelableExtra<Order>(Constants.EXTRA_MY_ORDER_DETAILS)!!
        }

        setupUI(myOrderDetails)
    }

    private fun setupActionBar() {

        setSupportActionBar(toolbar_my_order_details_activity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24)
        }

        toolbar_my_order_details_activity.setNavigationOnClickListener { onBackPressed() }
    }

    private fun setupUI(orderDetails: Order) {

        tv_order_details_id.text = orderDetails.title

        val dateFormat = "dd MMM yyyy HH:mm"
        val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())

        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = orderDetails.order_datetime

        val orderDateTime = formatter.format(calendar.time)
        tv_order_details_date.text = orderDateTime


        rv_my_order_items_list.layoutManager = LinearLayoutManager(this@MyOrderDetailsActivity)
        rv_my_order_items_list.setHasFixedSize(true)

        val cartListAdapter =
                CartItemsListAdapter(this@MyOrderDetailsActivity, orderDetails.items, false)
        rv_my_order_items_list.adapter = cartListAdapter

        tv_my_order_details_address_type.text = orderDetails.address.type
        tv_my_order_details_full_name.text = orderDetails.address.name
        tv_my_order_details_address.text =
                "${orderDetails.address.address}, ${orderDetails.address.zipCode}"
        tv_my_order_details_additional_note.text = orderDetails.address.additionalNote

        if (orderDetails.address.otherDetails.isNotEmpty()) {
            tv_my_order_details_other_details.visibility = View.VISIBLE
            tv_my_order_details_other_details.text = orderDetails.address.otherDetails
        } else {
            tv_my_order_details_other_details.visibility = View.GONE
        }
        tv_my_order_details_mobile_number.text = orderDetails.address.mobileNumber

        tv_order_details_total_amount.text = orderDetails.total_amount
    }
}