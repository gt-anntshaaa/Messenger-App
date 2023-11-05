plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    // DI Hilt
    kotlin("kapt")
    id("com.google.dagger.hilt.android")

    // Firebase
    id("com.google.gms.google-services")

    // SafeArgs - Navigation Components
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.messengerapp"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.messengerapp"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    // Allow references to generated code
    kapt {
        correctErrorTypes = true
    }

    buildFeatures{
        viewBinding = true
    }
}

dependencies {
    val activity_version = "1.6.1"
    val fragment_version = "1.5.7"
    val lifecycle_version = "2.6.2"
    val nav_version = "2.5.3"

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    // DI Hilt
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")

    // Firebase Auth
    implementation("com.google.firebase:firebase-auth-ktx:22.1.1")

    // Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:32.2.3"))
    implementation("com.google.firebase:firebase-analytics-ktx")

    // Firebase Cloud Firestore
    implementation("com.google.firebase:firebase-firestore-ktx:24.8.1")

    // Firebase Cloud Message
    implementation("com.google.firebase:firebase-messaging-ktx:23.2.1")

    // Firebase Storage
    implementation("com.google.firebase:firebase-storage-ktx:20.2.1")

    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")

    // Navigation Components
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

    // Picasso
    implementation ("com.squareup.picasso:picasso:2.8")

    // Rounded Image
    implementation ("com.makeramen:roundedimageview:2.3.0")

    // SDP SSP
    implementation ("com.intuit.sdp:sdp-android:1.1.0")
    implementation ("com.intuit.ssp:ssp-android:1.1.0")

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    implementation("androidx.activity:activity-ktx:$activity_version")
    implementation("androidx.fragment:fragment-ktx:$fragment_version")

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}