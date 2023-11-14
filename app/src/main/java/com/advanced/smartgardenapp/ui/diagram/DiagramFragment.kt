package com.advanced.smartgardenapp.ui.diagram

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.advanced.smartgardenapp.databinding.FragmentDiagramBinding
import com.advanced.smartgardenapp.utils.Constants

class DiagramFragment : Fragment() {
    private lateinit var binding: FragmentDiagramBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDiagramBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            diagramFragment = this@DiagramFragment


            webView.settings.javaScriptEnabled = true
            webView.loadUrl(Constants.KEY_DIAGRAM_URL)
            webView.settings.setSupportZoom(true)
            webView.settings.builtInZoomControls = true
        }
    }

}