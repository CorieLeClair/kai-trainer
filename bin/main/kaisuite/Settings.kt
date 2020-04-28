package kaisuite

import java.io.BufferedWriter
import java.io.FileInputStream
import java.io.FileWriter
import java.util.*

var projectPath = ""

class Settings {

    fun saveSetting(key : String, value : String, file : String){
        if(key == "projectpath"){
            projectPath= "$value\\settings\\guisettings.properties"
            println(projectPath)
        }
        val bufferedWriter = BufferedWriter(FileWriter(file, true))
        bufferedWriter.append("$key=$value")
        bufferedWriter.close()
    }

    fun saveSetting(key : String, value : String){
        val bufferedWriter = BufferedWriter(FileWriter(getSetting("projectpath"), true))
        bufferedWriter.append("$key=$value")
        bufferedWriter.close()
    }

    fun getSetting(key : String): String {
        println(projectPath)
        val propertiesFile = FileInputStream(projectPath)
        println(propertiesFile)
        val properties = Properties()
        properties.load(propertiesFile)
        return properties.getProperty(key)
    }

    fun getSetting(key : String, file : String): String {
        val propertiesFile = FileInputStream(file)
        val properties = Properties()
        properties.load(propertiesFile)
        return properties.getProperty(key)
    }
}