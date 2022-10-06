package com.github.test.template.mvvm

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.*
import com.github.test.template.extensions.*
import com.github.test.template.listeners.MyProjectManagerListener.Companion.projectInstance
import com.github.test.template.mvvm.template.classes.*
import com.github.test.template.mvvm.template.layout.*
import com.intellij.openapi.roots.*
import com.intellij.psi.*

fun RecipeExecutor.mvvmRecyclerActivitySetup(
    moduleData: ModuleTemplateData,
    packageName: String,
    className: String,
    activityLayoutName: String,
) {
    val (projectData, _, _, manifestOut) = moduleData
    val project = projectInstance ?: return

    addAllKotlinDependencies(moduleData)

    val virtualFiles = ProjectRootManager.getInstance(project).contentSourceRoots
    val virtSrc = virtualFiles.firstOrNull { it.path.contains("app/src/main/java") }?:return
    val virtRes = virtualFiles.firstOrNull { it.path.contains("app/src/main/res") }?:return
    val directorySrc = PsiManager.getInstance(project).findDirectory(virtSrc)?:return
    val directoryRes = PsiManager.getInstance(project).findDirectory(virtRes)?:return

    val activityClass = "${className}Activity".capitalize()
    val adapterClass = "${className}RecyclerAdatper".capitalize()
    val viewHolderClass = "${className}ItemViewHolder".capitalize()
    val viewModelClass = "${className}ViewModel".capitalize()

    mergeXml(
        manifestTemplateXml(packageName, "${className}Activity"),
        manifestOut.resolve("AndroidManifest.xml")
    )

    createRecyclerActivity(packageName, className, activityLayoutName, projectData)
        .save(directorySrc, packageName, "$activityClass.kt")

    createRecyclerAdapter(packageName, className)
        .save(directorySrc, "$packageName.adapter", "$adapterClass.kt")

    createViewHolder(packageName, className)
        .save(directorySrc, "$packageName.viewHolder", "$viewHolderClass.kt")

    createViewModel(packageName, className)
        .save(directorySrc, "$packageName.viewModel", "$viewModelClass.kt")

    createRecyclerActivityLayout(packageName, className)
        .save(directoryRes, "layout", "${activityLayoutName}.xml")

    createViewHolderLayout()
        .save(directoryRes, "layout", "item_${className.toSnakeCase()}.xml")
}