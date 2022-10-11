package com.github.faithdeveloper.testplugintemplate.extensions

import com.android.tools.idea.wizard.template.*
import com.intellij.psi.*
import org.jetbrains.kotlin.idea.*
import java.util.*

fun String.toSnakeCase() = replace(humps, "_").lowercase(Locale.getDefault())
private val humps = "(?<=.)(?=\\p{Upper})".toRegex()

val defaultPackageNameParameter
    get() = stringParameter {
        name = "Package name"
        visible = { !isNewModule }
        default = "com.kcs.hello" //기본 패키지명
        constraints = listOf(Constraint.PACKAGE)
        suggest = { packageName }
    }

/**
 * v
 */
fun String.save(srcDir: PsiDirectory, subDirPath: String, fileName: String) {
    try {
        val destDir = subDirPath.split(".").toDir(srcDir)
        val psiFile = PsiFileFactory
            .getInstance(srcDir.project)
            .createFileFromText(fileName, KotlinLanguage.INSTANCE, this)
        if(destDir.findFile(fileName) == null) {
            destDir.add(psiFile)
        }
    } catch (exc: Exception) {
        exc.printStackTrace()
    }
}

fun List<String>.toDir(srcDir: PsiDirectory): PsiDirectory {
    var result = srcDir
    forEach {
        result = result.findSubdirectory(it) ?: result.createSubdirectory(it)
    }
    return result
}