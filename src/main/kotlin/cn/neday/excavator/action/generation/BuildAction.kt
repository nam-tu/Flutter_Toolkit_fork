package cn.neday.excavator.action.generation

import cn.neday.excavator.action.BaseGenerationAnAction

class BuildAction : BaseGenerationAnAction() {
    override val commandList = listOf(
            "dart run build_runner build --delete-conflicting-outputs"
    )
    override val title = "Rebuilding"
    override val successMessage = "Complete!\nRunning rebuild successfully."
    override val errorMessage = "Could not running rebuild!"
}