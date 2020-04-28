package kaisuite.view

import javafx.event.EventTarget
import javafx.scene.control.Alert
import javafx.scene.control.TextField
import javafx.stage.FileChooser
import kai.kaibrain.KaiTrainer
import kaisuite.Settings
import tornadofx.*
import kai.kaibrain.KaiBrainGeneral
import java.io.File
import java.nio.file.Path;
import java.nio.file.Paths;

class CSVEditor : View("CSV Editor") {
    private val editor = gridpane {

    }

    private val scrolls = scrollpane {
        add(editor)
    }

    override val root = gridpane {
        KaiTrainer.KaiChatTrainer().startTrainingServer()
        hbox {
            button {
                text = "Back"
                setOnAction {
                    KaiTrainer.KaiChatTrainer().killTrainingServer()
                    this@CSVEditor.replaceWith(Home::class,
                            transition = ViewTransition.Slide(0.5.seconds, ViewTransition.Direction.RIGHT))
                }
            }

            button {
                text="Add row"
                setMinSize(80.0, 25.0)
                setMaxSize(80.0, 25.0)
                setOnAction {
                    setMinSize(80.0, 25.0)
                    setMaxSize(80.0, 25.0)
                    editor.add(textfield {  }, 0, editor.rowCount)
                    editor.add(textfield {  }, 1, editor.rowCount - 1)
                }
            }

            val fileName = textfield { text = "Title.json"}

            button{
                //println(alert(Alert.AlertType.CONFIRMATION, "Save?", "Save chat file as ${title.text}").result)

                text = "Save"
                setOnAction {
                    var counter = 0
                    val dict = HashMap<String, String>()
                    repeat(editor.rowCount){
                        val firstColumn = editor.children[counter] as TextField
                        val secondColumn = editor.children[counter + 1] as TextField

                        if(firstColumn.text != ""){
                            dict[firstColumn.text] = secondColumn.text
                        }

                        counter += 2
                    }
                    KaiTrainer.KaiChatTrainer().kaiTrainChat(dict, "${Settings().getPath()}\\mangen\\${fileName.text}")
                }
            }

            button{
                text = "Clear"
                setOnAction {
                    editor.getChildren().clear()
                } 
            }

            button{
                text = "Load"
                setOnAction {
                    val fileChooser = FileChooser()
                    val fileChosen = fileChooser.showOpenDialog(currentWindow)
                    val dicto = KaiBrainGeneral.BrainMessageInformation().getMessgesDict(fileChosen)
                    var counter = 0
                    println(fileChosen.normalize())

                    editor.clear()
                    fileName.text = fileChosen.getName()

                    while(counter < dicto.count()){
                        editor.add(TextField(dicto.toList()[counter].toList()[0]), 0, counter)
                        editor.add(TextField(dicto.toList()[counter].toList()[1]), 1, counter)
                        counter++
                    }
                }
            }

        }

        add(scrolls, 0, 1)
    }
}
