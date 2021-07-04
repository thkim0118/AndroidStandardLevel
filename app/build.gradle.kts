plugins {
    id(Plugins.ANDROID_APPLICATION_PLUGIN)
    id(Plugins.KOTLIN_ANDROID_PLUGIN)
    id(Plugins.KOTLIN_KAPT_PLUGIN)
}

android {
    compileSdkVersion(AndroidVersion.COMPILE_SDK_VERSION)

    defaultConfig {
        applicationId = AndroidVersion.APPLICATION_ID
        minSdkVersion(AndroidVersion.MIN_SDK_VERSION)
        targetSdkVersion(AndroidVersion.TARGET_SDK_VERSION)
        versionCode = AndroidVersion.VERSION_CODE
        versionName = AndroidVersion.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
    dynamicFeatures = mutableSetOf(
        Modules.DynamicFeature.BMI,
        Modules.DynamicFeature.CALCULATOR,
        Modules.DynamicFeature.DIARY,
        Modules.DynamicFeature.FRAME,
        Modules.DynamicFeature.LOTTO,
        Modules.DynamicFeature.POMODORO
    )
}

dependencies {
    implementation(project(Modules.COMMON))
    implementation(Deps.KOTLIN)

    implementation(Deps.MATERIAL)

    implementation(Deps.ANDROIDX_CORE_KTX)
    implementation(Deps.APPCOMPAT)
    implementation(Deps.CONSTRAINT_LAYOUT)
}