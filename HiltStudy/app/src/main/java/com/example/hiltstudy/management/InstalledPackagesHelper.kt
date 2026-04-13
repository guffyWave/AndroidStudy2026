package com.example.hiltstudy.management

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class InstalledPackagesHelper @Inject constructor(
    @ApplicationContext val context: Context
) {

    fun getPackageInfo(): String {
        return "PackageName: ${context.applicationInfo.packageName} , Name: ${context.applicationInfo.name} "
    }

}