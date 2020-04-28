package kaisuite.view

import kai.configuration.KaiConfig
import tornadofx.*
import java.io.File

class Preview : View("My View") {

    private val texts = gridpane {
    }

    val scrolls = scrollpane {
        add(texts)
    }

    var counter = 0


    override val root = vbox {

        hbox {

            val inputText = textfield {
                promptText = "Say something!"
            }

            button {
                text = "Enter"
                setOnAction {
                    texts.add(button { text = inputText.text }, 0, counter)
                    texts.add(button { text = kaiTalk(inputText.text) }, 1, counter + 1)
                    counter += 2
                }
            }
        }

        scrollpane {
            add(texts)
        }

    }
}

fun kaiTalk(input : String) : String{
    KaiConfig.Kai().defineProjectFolder(File("C:\\Users\\corie\\OneDrive\\Desktop\\kaiProject"))
    KaiConfig.Kai().definePythonInterpreter(File("C:\\Users\\corie\\OneDrive\\Documents\\kai\\KaiCompanion\\pai"))
    //KaiConfig.Kai().enableAutoThink(true)

    KaiConfig.Kai().defineInput(input)
    KaiConfig.Kai().runConversation(true)
    println(KaiConfig.Kai().getKaiResponse())
    return KaiConfig.Kai().getKaiResponse()
}
