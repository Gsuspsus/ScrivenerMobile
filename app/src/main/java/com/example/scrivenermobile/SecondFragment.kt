package com.example.scrivenermobile

import android.os.Bundle
import android.os.Debug
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.scrivenermobile.databinding.FragmentSecondBinding
import services.Decompresser
import services.ProjectAdapter
import services.ScrivenerFolder
import java.io.*
import java.util.zip.ZipInputStream
import android.content.Intent
import android.net.Uri


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private lateinit var recyclerView : RecyclerView
    private lateinit var data : Array<ScrivenerFolder>
        // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = SecondFragmentArgs.fromBundle(requireArguments())
        Decompresser(ZipInputStream(ByteArrayInputStream(args.zipped.toByteArray()))).unzip()
        val path = requireContext().filesDir.absolutePath + "/scriv/_Scriv/EksamensOpgave2022.scriv/Files/Data"
        val f = File(path)
        data = f.listFiles().map{ ScrivenerFolder(it.path, it.listFiles())}.toTypedArray()

        recyclerView = view.findViewById(R.id.recyclerView)
        val adapter = ProjectAdapter(data)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}