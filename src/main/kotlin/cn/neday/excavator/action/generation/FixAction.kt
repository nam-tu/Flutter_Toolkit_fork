package cn.neday.excavator.action.generation

import cn.neday.excavator.action.BaseGenerationAnAction

class FixAction : BaseGenerationAnAction() {
    override val command = "dart fix --apply"
    override val title = "Fix"
    override val successMessage = "Complete!\nRunning fix successfully."
    override val errorMessage = "Could not running fix!"
}