plugins {
    id("com.android.application")
    id("kotlin-android")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.muhammad.coutry.list.coutryinfo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.muhammad.coutry.list.coutryinfo"
        minSdk = 25
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            multiDexEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions{

        jvmTarget = "17"
    }
    kotlin {
        jvmToolchain(17)
    }

    buildFeatures {

        compose = true
        viewBinding = true

        lint {
            abortOnError = false
            checkReleaseBuilds = false
        }

        dataBinding {
            enable = true
        }
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.6"
    }
}

dependencies {


    val lifeCycleExtensionVersion = "1.1.1"
    val retrofitVersion = "2.9.0"
    val glideVersion = "4.9.0"
    val roomVersion = "2.6.1"
    val navVersion = "2.7.6"
    val preferencesVersion = "1.2.1"
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("androidx.recyclerview:recyclerview-selection:1.1.0")
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")
    implementation(platform("androidx.compose:compose-bom:2023.10.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")

    implementation ("androidx.core:core-splashscreen:1.0.1")

    implementation ("com.github.bumptech.glide:glide:4.16.0")

    implementation("androidx.compose.material3:material3")

    implementation ("android.arch.lifecycle:extensions:$lifeCycleExtensionVersion")

    implementation ("androidx.room:room-runtime:$roomVersion")

    annotationProcessor ("androidx.room:room-compiler:$roomVersion")

    // To use Kotlin Symbol Processing (KSP)

    ksp ("androidx.room:room-compiler:$roomVersion")

    // optional - RxJava2 support for Room

    implementation ("androidx.room:room-rxjava2:$roomVersion")

    // optional - RxJava3 support for Room

    implementation ("androidx.room:room-rxjava3:$roomVersion")

    // optional - Guava support for Room, including Optional and ListenableFuture

    implementation ("androidx.room:room-guava:$roomVersion")

    // optional - Test helpers

    testImplementation ("androidx.room:room-testing:$roomVersion")

    // optional - Paging 3 Integration

    implementation ("androidx.room:room-paging:$roomVersion")

    implementation ("androidx.room:room-runtime:$roomVersion")

    implementation ("androidx.room:room-ktx:$roomVersion")

    implementation ("androidx.legacy:legacy-support-v4:1.0.0")

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    implementation ("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation ("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation ("com.squareup.retrofit2:adapter-rxjava2:$retrofitVersion")

    implementation ("io.reactivex.rxjava2:rxjava:2.2.21")
    implementation ("io.reactivex.rxjava2:rxandroid:2.1.1")

    implementation ("com.github.bumptech.glide:glide:$glideVersion")

    implementation ("androidx.palette:palette-ktx:1.0.0")

    implementation ("androidx.preference:preference-ktx:$preferencesVersion")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.10.01"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}