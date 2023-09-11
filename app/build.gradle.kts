plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.keliceru.challengeprosegur"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.keliceru.challengeprosegur"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.keliceru.challengeprosegur.util.TestRunner"
    }

    buildTypes {
        release {
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
        dataBinding = true
    }

    testOptions {
        animationsDisabled = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.2")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.2")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.47")
    kapt("com.google.dagger:hilt-android-compiler:2.47")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.47")

    // Room
    implementation("androidx.room:room-runtime:2.6.0-beta01")
    kapt("androidx.room:room-compiler:2.6.0-beta01")
    implementation("androidx.room:room-ktx:2.6.0-beta01")

    // Turbine for flow testing
    testImplementation("app.cash.turbine:turbine:1.0.0")

    // Mockk for testing
    testImplementation("io.mockk:mockk:1.13.7")

    // Coroutine testing library
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")

    androidTestImplementation("com.google.dagger:hilt-android-testing:2.47")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.test:core-ktx:1.5.0")

    androidTestImplementation("androidx.navigation:navigation-testing:2.7.2")
    debugImplementation("androidx.fragment:fragment-testing:1.6.1")
}