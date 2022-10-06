package com.github.test.template.mvvm.template.classes

import com.android.tools.idea.wizard.template.ProjectTemplateData

fun createRecyclerActivity(
    packageName: String,
    className: String,
    activityLayoutName: String,
    projectData: ProjectTemplateData
) = """
package $packageName
	
import ${projectData.applicationPackage}.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
    
class ${className}Activity : AppCompatActivity() {
}
""".trimIndent()
