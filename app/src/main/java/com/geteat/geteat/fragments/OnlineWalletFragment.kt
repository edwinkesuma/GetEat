@file:Suppress("UNREACHABLE_CODE")

package com.geteat.geteat.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.geteat.geteat.R
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.fragment_online_wallet.*


class OnlineWalletFragment : BaseFragment() {

    fun onCreate(inflater: LayoutInflater, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        return inflater.inflate(R.layout.fragment_online_wallet, container, false)

        btn_spay.setOnClickListener {
            val pm = requireContext().packageManager
            val launchIntent = pm.getLaunchIntentForPackage("com.example.package")
            requireContext().startActivity(launchIntent)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        //Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_online_wallet, container, false)

    }
}