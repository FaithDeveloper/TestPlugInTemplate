package com.github.test.template.mvvm.template.classes

fun createRecyclerAdapter(
    packageName: String,
    className: String
) = """
package $packageName.adapter
import androidx.recyclerview.widget.RecyclerView

class ${className}RecyclerAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

}

""".trimIndent()