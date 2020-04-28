package kaisuite.view

import kai.kaibrain.KaiTrainer
import kai.kaibrain.KaiTrainer.KaiSentimentTrainer
import kaisuite.Settings
import tornadofx.*

class SentimentTrainer : View("Sentiment Trainer") {
    private val noun = textfield {
        promptText = "Noun"
    }

    private val slider = slider {
        isShowTickLabels = true
        isShowTickMarks = false
        max = 10.0
        min = 0.0
        value = 5.0
        majorTickUnit = 1.0
        blockIncrement = 1.0
        isSnapToTicks = true
    }

    private val controls = hbox {
        button {
            text = "Back"
            setOnAction {
                this@SentimentTrainer.replaceWith(Home::class,
                    transition = ViewTransition.Slide(0.5.seconds, ViewTransition.Direction.RIGHT))
            }
        }

        val fileName = textfield { text = "Title.json"}

        button{
            //println(alert(Alert.AlertType.CONFIRMATION, "Save?", "Save chat file as ${title.text}").result)
            text = "Save"
            setOnAction {
                println("${Settings().getSetting("projectpath")}/${fileName.text}")
                KaiSentimentTrainer().startTrainer("Trump", 5, "${Settings().getSetting("projectpath")}\\${fileName.text}")
            }
        }
    }
    override val root = gridpane {
        add(controls, 0, 0)
        add(slider, 0, 1)
        add(noun, 1, 1)
    }
}
