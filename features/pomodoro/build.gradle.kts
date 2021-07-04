plugins {
    id(Plugins.ANDROID_DYNAMIC_FEATURE_PLUGIN)
    id(Plugins.KOTLIN_ANDROID_PLUGIN)
}

android {
    compileSdkVersion(AndroidVersion.COMPILE_SDK_VERSION)
    defaultConfig {
        minSdkVersion(AndroidVersion.MIN_SDK_VERSION)
        targetSdkVersion(AndroidVersion.TARGET_SDK_VERSION)
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    buildFeatures.viewBinding = true
}

dependencies {
    implementation(project(Modules.APP))
    implementation(project(Modules.COMMON))

    implementation(Deps.KOTLIN)

    implementation(Deps.MATERIAL)

    implementation(Deps.ANDROIDX_CORE_KTX)
    implementation(Deps.APPCOMPAT)
    implementation(Deps.CONSTRAINT_LAYOUT)
    implementation("androidx.appcompat:appcompat:1.3.0")
    implementation("com.google.android.material:material:1.3.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra["kotlin_version"]}")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
}