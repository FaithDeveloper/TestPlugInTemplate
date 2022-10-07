package com.github.test.template.listeners

import com.github.test.template.services.MyProjectService
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManagerListener

internal class MyProjectManagerListener : ProjectManagerListener {

    override fun projectOpened(project: Project) {
        println("######### WellCommon Project Name : ${project.name} #########")

        // 만약 특정 prefix Name 만 동작하고 싶을 시 주석 삭제
//        project.name.startsWith("Test", ignoreCase = true)
        projectInstance = project
//        }
        project.service<MyProjectService>()
    }

    override fun projectClosing(project: Project) {
        // 만약 특정 prefix Name 만 동작하고 싶을 시 주석 삭제
//        project.name.startsWith("Test", ignoreCase = true)
        projectInstance = null
//        }
        super.projectClosing(project)
    }

    companion object {
        var projectInstance: Project? = null
    }
}
