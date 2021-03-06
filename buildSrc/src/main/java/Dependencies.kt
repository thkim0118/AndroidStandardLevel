object Deps {
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib:${PluginVersion.KOTLIN_VERSION}"

    const val ANDROIDX_CORE_KTX = "androidx.core:core-ktx:${Version.CORE_KTX_VERSION}"

    // AppCompat
    const val APPCOMPAT = "androidx.appcompat:appcompat:${Version.APPCOMPAT_VERSION}"

    // Material
    const val MATERIAL = "com.google.android.material:material:${Version.MATERIAL_VERSION}"

    const val LIFECYCLE_VIEWMODEL =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.LIFECYCLE}"

    // ConstraintLayout
    const val CONSTRAINT_LAYOUT =
        "androidx.constraintlayout:constraintlayout:${Version.CONSTRAINT_LAYOUT_VERSION}"

    // Dagger Hilt
    const val DAGGER_HILT_ANDROID = "com.google.dagger:hilt-android:${Version.DAGGER_HILT_VERSION}"
    const val DAGGER_HILT_COMPILER =
        "com.google.dagger:hilt-android-compiler:${Version.DAGGER_HILT_VERSION}"

    // Dagger Hilt AndroidX
    const val DAGGER_HILT_VIEWMODEL =
        "androidx.hilt:hilt-lifecycle-viewmodel:${Version.DAGGER_HILT_ANDRIODX}"
    const val DAGGER_HILT_ANDROIDX_HILT_COMPILER =
        "androidx.hilt:hilt-compiler:${Version.DAGGER_HILT_ANDRIODX}"

    const val ROOM_RUNTIME = "androidx.room:room-runtime:${Version.ROOM_VERSION}"
    const val ROOM_COMPILER = "androidx.room:room-compiler:${Version.ROOM_VERSION}"
    const val ROOM_KTX = "androidx.room:room-ktx:${Version.ROOM_VERSION}"

    const val FRAGMENT = "androidx.fragment:fragment-ktx:${Version.FRAGMENT_VERSION}"

    // Coroutines
    const val COROUTINES_CORE =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.COROUTINES_VERSION}"
    const val COROUTINES_ANDROID =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.COROUTINES_VERSION}"

    const val COROUTINE_LIVEDATA = "androidx.lifecycle:lifecycle-livedata-ktx:${Version.LIFECYCLE}"
    const val LIVEDATA = "androidx.lifecycle:lifecycle-livedata-ktx:${Version.LIFECYCLE}"
    const val LIVEDATA_RUNTIME = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.LIFECYCLE}"

    const val SWIPE_REFRESH_LAYOUT =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Version.SWIPE_REFRESH_LAYOUT_VERSION}"

    const val FIREBASE_BOM =
        "com.google.firebase:firebase-bom:${Version.FIREBASE_VERSION}"
    const val FIREBASE_ANALYTICS =
        "com.google.firebase:firebase-analytics-ktx"
    const val FIREBASE_MESSAGING =
        "com.google.firebase:firebase-messaging-ktx"
    const val FIREBASE_CONFIG =
        "com.google.firebase:firebase-config-ktx"
    const val FIREBASE_AUTH =
        "com.google.firebase:firebase-auth-ktx"
    const val FIREBASE_DATABASE =
        "com.google.firebase:firebase-database-ktx"
    const val FIREBASE_STORAGE =
        "com.google.firebase:firebase-storage-ktx"

    const val RETROFIT =
        "com.squareup.retrofit2:retrofit:${Version.RETROFIT_VERSION}"

    const val RETROFIT_GSON =
        "com.squareup.retrofit2:converter-gson:${Version.RETROFIT_VERSION}"

    const val OK_HTTP =
        "com.squareup.okhttp3:okhttp:${Version.OK_HTTP_VERSION}"

    const val OK_HTTP_LOGGING =
        "com.squareup.okhttp3:logging-interceptor:${Version.OK_HTTP_VERSION}"

    const val GLIDE =
        "com.github.bumptech.glide:glide:${Version.GLIDE_VERSION}"

    const val FACEBOOK_LOGIN =
        "com.facebook.android:facebook-login:${Version.FACEBOOK_LOGIN_VERSION}"

    const val CARD_STACK_VIEW =
        "com.yuyakaido.android:card-stack-view:${Version.CARD_STACK_VIEW_VERSION}"

    const val NAVER_MAP =
        "com.naver.maps:map-sdk:${Version.NAVER_MAP_VERSION}"

    const val PLAY_SERVICES_LOCATION =
        "com.google.android.gms:play-services-location:${Version.PLAY_SERVICES_LOCATION_VERSION}"

    const val PLAY_SERVICES_MAPS =
        "com.google.android.gms:play-services-maps:${Version.PLAY_SERVICES_MAPS_VERSION}"

    const val EXO_PLAYER =
        "com.google.android.exoplayer:exoplayer:${Version.EXO_PLAYER_VERSION}"
}

object TestDeps {
    const val JUNIT = "junit:junit:${TestVersion.JUNIT_VERSION}"
    const val HAMCREST = "org.hamcrest:hamcrest-all:${TestVersion.HAMCREST_VERSION}"
    const val ANDROID_TEST_CORE = "androidx.test:core-ktx:${TestVersion.ANDROID_TEST_CORE}"
    const val ANDROID_ARCH_CORE = "androidx.arch.core:core-testing:${TestVersion.ANDROID_ARCH_TEST}"
    const val MOCKITO = "org.mockito:mockito-core:${TestVersion.MOCKITO_VERSION}"
    const val ANDROID_JUNIT = "androidx.test.ext:junit:${TestVersion.ANDROID_JUNIT}"
    const val ANDROID_ESPRESSO = "androidx.test.espresso:espresso-core:${TestVersion.ESPRESSO_VERSION}"
}
