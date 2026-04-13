package com.example.hiltstudy.management

import android.content.Context
import androidx.appcompat.app.AlertDialog
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(ActivityComponent::class)
object UIBuilderModule {

    @Provides
    fun providesDialogBuilder(@ActivityContext context: Context): AlertDialog.Builder {
        return AlertDialog.Builder(context)
    }

}