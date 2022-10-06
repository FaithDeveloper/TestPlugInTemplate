package com.github.test.template.listeners

import com.github.test.template.services.*
import com.intellij.openapi.components.*
import com.intellij.openapi.project.*

internal class MyProjectManagerListener : ProjectManagerListener {

    override fun projectOpened(project: Project) {
        if (project.name.equals("Test", ignoreCase = true)) {
            projectInstance = project
        }
        project.service<MyProjectService>()
    }

    override fun projectClosing(project: Project) {
        if (project.name.equals("Test", ignoreCase = true)) {
            projectInstance = null
        }
        super.projectClosing(project)
    }

    companion object {
        var projectInstance: Project? = null
    }
}
