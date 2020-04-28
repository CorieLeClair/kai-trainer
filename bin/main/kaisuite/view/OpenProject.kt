package kaisuite.view

import javafx.scene.control.Alert
import kaisuite.Settings
import tornadofx.*
import java.io.File
import kai.configuration.KaiConfig

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

class OpenProject : View("Open Project") {
    override val root =  hbox(){
        // window operations
        setMinSize(400.0, 400.0 )
        !isResizable


        button {
            text = "New Project"
            setOnAction {
                val fileChooser = chooseDirectory()
                if (fileChooser != null) {
                    if (fileChooser.list().count() < 1) {
                        File("${fileChooser.absolutePath}/mangen").mkdirs()
                        File("${fileChooser.absolutePath}/autogen").mkdirs()
                        File("${fileChooser.absolutePath}/settings").mkdirs()
                        File("${fileChooser.absolutePath}/settings/guisettings.properties").createNewFile()
                        Settings().saveSetting("projectpath", fileChooser.absolutePath, "${fileChooser.absolutePath}/settings/guisettings.properties")
                        this@OpenProject.replaceWith(Home::class,
                            transition = ViewTransition.Slide(0.5.seconds, ViewTransition.Direction.LEFT))
                        setupKai()
                    } else{
                        alert(Alert.AlertType.ERROR, "Project Error", "This folder/project is already occupied.")
                    }
                }
            }
        }

        button{
            text = "Open Project"
            setOnAction {
                val fileChooser = chooseDirectory()
                if (fileChooser != null) {
                    if(File("${fileChooser.absolutePath}/mangen").exists() && File("${fileChooser.absolutePath}/autogen").exists()){
                        Settings().saveSetting("projectpath", fileChooser.absolutePath, "${fileChooser.absolutePath}/settings/guisettings.properties")
                        this@OpenProject.replaceWith(Home::class,
                                transition = ViewTransition.Slide(0.5.seconds, ViewTransition.Direction.LEFT))
                    } else{
                        alert(Alert.AlertType.ERROR, "Project Error", "This folder/project does not have the correct project folders.")
                    }
                }
                setupKai()
            }
        }
    }

    private fun setupKai(){
        KaiConfig.Kai().definePythonInterpreter(File("C:\\Users\\corie\\OneDrive\\Documents\\kai\\KaiCompanion\\pai"))
    }
}