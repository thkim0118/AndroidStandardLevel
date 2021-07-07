package com.thkim.calculator.di

import android.app.Activity
import com.terry.common.di.CoreModuleDependencies
import com.thkim.calculator.CalculatorMainActivity
import dagger.BindsInstance
import dagger.Component

/*
 * Created by Taehyung Kim on 2021-07-05
 */
@Component(
    dependencies = [CoreModuleDependencies::class],
    modules = [CalculatorModule::class]
)
interface CalculatorComponent {

    fun inject(calculatorMainActivity: CalculatorMainActivity)

    @Component.Factory
    interface Factory {
        fun create(
            dependentModule: CoreModuleDependencies,
            @BindsInstance activity: Activity
        ): CalculatorComponent
    }

}