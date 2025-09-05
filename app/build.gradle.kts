plugins {
    alias(libs.plugins.android.application)

    id("com.google.gms.google-services")



}

android {
    namespace = "com.ezzco.eduplatform"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ezzco.eduplatform"
        minSdk = 24
        targetSdk = 34
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
}

dependencies {

    implementation("androidx.fragment:fragment:1.3.6")
    implementation("androidx.viewpager:viewpager:1.0.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.8.0")
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.database)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(platform("com.google.firebase:firebase-bom:33.1.2"))
    implementation("com.google.firebase:firebase-analytics")
    implementation ("androidx.cardview:cardview:1.0.0")
    implementation ("com.github.ybq:Android-SpinKit:1.4.0")


}