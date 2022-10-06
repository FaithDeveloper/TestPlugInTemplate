package com.github.test.template.services

import com.intellij.openapi.project.Project
import com.github.test.template.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
