package com.terry.airbnb.di

import android.app.Activity
import com.terry.airbnb.HouseMainActivity
import com.terry.common.di.UseCaseDependencies
import dagger.BindsInstance
import dagger.Component

/*
 * Created by Taehyung Kim on 2021-07-25
 */
@Component(
    dependencies = [UseCaseDependencies::class],
    modules = [HouseModule::class]
)
interface HouseComponent {

    fun inject(houseMainActivity: HouseMainActivity)

    @Component.Factory
    interface Factory {
        fun create(
            dependencies: UseCaseDependencies,
            @BindsInstance activity: Activity
        ): HouseComponent
    }

}