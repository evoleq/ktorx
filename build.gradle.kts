

plugins {
    //java
    kotlin("multiplatform") version "1.3.70"
    id ("com.github.hierynomus.license") version "0.15.0"
    `maven-publish`
    maven
    id ("com.jfrog.bintray") version "1.8.0"
    id("org.jetbrains.dokka") version "0.9.17"
    kotlin("plugin.serialization") version "1.3.70"
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
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.5")
    
            // evoleq
            implementation( Config.Dependencies.evoleqCore )
            implementation("org.evoleq:mathcat-result-jvm:1.0.0")
            implementation("org.evoleq:mathcat-core-jvm:1.0.0")
            implementation("org.evoleq:mathcat-structure-jvm:1.0.0")
            
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
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:1.3.5")
    
            implementation(Config.Dependencies.kotlinSerializationRuntime)
    
            // evoleq
            implementation( Config.Dependencies.evoleqCoreJs )
            implementation("org.evoleq:mathcat-result-js:1.0.0")
            implementation("org.evoleq:mathcat-core-js:1.0.0")
            implementation("org.evoleq:mathcat-structure-js:1.0.0")
            
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
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:1.3.5")
                implementation( Config.Dependencies.kotlinSerializationRuntimeCommon )
    
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
