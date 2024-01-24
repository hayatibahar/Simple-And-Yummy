import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.navigation.safeargs)
}

android {
    namespace = "com.hayatibahar.simpleandyummy"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.hayatibahar.simpleandyummy"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val apiKey = gradleLocalProperties(rootDir).getProperty("API_KEY")
        buildConfigField("String", "API_KEY", apiKey)
        buildConfigField("String", "BASE_URL", "\"https://api.spoonacular.com/\"")
        buildConfigField(
            "String",
            "BASE_IMAGE_URL",
            "\"https://spoonacular.com/cdn/ingredients_100x100/\""
        )
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        buildConfig = true
        viewBinding = true
    }

}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Lifecycle
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.lifecycle.extensions)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.gson.converter)
    implementation(libs.okhttp)
    implementation(libs.interceptor)

    // Navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // Lottie
    implementation(libs.lottie)

    // Glide
    implementation(libs.glide)

    // Room
    implementation(libs.bundles.room)
    ksp(libs.room.compiler)

    // Jsoup
    implementation(libs.jsoup)

    // ViewPager2
    implementation(libs.androidx.viewpager2)

    // DataStore
    implementation(libs.androidx.datastore.preferences)

    // Swipe Refresh Layout
    implementation(libs.androidx.swiperefreshlayout)

    // Flex Box
    implementation (libs.flexbox)


}