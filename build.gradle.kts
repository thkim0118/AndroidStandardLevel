// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
    dependencies {
        classpath(Plugins.CLASSPATH_GRADLE)
        classpath(Plugins.CLASSPATH_KOTLIN)
        classpath(Plugins.CLASSPATH_DAGGER_HILT)
        classpath(Plugins.CLASSPATH_GOOGLE_SERVICE)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()
        maven {
            setUrl("https://naver.jfrog.io/artifactory/maven/")
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
