package services

import org.w3c.dom.NodeList
import java.io.InputStream
import javax.xml.parsers.DocumentBuilderFactory

class ScrivenerParser(istream : InputStream) {
    var empDataHashMap = HashMap< String, String>()
    var empList: ArrayList< HashMap< String, String>> = ArrayList()
    val builderFactory = DocumentBuilderFactory.newInstance()
    val docBuilder = builderFactory.newDocumentBuilder()
    val doc = docBuilder.parse(istream)

    fun get(tagName : String) : NodeList {
        return doc.getElementsByTagName(tagName)
    }

}