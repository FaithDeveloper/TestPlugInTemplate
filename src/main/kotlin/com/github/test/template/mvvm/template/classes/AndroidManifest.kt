package com.github.test.template.mvvm.template.classes

fun manifestTemplateXml(packageName: String, activityClassName: String) = """
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:tools="http://schemas.android.com/tools"
package="com.kcs.testapplication">

    <application>
        <activity android:name="$packageName.$activityClassName"/>   
    </application>
</manifest>
        """