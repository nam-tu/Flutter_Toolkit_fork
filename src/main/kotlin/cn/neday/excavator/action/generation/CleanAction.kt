package cn.neday.excavator.action.generation

import cn.neday.excavator.action.BaseGenerationAnAction

class CleanAction : BaseGenerationAnAction() {
    override val command = "./clean\ncd bat"
    override val title = "Cleaning"
    override val successMessage = "Complete!\nRunning clean successfully."
    override val errorMessage = "Could not running clean!"
}