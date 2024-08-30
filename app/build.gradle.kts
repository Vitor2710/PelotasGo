plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}
buildscript {
    val kotlinVersion by extra("1.9.22") // ou a versão mais recente disponível

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.22")
    }
}

android {
    packaging{
        resources.excludes.add("META-INF/INDEX.LIST")
        resources.excludes.add("META-INF/*.SF")
        resources.excludes.add("META-INF/*.DSA")
        resources.excludes.add("META-INF/*.RSA")
        resources.excludes.add("META-INF/DEPENDENCIES")
        resources.excludes.add("META-INF/LICENSE")
        resources.excludes.add("META-INF/LICENSE.txt")
        resources.excludes.add("META-INF/license.txt")
        resources.excludes.add("META-INF/NOTICE")
        resources.excludes.add("META-INF/NOTICE.txt")
        resources.excludes.add("META-INF/notice.txt")
        resources.excludes.add("META-INF/ASL2.0")
        resources.excludes.add("META-INF/*.kotlin_module")
        resources.pickFirsts.add("META-INF/INDEX.LIST")
        resources.pickFirsts.add("META-INF/io.netty.versions.properties")
    }

    namespace = "com.example.palworldgo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.palworldgo"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}
dependencies {
    implementation ("org.osmdroid:osmdroid-android:6.1.14")
    implementation ("com.google.android.gms:play-services-maps:18.0.1")
    implementation("com.google.android.gms:play-services-location:21.2.0")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation(kotlin("stdlib-jdk8"))

}