plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.paparazzi)
}

android {
    namespace = "com.example.reproducer_unsatisfiedlinkerror"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.reproducer_unsatisfiedlinkerror"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    testOptions {
        unitTests {
            // Removing this seems to make the issue disappear in this simple example
            isIncludeAndroidResources = true
        }
    }
}

tasks.withType<Test>().configureEach {
    // always re-run tests
    outputs.upToDateWhen { false }
    // Fork for every test class (this will make the tests succeed)
    // forkEvery = 1
}

// Disable unnecessary release build type
androidComponents {
    beforeVariants(selector().withBuildType("release")) { it.enable = false }
}

dependencies {
    testImplementation(libs.junit)
    testImplementation(libs.robolectric)
    androidTestImplementation(libs.androidx.junit)
}
