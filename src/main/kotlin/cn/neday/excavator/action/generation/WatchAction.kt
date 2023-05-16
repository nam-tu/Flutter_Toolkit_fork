package cn.neday.excavator.action.generation

import cn.neday.excavator.action.BaseGenerationAnAction

class WatchAction : BaseGenerationAnAction() {
    override val command = "dart run build_runner watch --delete-conflicting-outputs"
    override val title = "Watching"
    override val successMessage = "Complete!\nRunning watch successfully."
    override val errorMessage = "Could not running watch!"
}