package com.example.scrivenermobile

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.scrivenermobile.databinding.FragmentFirstBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.*
import java.lang.StringBuilder
import java.net.ProtocolException
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val client = OkHttpClient()
    private var zip = MutableLiveData<String>()
    private lateinit var zipped_Stream : ZipInputStream

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        zip.observe(viewLifecycleOwner,{
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        })

        zip.postValue("")
    }

    fun run(url: String){
        val request = Request.Builder()
            .url(url)
            .addHeader("Connection", "close")
            .build()


        GlobalScope.launch(Dispatchers.IO){
            val data = client.newCall(request).execute().body()?.charStream()
            if(data != null){
                val reader = BufferedReader(data)
                zip.postValue(reader.readText())
                reader.close()
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}