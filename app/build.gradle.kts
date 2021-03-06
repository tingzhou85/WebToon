import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("realm-android")
    id("org.jlleitschuh.gradle.ktlint")
}

android {
    compileSdkVersion(28)

    defaultConfig {
        applicationId = "com.pluu.webtoon"
        minSdkVersion(21)
        targetSdkVersion(28)
        versionCode = 46
        versionName = "1.5.1"
        vectorDrawables.useSupportLibrary = true
    }

    signingConfigs {
        getByName("debug") {
            storeFile = rootProject.file("debug.keystore")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }
    }

    buildTypes {
        getByName("debug") {
            signingConfig = signingConfigs.getByName("debug")
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"))
            proguardFiles(file("proguard-rules.pro"))
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    lintOptions {
        check("Interoperability")
    }

    packagingOptions {
        exclude("META-INF/atomicfu.kotlin_module")
    }

    useLibrary("android.test.mock")
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(Dep.AndroidX.annotation)
    implementation(Dep.AndroidX.activity)
    implementation(Dep.AndroidX.activityKtx)
    implementation(Dep.AndroidX.fragment)
    implementation(Dep.AndroidX.fragmentKtx)
    implementation(Dep.AndroidX.appcompat)
    implementation(Dep.AndroidX.coreKtx)
    implementation(Dep.AndroidX.lifecycleExtensions)

    // Android UI
    implementation(Dep.AndroidX.UI.recyclerview)
    implementation(Dep.AndroidX.UI.palette)
    implementation(Dep.AndroidX.UI.cardview)
    implementation(Dep.AndroidX.UI.preference)
    implementation(Dep.AndroidX.UI.browser)
    implementation(Dep.AndroidX.UI.constraintLayout)
    implementation(Dep.AndroidX.UI.material)

    // DI
    implementation(Dep.Koin.android)
    implementation(Dep.Koin.androidViewModel)

    // Jsoup
    implementation(Dep.jsoup)
    // Glide
    implementation(Dep.Glide.core)
    kapt(Dep.Glide.compiler)
    // OkHttp
    implementation(Dep.OkHttp.core)
    implementation(Dep.OkHttp.loggingInterceptor)
    // kotlin
    implementation(Dep.Kotlin.stdlibJvm)
    implementation(Dep.Kotlin.coroutinesCore)
    implementation(Dep.Kotlin.coroutinesAndroid)

    testImplementation(Dep.Test.junit)
    testImplementation(Dep.Test.assertJ)
    testImplementation(Dep.Test.mockito)
}

kapt {
    useBuildCache = true
}

androidExtensions {
    isExperimental = true
}

ktlint {
    android.set(true)
    debug.set(true)
    verbose.set(true)
    reporters.set(listOf(ReporterType.CHECKSTYLE))
    ignoreFailures.set(true)
}
