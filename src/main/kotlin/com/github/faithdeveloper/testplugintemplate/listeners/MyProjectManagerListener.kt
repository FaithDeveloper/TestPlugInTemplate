package com.github.faithdeveloper.testplugintemplate.listeners


import com.github.faithdeveloper.testplugintemplate.services.MyProjectService
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManagerListener

internal class MyProjectManagerListener : ProjectManagerListener {

    //Android Studio에서 프로젝트를 열 때마다 해당 function 호출
    override fun projectOpened(project: Project) {
        println("######### WellCommon Project Name : ${project.name} #########")

        // 만약 특정 prefix Name 만 동작하고 싶을 시 주석 삭제
//        project.name.startsWith("Test", ignoreCase = true)
        projectInstance = project
//        }
        project.service<MyProjectService>()
    }

    //Android Studio에서 프로젝트를 닫을 때마다 해당 function 호출
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
