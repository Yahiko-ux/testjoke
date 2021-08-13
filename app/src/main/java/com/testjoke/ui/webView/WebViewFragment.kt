package com.testjoke.ui.webView

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import by.kirich1409.viewbindingdelegate.viewBinding
import com.testjoke.R
import com.testjoke.databinding.FragmentWebViewBinding

class WebViewFragment : Fragment(R.layout.fragment_web_view) {
    private val binding: FragmentWebViewBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            setWebView()
        } else {
            savedInstanceState.getBundle("webViewState")
                ?.let { binding.webView.restoreState(it) }
        }
        onBackPressed()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val bundle = Bundle()
        binding.webView.saveState(bundle)
        outState.putBundle("webViewState", outState)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setWebView() {
        with(binding.webView) {
            webViewClient = AppWebViewClient()
            settings.javaScriptEnabled = true
            loadUrl("http://www.icndb.com/api")
        }
    }

    private fun onBackPressed() {
        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {

                override fun handleOnBackPressed() {
                    if (binding.webView.canGoBack()) {
                        binding.webView.goBack()
                    } else {
                        isEnabled = false
                        activity?.onBackPressed()
                    }
                }
            })
    }

    private class AppWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            view?.loadUrl(request?.url.toString())
            return true
        }
    }
}