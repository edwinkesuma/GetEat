package com.geteat.geteat.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.geteat.geteat.R
import com.geteat.geteat.adapter.SoldProductsListAdapter
import com.geteat.geteat.firestore.FirestoreClass
import com.geteat.geteat.models.SoldProduct
import kotlinx.android.synthetic.main.fragment_sold_products.*
import kotlinx.android.synthetic.main.item_list_layout.view.*

class SoldProductsFragment : BaseFragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sold_products, container, false)
    }

    // TODO Step 5: Override the onResume function and call the function to get the list of sold products.
    override fun onResume() {
        super.onResume()

        getSoldProductsList()
    }

    // TODO Step 4: Create a function to get the list of sold products.
    // START
    private fun getSoldProductsList() {
        // Show the progress dialog.
        showProgressDialog(resources.getString(R.string.please_wait))

        // Call the function of Firestore class.
        FirestoreClass().getSoldProductsList(this@SoldProductsFragment)
    }
    // END

    // TODO Step 2: Create a function to get the success result list of sold products.
    // START
    /**
     * A function to get the list of sold products.
     */
    fun successSoldProductsList(soldProductsList: ArrayList<SoldProduct>) {

        // Hide Progress dialog.
        hideProgressDialog()

        // TODO Step 7: Populate the list in the RecyclerView using the adapter class.
        // START
        if (soldProductsList.size > 0) {
            rv_sold_product_items.visibility = View.VISIBLE
            tv_no_sold_products_found.visibility = View.GONE

            rv_sold_product_items.layoutManager = LinearLayoutManager(activity)
            rv_sold_product_items.setHasFixedSize(true)

            val soldProductsListAdapter =
                    SoldProductsListAdapter(requireActivity(), soldProductsList)
            rv_sold_product_items.adapter = soldProductsListAdapter
        } else {
            rv_sold_product_items.visibility = View.GONE
            tv_no_sold_products_found.visibility = View.VISIBLE
        }
        // END
    }
    // END

}