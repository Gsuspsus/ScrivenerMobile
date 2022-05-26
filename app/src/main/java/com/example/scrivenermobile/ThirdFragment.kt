package com.example.scrivenermobile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.scrivenermobile.databinding.FragmentSecondBinding
import com.example.scrivenermobile.databinding.FragmentThirdBinding
import java.io.File
import android.R
import android.graphics.Color
import androidx.navigation.fragment.findNavController
import jp.wasabeef.richeditor.RichEditor


class ThirdFragment : Fragment() {

    private var _binding: FragmentThirdBinding? = null
    private val binding get() = _binding!!
    private lateinit var path : String
    private lateinit var contentsFile : File

    // TODO: Rename and change types of parameters
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = ThirdFragmentArgs.fromBundle(requireArguments())
        path = args.filePath
        contentsFile = File(path)
        binding.contents.setText(contentsFile.readText())
    }
}