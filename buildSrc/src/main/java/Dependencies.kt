object AppConfig {
    val id = "com.terry.androidstandardlevel"
    val versionCode = 1
    val versionName = "1.0"
}

object Modules {
    val app = ":app"

    val common = ":common"

    val bmi = ":features:bmi"
    val lotto = ":features:lotto"
    val diary = ":features:diary"
    val calculator = ":features:calculator"
    val frame = ":features:frame"
}

object Vers {
    val compileSdk = 30
    val targetSdk = 30
    val minSdk = 21

    val kotlin = "1.5.10"
    val gradle = "4.2.1"
    val moduleNav = "1.5"

    val appCompat = "1.3.0"
    val coreKtx = "1.5.0"
    val constraintLayout = "2.0.4"
    val room = "2.3.0"

    val materialX = "1.3.0"

    val junit = "4.12"

    val androidJunit = "1.1.2"
    val expressoCore = "3.3.0"
}

object Libs {
    val kotlinStd = "org.jetbrains.kotlin:kotlin-stdlib:${Vers.kotlin}"

    val materialX = "com.google.android.material:material:${Vers.materialX}"
}

object AndroidLibs {
    val coreKtx = "androidx.core:core-ktx:${Vers.coreKtx}"
    val appCompat = "androidx.appcompat:appcompat:${Vers.appCompat}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Vers.constraintLayout}"

    val roomRuntime = "androidx.room:room-runtime:${Vers.room}"
    val roomCompiler = "androidx.room:room-compiler:${Vers.room}"
}

object TestLibs {
    val junit = "junit:junit:${Vers.junit}"
}

object AndroidTestLibs {
    val junit = "androidx.test.ext:junit:${Vers.androidJunit}"
    val espresso = "androidx.test.espresso:espresso-core:${Vers.expressoCore}"
}