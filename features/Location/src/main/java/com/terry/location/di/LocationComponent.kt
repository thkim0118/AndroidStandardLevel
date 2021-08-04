package com.terry.location.di

import android.app.Activity
import com.terry.common.di.UseCaseDependencies
import com.terry.location.LocationMainActivity
import com.terry.location.MapActivity
import dagger.BindsInstance
import dagger.Component

/*
 * Created by Taehyung Kim on 2021-08-01
 */
@Component(
    dependencies = [UseCaseDependencies::class],
    modules = [LocationModule::class]
)
interface LocationComponent {

    fun inject(locationMainActivity: LocationMainActivity)
    fun inject(mapActivity: MapActivity)

    @Component.Factory
    interface Factory {
        fun create(
            dependentModule: UseCaseDependencies,
            @BindsInstance activity: Activity
        ): LocationComponent
    }
}



