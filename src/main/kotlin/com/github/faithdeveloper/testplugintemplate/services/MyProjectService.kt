package com.github.faithdeveloper.testplugintemplate.services

import com.github.faithdeveloper.testplugintemplate.MyBundle
import com.intellij.openapi.project.Project

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
