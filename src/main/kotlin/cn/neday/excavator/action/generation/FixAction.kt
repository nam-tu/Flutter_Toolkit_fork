package cn.neday.excavator.action.generation

import cn.neday.excavator.action.BaseGenerationAnAction

class FixAction : BaseGenerationAnAction() {
    override val commandList = listOf(
            "dart fix --apply",
            "dart format . --line-length=160",
            )
    override val title = "Fix"
    override val successMessage = "Complete!\nRunning fix successfully."
    override val errorMessage = "Could not running fix!"
}