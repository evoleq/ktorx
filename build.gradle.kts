

plugins {
    //java
    kotlin("multiplatform") version Config.Versions.kotlinMppPlugin
    id ("com.github.hierynomus.license") version "0.15.0"
    `maven-publish`
    maven
    id ("com.jfrog.bintray") version "1.8.0"
    id("org.jetbrains.dokka") version "0.9.17"
    kotlin("plugin.serialization") version Config.Versions.kotlinSerializationPlugin
}

group = Config.Projects.Ktorx.group
version = Config.Projects.Ktorx.version//+"-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
    maven ( "https://dl.bintray.com/kotlin/ktor" )
    maven  ("https://dl.bintray.com/kotlin/kotlinx")
}

kotlin {
    /* Targets configuration omitted.
    *  To find out how to configure the targets, please follow the link:
    *  https://kotlinlang.org/docs/reference/building-mpp-with-gradle.html#setting-up-targets */
    jvm().compilations["main"].defaultSourceSet {
        dependencies {
            implementation(kotlin("stdlib-jdk8"))
            implementation(kotlin("reflect"))
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Config.Versions.coroutines}")
    
            // evoleq
            implementation( Config.Dependencies.evoleqCore )
            implementation("org.evoleq:mathcat-result-jvm:${Config.Versions.mathcat}")
            implementation("org.evoleq:mathcat-core-jvm:${Config.Versions.mathcat}")
            implementation("org.evoleq:mathcat-structure-jvm:${Config.Versions.mathcat}")
            implementation("org.evoleq:mathcat-morphism-jvm:${Config.Versions.mathcat}")
            implementation("org.evoleq:mathcat-state-jvm:${Config.Versions.mathcat}")
            
            implementation(Config.Dependencies.kotlinSerializationRuntime)
            
            implementation ("org.slf4j:slf4j-nop:1.7.25")
            implementation("io.ktor:ktor-serialization:${Config.Versions.ktor}")
            implementation("io.ktor:ktor-websockets:${Config.Versions.ktor}")
            implementation("io.ktor:ktor-client-core:${Config.Versions.ktor}")
            implementation("io.ktor:ktor-client-cio:${Config.Versions.ktor}")
            implementation("io.ktor:ktor-client-serialization-jvm:${Config.Versions.ktor}")
            implementation("io.ktor:ktor-client-json-jvm:${Config.Versions.ktor}")
            
        }
    }
    // JVM-specific tests and their dependencies:
    jvm().compilations["test"].defaultSourceSet {
        dependencies {
            implementation(kotlin("test-junit"))
        }
    }
    
    js().compilations["main"].defaultSourceSet  {
        dependencies {
            //implementation(kotlin("js"))
            implementation(kotlin("reflect"))
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:${Config.Versions.coroutines}")
    
            implementation(Config.Dependencies.kotlinSerializationRuntime)
    
            // evoleq
            implementation( Config.Dependencies.evoleqCoreJs )
            implementation("org.evoleq:mathcat-result-js:${Config.Versions.mathcat}")
            implementation("org.evoleq:mathcat-core-js:${Config.Versions.mathcat}")
            implementation("org.evoleq:mathcat-structure-js:${Config.Versions.mathcat}")
            implementation("org.evoleq:mathcat-morphism-js:${Config.Versions.mathcat}")
            implementation("org.evoleq:mathcat-state-js:${Config.Versions.mathcat}")
            
            implementation("io.ktor:ktor-websockets:${Config.Versions.ktor}")
            implementation("io.ktor:ktor-client-core:${Config.Versions.ktor}")
            implementation("io.ktor:ktor-client-cio:${Config.Versions.ktor}")
            implementation("io.ktor:ktor-client-serialization-js:${Config.Versions.ktor}")
            implementation("io.ktor:ktor-client-json-js:${Config.Versions.ktor}")
        }
        /* ... */
    }
    js().compilations["test"].defaultSourceSet {/* ... */ }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation(kotlin("reflect"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Config.Versions.coroutines}")
                implementation( Config.Dependencies.kotlinSerializationRuntimeCommon )
    
                //implementation("org.jetbrains.kotlinx:kotlinx-serialization-cbor:${Config.Versions.kotlinSerialization}")
                
                //implementation("io.ktor:ktor-websockets:${Config.Versions.ktor}")
                /*
                implementation("io.ktor:ktor-client-core:${Config.Versions.ktor}")
                implementation("io.ktor:ktor-client-cio:${Config.Versions.ktor}")
                implementation("io.ktor:ktor-client-serialization${Config.Versions.ktor}")
                implementation("io.ktor:ktor-client-json:${Config.Versions.ktor}")
                
                 */
}
}
val commonTest by getting {
dependencies {
    implementation(kotlin("test-common"))
    implementation(kotlin("test-annotations-common"))
}
}
}
}


tasks{
    val licenseFormatJsMain by creating(com.hierynomus.gradle.license.tasks.LicenseFormat::class) {
        source = fileTree("$projectDir/src/jsMain/kotlin") {
        }
        group = "license"
    }
    val licenseFormatJvmMain by creating(com.hierynomus.gradle.license.tasks.LicenseFormat::class) {
        source = fileTree("$projectDir/src/jvmMain/kotlin") {
        }
        group = "license"
    }
    val licenseFormatCommonMain by creating(com.hierynomus.gradle.license.tasks.LicenseFormat::class) {
        source = fileTree("$projectDir/src/commonMain/kotlin") {
        }
        group = "license"
    }
    
    
    licenseFormat {
        finalizedBy(licenseFormatJsMain, licenseFormatCommonMain)
    }
}

apply(from = "../publish.mpp.gradle.kts")