plugins {
    id(Plugins.ANDROID_DYNAMIC_FEATURE_PLUGIN)
    id(Plugins.KOTLIN_ANDROID_PLUGIN)
    id(Plugins.KOTLIN_KAPT_PLUGIN)
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

    implementation(Deps.FRAGMENT)

    testImplementation(TestDeps.JUNIT)
    testImplementation(TestDeps.HAMCREST)
    testImplementation(TestDeps.ANDROID_TEST_CORE)
    testImplementation(TestDeps.ANDROID_ARCH_CORE)
    testImplementation(TestDeps.MOCKITO)
    androidTestImplementation(TestDeps.ANDROID_JUNIT)
    androidTestImplementation(TestDeps.ANDROID_ESPRESSO)
}