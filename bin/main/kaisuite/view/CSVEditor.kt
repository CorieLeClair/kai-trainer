package kaisuite.view

import javafx.event.EventTarget
import javafx.scene.control.Alert
import javafx.scene.control.TextField
import tornadofx.*

class CSVEditor : View("CSV Editor") {
    private val editor = gridpane()

    override val root = gridpane {
        hbox {
            button {
                text = "Back"
                setOnAction {
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

            val title = textfield { text = "Title.json"}

            button{
                //println(alert(Alert.AlertType.CONFIRMATION, "Save?", "Save chat file as ${title.text}").result)

                text = "Save"
                setOnAction {
                    var counter = 0
                    val dict = HashMap<String, String>()
                    repeat(editor.rowCount){
                        val firstColumn = editor.children[counter] as TextField
                        val secondColumn = editor.children[counter + 1] as TextField

                        dict[firstColumn.text] = secondColumn.text

                        counter += 2
                    }

                    println(dict)
                }
            }
        }

        add(editor, 0, 1)
    }
}
