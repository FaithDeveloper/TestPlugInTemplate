package com.github.faithdeveloper.testplugintemplate

import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider
import com.github.faithdeveloper.testplugintemplate.mvvm.*

class WizardTemplateProviderImpl : WizardTemplateProvider() {
	override fun getTemplates(): List<Template> = listOf(recyclerActivitySetupTemplate)
}