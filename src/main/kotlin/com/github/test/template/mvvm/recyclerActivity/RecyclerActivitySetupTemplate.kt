package com.github.test.template.mvvm

import com.android.tools.idea.wizard.template.*
import com.github.test.template.extensions.*

val recyclerActivitySetupTemplate
    get() = template {
        name = "Test RecyclerView Activity"
        description = "리사이클러뷰 액티비티"
        minApi = 16
        category = Category.Other // Check other categories
        formFactor = FormFactor.Mobile
        screens = listOf(
            WizardUiContext.FragmentGallery, WizardUiContext.MenuEntry,
            WizardUiContext.NewProject, WizardUiContext.NewModule
        )

        val packageNameParam = defaultPackageNameParameter
        val className = stringParameter {
            name = "Class Name"
            default = "Titie"
            help = "액티비티 생성 시 사용"
            constraints = listOf(Constraint.NONEMPTY)
        }

        val activityLayoutName = stringParameter {
            name = "Activity Layout Name"
            default = "Titie"
            help = "액티비티 레이아웃 생성 시 사용"
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = { activityToLayout(className.value.toSnakeCase()) }
        }

        widgets(
            TextFieldWidget(className),
            TextFieldWidget(activityLayoutName),
            PackageNameWidget(packageNameParam)
        )

        recipe = { data: TemplateData ->
            mvvmRecyclerActivitySetup(
                data as ModuleTemplateData,
                packageNameParam.value,
                className.value,
                activityLayoutName.value
            )
        }
    }