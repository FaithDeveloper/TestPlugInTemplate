package com.github.faithdeveloper.testplugintemplate.mvvm

import com.android.tools.idea.wizard.template.*
import com.github.faithdeveloper.testplugintemplate.extensions.defaultPackageNameParameter
import com.github.faithdeveloper.testplugintemplate.extensions.toSnakeCase

val recyclerActivitySetupTemplate
    get() = template {
        name = "MVVM RecyclerView Activity"
        description = "This Template make RecyclerView Template with MVVM Architecture."
        minApi = 21
        category = Category.Other // Check other categories
        formFactor = FormFactor.Mobile
        screens = listOf(
            WizardUiContext.FragmentGallery, WizardUiContext.MenuEntry,
            WizardUiContext.NewProject, WizardUiContext.NewModule
        )

        val packageNameParam = defaultPackageNameParameter
        val className = stringParameter {
            name = "Class Name"
            default = "" //ex) default = "RecyclerViewActivity"
            help = "Please, Input Class Name."
            constraints = listOf(Constraint.NONEMPTY)
        }

        val activityLayoutName = stringParameter {
            name = "Activity Layout Name."
            default = "" //ex) default = "RecyclerView"
            help = "Please, Input Layout Name"
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = { activityToLayout(className.value.toSnakeCase()) }
        }

        widgets(
            TextFieldWidget(className),
            TextFieldWidget(activityLayoutName),
            PackageNameWidget(packageNameParam)
        )

        recipe = { data: TemplateData ->
            // 텝플릿 생성
            mvvmRecyclerActivitySetup(
                data as ModuleTemplateData,
                packageNameParam.value,
                className.value,
                activityLayoutName.value
            )
        }
    }