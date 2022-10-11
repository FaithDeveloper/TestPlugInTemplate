package com.github.faithdeveloper.testplugintemplate.mvvm

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.addAllKotlinDependencies
import com.github.faithdeveloper.testplugintemplate.extensions.save
import com.github.faithdeveloper.testplugintemplate.extensions.toSnakeCase
import com.github.faithdeveloper.testplugintemplate.listeners.MyProjectManagerListener.Companion.projectInstance
import com.github.faithdeveloper.testplugintemplate.mvvm.template.classes.createRecyclerActivity
import com.github.faithdeveloper.testplugintemplate.mvvm.template.classes.createRecyclerAdapter
import com.github.faithdeveloper.testplugintemplate.mvvm.template.classes.createViewHolder
import com.github.faithdeveloper.testplugintemplate.mvvm.template.classes.createViewModel
import com.github.faithdeveloper.testplugintemplate.mvvm.template.layout.createRecyclerActivityLayout
import com.github.faithdeveloper.testplugintemplate.mvvm.template.layout.createViewHolderLayout
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.psi.PsiManager

fun RecipeExecutor.mvvmRecyclerActivitySetup(
    moduleData: ModuleTemplateData,
    packageName: String,
    className: String,
    activityLayoutName: String,
) {
    val (projectData, _, _, manifestOut) = moduleData
    val project = projectInstance ?: run {
        println("projectInstance is null")
        return
    }

    addAllKotlinDependencies(moduleData)

    val virtualFiles = ProjectRootManager.getInstance(project).contentSourceRoots
    val virtSrc = virtualFiles.firstOrNull { it.path.contains("app/src/main/java") } ?: return
    val virtRes = virtualFiles.firstOrNull { it.path.contains("app/src/main/res") } ?: return
    val directorySrc = PsiManager.getInstance(project).findDirectory(virtSrc) ?: return
    val directoryRes = PsiManager.getInstance(project).findDirectory(virtRes) ?: return

    val activityClass = "${className}Activity".replaceFirstChar { it }
    val adapterClass = "${className}RecyclerAdapter".replaceFirstChar { it }
    val viewHolderClass = "${className}ItemViewHolder".replaceFirstChar { it }
    val viewModelClass = "${className}ViewModel".replaceFirstChar { it }

    println("[check]packageName = $packageName")

    // Activity 추가 시 Manifest 에 추가를 원할 시 주석제거 (개선필요)
//    mergeXml(
//        manifestTemplateXml(projectData = projectData, packageName = packageName, activityClassName = "${className}Activity"),
//        manifestOut.resolve("AndroidManifest.xml")
//    )

    createRecyclerActivity(packageName, className, activityLayoutName, projectData)
        .save(directorySrc, packageName, "$activityClass.kt")

    createRecyclerAdapter(packageName, className)
        .save(directorySrc, "$packageName.adapter", "$adapterClass.kt")

    createViewHolder(packageName, className)
        .save(directorySrc, "$packageName.viewholder", "$viewHolderClass.kt")

    createViewModel(packageName, className)
        .save(directorySrc, "$packageName.viewmodel", "$viewModelClass.kt")

    createRecyclerActivityLayout(packageName, className)
        .save(directoryRes, "layout", "${activityLayoutName}.xml")

    createViewHolderLayout()
        .save(directoryRes, "layout", "item_${className.toSnakeCase()}.xml")
}