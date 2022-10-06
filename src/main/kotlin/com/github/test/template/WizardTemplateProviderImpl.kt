package com.github.test.template

import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider
import com.github.test.template.mvvm.*

class WizardTemplateProviderImpl : WizardTemplateProvider() {
	override fun getTemplates(): List<Template> = listOf(recyclerActivitySetupTemplate)
}