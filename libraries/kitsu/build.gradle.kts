plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.google.devtools.ksp)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.chesire.nekome.kitsu"
    compileSdk = libs.versions.sdk.get().toInt()

    defaultConfig {
        minSdk = 21

        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    implementation(project(":libraries:core"))
    implementation(project(":libraries:datasource:auth"))

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core)
    implementation(libs.google.hilt.android)
    implementation(libs.kotlin.coroutines.android)
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.kotlin.result)
    implementation(libs.squareup.retrofit2)
    implementation(libs.squareup.retrofit2.converter.moshi)
    ksp(libs.google.hilt.android.compiler)
    ksp(libs.squareup.moshi.codegen)

    testImplementation(project(":testing"))
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
}
