package com.github.faithdeveloper.testplugintemplate.listeners

import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManagerListener
import com.github.faithdeveloper.testplugintemplate.services.MyProjectService

internal class MyProjectManagerListener : ProjectManagerListener {

    //Android Studio에서 프로젝트를 열 때마다 해당 fun이 호출됨
    override fun projectOpened(project: Project) {
        if (project.name.equals("Test", ignoreCase = true)) { 
        //나는 Test 라는 이름의 프로젝트를 만들어서 실행 했기 때문에 Test 라고 셋팅. 내가 사용하려는 프로젝트의 rootProject.name 과 동일하게 세팅 합니다.
            projectInstance = project
        }
        project.service<MyProjectService>()

        System.getenv("CI")
            ?: TODO("Don't forget to remove all non-needed sample code files with their corresponding registration entries in `plugin.xml`.")
    }
    
    //Android Studio에서 프로젝트를 닫을 때마다 해당 fun이 호출됨
    override fun projectClosing(project: Project) {
        if (project.name.equals("Test", ignoreCase = true)) {
            projectInstance = null
        }
        super.projectClosing(project)
    }

    companion object {
        //VirtaulFile 을 가져오기 위해 사용 되는데 프로젝트 네임이 같아야 사용 가능하게 만듦
        var projectInstance: Project? = null
    }
}
