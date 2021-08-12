package com.testjoke.ui.webView

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.testjoke.R
import com.testjoke.databinding.FragmentWebViewBinding


class WebViewFragment : Fragment(R.layout.fragment_web_view) {
   private val binding: FragmentWebViewBinding by viewBinding()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}