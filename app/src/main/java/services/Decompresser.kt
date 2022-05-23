package services

import android.os.Environment
import android.util.Log
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.lang.Exception
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream


class Decompresser(private val zin: ZipInputStream, private val _location: String) {
    fun unzip() {
        try {
            Log.e("SSSSSSSSSSSSSS", _location)
            var ze: ZipEntry? = null
            while (zin.nextEntry.also { ze = it } != null) {
                Log.v("Decompress", "Unzipping " + ze!!.name)
                if (ze!!.isDirectory) {
                    _dirChecker(ze!!.name)
                } else {
                    val fout = FileOutputStream(_location + ze!!.name)
                    var c = zin.read()
                    while (c != -1) {
                        fout.write(c)
                        c = zin.read()
                    }
                    zin.closeEntry()
                    fout.close()
                }
            }
            zin.close()
        } catch (e: Exception) {
            Log.e("Decompress", "unzip", e)
        }
    }

    private fun _dirChecker(dir: String) {
        val f = File(_location + dir + "_Scriv")
        if (!f.isDirectory) {
            f.mkdirs()
        }
    }

    init {
        _dirChecker("")
    }
}
