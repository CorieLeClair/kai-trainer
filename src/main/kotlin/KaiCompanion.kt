import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.scene.layout.BorderPane
import javafx.scene.layout.GridPane
import javafx.scene.layout.HBox
import javafx.stage.Stage
import kai.kaibrain.KaiTrainer.KaiChatTrainer
import py4j.Py4JNetworkException
import kotlin.collections.HashMap

class KaiCompanion : Application() {
    override fun start(stage: Stage) {
        // HOME //
        val gridHome = GridPane()

        // buttons
        val buttonOpenProject = Button("Open Project")
        val buttonNewProject = Button("New Project")

        buttonNewProject.setOnAction {
            stage.close()
            mainMenu()
        }

        // adding to grid pane
        gridHome.add(buttonNewProject, 0, 0)
        gridHome.add(buttonOpenProject, 0, 1)


        // stage settings
        val homeScene = Scene(gridHome)
        stage.isResizable = false
        stage.scene = homeScene
        stage.height = 400.0
        stage.width = 500.0
        stage.show()
    }


    private fun mainMenu(){
        val mainBorder = BorderPane()
        val menuHBox = HBox()

        // menu
        val trainChatButton = Button("Train Chat/CSV")

        trainChatButton.setOnAction {
            chatWindowCustomCSV()
        }

        // menu
        menuHBox.children.add(trainChatButton)

        // border
        mainBorder.top = menuHBox

        // window settings
        val mainMenuScene = Scene(mainBorder)
        val mainMenuWindow = Stage()
        mainMenuWindow.scene = mainMenuScene
        mainMenuWindow.height = 400.0
        mainMenuWindow.width = 500.0
        mainMenuWindow.show()
    }

    private fun chatWindowCustomCSV(){
        // trainer
        val mainMenuWindow = Stage()
        val dict = HashMap<String, String>()


        var counter = 0

        val grid = GridPane()
        val gridMenu = GridPane()
        val gridCSV = GridPane()

        // menu
        val buttonAddRow = Button("Add Row")
        val buttonTrain = Button("Train")
        val buttonSave = Button("Save & Quit")
        val inputFile = TextField()

        buttonAddRow.setOnAction {
            gridCSV.add(TextField(), 0, counter)
            gridCSV.add(TextField(), 1, counter)
            counter++
        }

        buttonTrain.setOnAction {
            var counterRow = 0


            while(counter <= gridCSV.children.count() - 1){
                try {
                    val childOne = gridCSV.children[counterRow] as TextField
                    val childTwo = gridCSV.children[counterRow + 1] as TextField

                    println(childOne.text)
                    dict[childOne.text] = childTwo.text
                    counterRow += 1
                } catch (exception : IndexOutOfBoundsException){
                    break
                }
            }

            KaiChatTrainer().kaiTrainChat(dict, inputFile.text)
        }

        gridMenu.add(buttonAddRow, 0, 0)
        gridMenu.add(buttonTrain, 1, 0)
        gridMenu.add(buttonSave, 0, 1)
        gridMenu.add(inputFile, 1, 1)

        // csv editor


        // grid
        grid.add(gridMenu, 0, 0)
        grid.add(gridCSV, 0, 1)

        // window settings
        val mainMenuScene = Scene(grid)
        mainMenuWindow.scene = mainMenuScene
        mainMenuWindow.height = 400.0
        mainMenuWindow.width = 500.0
        mainMenuWindow.show()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(KaiCompanion::class.java)
        }
    }
}

