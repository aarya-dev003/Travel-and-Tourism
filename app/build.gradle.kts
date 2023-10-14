plugins {
    id("com.android.application")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("com.google.gms.google-services")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.bharatyatrisathi"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.bharatyatrisathi"
        minSdk = 24
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment:2.5.3")
    implementation("de.hdodenhof:circleimageview:3.1.0")
    implementation("androidx.navigation:navigation-ui:2.5.3")
    implementation("com.google.android.gms:play-services-maps:18.1.0")
    implementation("com.google.firebase:firebase-inappmessaging-display-ktx:20.3.5")
    implementation("com.google.firebase:firebase-auth-ktx:22.1.2")
    implementation("com.google.android.gms:play-services-auth:20.7.0")
    implementation("com.google.firebase:firebase-config-ktx:21.4.1")
    implementation("com.google.firebase:firebase-messaging-ktx:23.2.1")


    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("de.hdodenhof:circleimageview:3.1.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


    implementation("com.google.firebase:firebase-database-ktx:20.2.2")
}