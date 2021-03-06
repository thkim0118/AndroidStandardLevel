import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id(Plugins.ANDROID_APPLICATION_PLUGIN)
    id(Plugins.KOTLIN_ANDROID_PLUGIN)
    id(Plugins.KOTLIN_KAPT_PLUGIN)
    id(Plugins.DAGGER_HILT_PLUGIN)
    id(Plugins.GOOGLE_SERVICE)
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

        val fbAppId = gradleLocalProperties(rootDir).getProperty("facebook.app.id")
        val fbLoginProtocolScheme =
            gradleLocalProperties(rootDir).getProperty("facebook.login.protocol.scheme")
        val naverClientId = gradleLocalProperties(rootDir).getProperty("naver.client.id")
        val googleApiKey = gradleLocalProperties(rootDir).getProperty("google.api.key")
        resValue("string", "facebook_app_id", fbAppId)
        resValue("string", "fb_login_protocol_scheme", fbLoginProtocolScheme)
        resValue("string", "naver_client_id", naverClientId)
        resValue("string", "google_key", googleApiKey)
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
        Modules.DynamicFeature.POMODORO,
        Modules.DynamicFeature.AUDIO_RECORDER,
        Modules.DynamicFeature.WEB_VIEWER,
        Modules.DynamicFeature.NOTIFICATION,
        Modules.DynamicFeature.REMOTE_CONFIG,
        Modules.DynamicFeature.ALARM,
        Modules.DynamicFeature.BOOKS,
        Modules.DynamicFeature.TINDER,
        Modules.DynamicFeature.TRANSACTION,
        Modules.DynamicFeature.AIRBNB,
        Modules.DynamicFeature.VIDEO_PLAYER,
        Modules.DynamicFeature.MUSIC_PLAYER,
        Modules.DynamicFeature.LOCATION,
        Modules.DynamicFeature.ARCHITECTURE,
        Modules.DynamicFeature.WEEKLY_STUDY,
        Modules.DynamicFeature.MOTION_INTRO
    )
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    api(project(Modules.CORE))
    implementation(Deps.KOTLIN)

    implementation(Deps.MATERIAL)

    implementation(Deps.ANDROIDX_CORE_KTX)
    implementation(Deps.APPCOMPAT)
    implementation(Deps.CONSTRAINT_LAYOUT)

    // Dagger Hilt
    implementation(Deps.DAGGER_HILT_ANDROID)
    kapt(Deps.DAGGER_HILT_COMPILER)
    // Dagger Hilt AndroidX & ViewModel
    implementation(Deps.DAGGER_HILT_VIEWMODEL)
    kapt(Deps.DAGGER_HILT_ANDROIDX_HILT_COMPILER)

    implementation(platform(Deps.FIREBASE_BOM))
    implementation(Deps.FIREBASE_ANALYTICS)
    implementation(Deps.FIREBASE_MESSAGING)
    implementation(Deps.FIREBASE_CONFIG)
    implementation(Deps.FIREBASE_AUTH)
    implementation(Deps.FIREBASE_DATABASE)
    implementation(Deps.FIREBASE_STORAGE)

    implementation(Deps.LIFECYCLE_VIEWMODEL)

    implementation(Deps.ROOM_RUNTIME)
    implementation(Deps.ROOM_COMPILER)

    implementation(Deps.RETROFIT)
    implementation(Deps.RETROFIT_GSON)
    implementation(Deps.OK_HTTP)
    implementation(Deps.OK_HTTP_LOGGING)

    implementation(Deps.FRAGMENT)

    implementation(Deps.COROUTINE_LIVEDATA)
    implementation(Deps.LIVEDATA)
    implementation(Deps.LIVEDATA_RUNTIME)

    implementation(Deps.FACEBOOK_LOGIN)

    implementation(Deps.GLIDE)

    implementation(Deps.NAVER_MAP)
    implementation(Deps.PLAY_SERVICES_LOCATION)

    implementation(Deps.EXO_PLAYER)

}