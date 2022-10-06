package com.github.test.template.mvvm.template.classes

fun createViewHolder(
    packageName: String,
    className: String
) = """
package $packageName
import androidx.recyclerview.widget.RecyclerView
import com.kcs.testapplication.databinding.Item${className}Binding
    
class ${className}ItemViewHolder(val binding: Item${className}Binding)
 : RecyclerView.ViewHolder(binding.root)
""".trimIndent()