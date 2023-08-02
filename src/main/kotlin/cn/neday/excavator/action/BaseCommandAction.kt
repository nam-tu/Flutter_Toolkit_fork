package cn.neday.excavator.action

import cn.neday.excavator.checker.ProjectChecker
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindowManager
import org.jetbrains.plugins.terminal.ShellTerminalWidget
import org.jetbrains.plugins.terminal.TerminalToolWindowFactory
import org.jetbrains.plugins.terminal.TerminalView
import java.io.IOException

abstract class BaseGenerationAnAction : BaseAnAction() {
    abstract val commandList: List<String>
    abstract val title: String
    abstract val successMessage: String
    abstract val errorMessage: String

    override fun update(event: AnActionEvent) {
        val project = event.getData(PlatformDataKeys.PROJECT)
        val projectPath = project?.basePath
        event.presentation.isEnabledAndVisible = ProjectChecker().check(projectPath).isOk
    }

    override fun actionPerformed(event: AnActionEvent) {
        event.project?.let { project ->
            project.basePath?.let { projectPath ->
                execCommand(project, projectPath)
            } ?: showErrorMessage("Current directory does not seem to be a project directory.")
        } ?: showErrorMessage("Current directory does not seem to be a project directory.")
    }

    private fun execCommand(project: Project, projectPath: String) {
        val terminalView = TerminalView.getInstance(project)
        val window = ToolWindowManager.getInstance(project)
            .getToolWindow(TerminalToolWindowFactory.TOOL_WINDOW_ID)
        if (window == null) {
            showErrorMessage("Please check that the following plugins are installed: Terminal")
            return
        }
        val terminalName = "Flutter-Toolkit"
        val content = window.contentManager.findContent(terminalName)
        if (content != null) {
            terminalView.closeTab(content)
        }
        val localTerminal = window.contentManager.findContent("Local")
        if (localTerminal != null) {
            terminalView.closeTab(localTerminal)
        }
        val terminalWidget: ShellTerminalWidget = terminalView.createLocalShellWidget(projectPath, terminalName)

        for (command in commandList) {
            try {
                terminalWidget.executeCommand(command)
            } catch (exception: IOException) {
                showErrorMessage("Cannot run command:" + command + "  " + exception.message)
            }
        }
    }
}
