import com.android.build.gradle.ProguardFiles.getDefaultProguardFile
import org.gradle.internal.impldep.com.amazonaws.util.XpathUtils.asNode
import groovy.util.Node

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("maven-publish")
}

android {
    namespace = "com.tapmind.customadapter_admob"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
        version = "1.0"

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
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
//    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    compileOnly("com.google.android.gms:play-services-ads:23.6.0")
    implementation("com.github.ravirising26:TapMindSdk:1.0.3")
}

publishing {
    publications {
        create<MavenPublication>("release") {
            afterEvaluate {
                from(components["release"])
            }

            groupId = "com.github.ravirising26"
            artifactId = "CustomAdapter-admob"
            version = "1.0.0"

            pom.withXml {
                val root = asNode()
                val depsNode = root.children()
                    .filterIsInstance<Node>()
                    .firstOrNull { it.name() == "dependencies" }

                depsNode?.let { root.remove(it) }
            }
        }
    }
}