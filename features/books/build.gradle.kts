import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id(Plugins.ANDROID_DYNAMIC_FEATURE_PLUGIN)
    id(Plugins.KOTLIN_ANDROID_PLUGIN)
    id(Plugins.KOTLIN_KAPT_PLUGIN)
    id(Plugins.DAGGER_HILT_PLUGIN)
}

android {
    compileSdkVersion(AndroidVersion.COMPILE_SDK_VERSION)
    defaultConfig {
        minSdkVersion(AndroidVersion.MIN_SDK_VERSION)
        targetSdkVersion(AndroidVersion.TARGET_SDK_VERSION)

        val key = gradleLocalProperties(rootDir).getProperty("interpark.api.key")
        buildConfigField("String", "interpark_key", key)
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures.viewBinding = true
}

dependencies {
    implementation(project(Modules.APP))
    implementation(project(Modules.CORE))

    implementation(Deps.KOTLIN)

    implementation(Deps.MATERIAL)

    implementation(Deps.ANDROIDX_CORE_KTX)
    implementation(Deps.APPCOMPAT)
    implementation(Deps.CONSTRAINT_LAYOUT)

    implementation(Deps.RETROFIT)
    implementation(Deps.RETROFIT_GSON)
    implementation(Deps.GLIDE)

    // Dagger Hilt
    implementation(Deps.DAGGER_HILT_ANDROID)
    kapt(Deps.DAGGER_HILT_COMPILER)
    // Dagger Hilt AndroidX & ViewModel
    implementation(Deps.DAGGER_HILT_VIEWMODEL)
    kapt(Deps.DAGGER_HILT_ANDROIDX_HILT_COMPILER)

    // 타 모듈에서 같은 라이브러리를 사용할 경우 same package error 가 발생한다.
    // 이를 해결하기 위해서는 모듈에 라이브러리 종속성을 추가할 경우 root 모듈,
    // 즉 app 모듈에 해당 라이브러리 종속성을 추가해주면 해결할 수 있다.
    // -> 모듈에 라이브러리 종속성을 추가할 경우
    //    app 모듈에서 사용하지 않더라도 라이브러리 종속성을 똑같이 추가해준다.
    implementation(Deps.LIFECYCLE_VIEWMODEL)

    implementation(Deps.ROOM_RUNTIME)
    implementation(Deps.ROOM_COMPILER)

    implementation(Deps.FRAGMENT)

    implementation(Deps.COROUTINE_LIVEDATA)
}