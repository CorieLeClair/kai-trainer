package kaisuite.view

import tornadofx.*

class Home : View("Home") {
    override val root = gridpane {
        hbox {
            button {
                text = "CSV Trainer"
                setOnAction {
                    this@Home.replaceWith(CSVEditor::class,
                        transition = ViewTransition.Slide(0.5.seconds, ViewTransition.Direction.LEFT))
                }
            }

            button{
                text = "Sentiment Trainer"
                setOnAction {
                    this@Home.replaceWith(SentimentTrainer::class,
                        transition = ViewTransition.Slide(0.5.seconds, ViewTransition.Direction.LEFT))
                }
            }

            button{
                text = "Project Generator"
            }

            button{
                text = "Test Kai"
            }
        }
    }
}
