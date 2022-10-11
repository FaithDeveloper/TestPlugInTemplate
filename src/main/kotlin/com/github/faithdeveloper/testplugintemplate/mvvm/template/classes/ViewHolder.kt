package com.github.faithdeveloper.testplugintemplate.mvvm.template.classes

fun createViewHolder(
    packageName: String,
    className: String
) = """
package $packageName.viewholder
import androidx.recyclerview.widget.RecyclerView
import ${packageName}.databinding.Item${className}Binding
    
class ${className}ItemViewHolder(val binding: Item${className}Binding)
 : RecyclerView.ViewHolder(binding.root)
""".trimIndent()