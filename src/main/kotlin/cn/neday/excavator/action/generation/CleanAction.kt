package cn.neday.excavator.action.generation

import cn.neday.excavator.action.BaseGenerationAnAction

class CleanAction : BaseGenerationAnAction() {
    override val commandList = listOf(
            "Remove-Item -Path build -Recurse -Force -Confirm:\$false",
            "Remove-Item -Path .dart_tool -Recurse -Force -Confirm:\$false",
            "cd lib",
            "forfiles /s /m *.g.dart /c \"cmd /c del @file\"",
            "forfiles /s /m *.freezed.dart /c \"cmd /c del @file\"",
            "forfiles /s /m .gitkeep /c \"cmd /c del @file\"",
            "cd ..",
            "flutter clean",
            "dart pub cache clean -f",
            "dart pub get",
            "flutter pub get",
            "dart run build_runner build --delete-conflicting-outputs"
    )
    override val title = "Cleaning"
    override val successMessage = "Complete!\nRunning clean successfully."
    override val errorMessage = "Could not running clean!"
}