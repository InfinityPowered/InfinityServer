import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import xyz.jpenilla.runpaper.task.RunServer

plugins {
    `java-library`
    id("com.github.johnrengelman.shadow") version "8.1.1"

    // Extra plugins for testing the implementation
    id("xyz.jpenilla.run-paper") version "2.1.0"
}

group = "io.github.derechtepilz"
version = "0.0.1-SNAPSHOT"
description = "An API made to create story games for Minecraft servers!"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
    maven {
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
    maven {
        url = uri("https://oss.sonatype.org/content/groups/public/")
    }
    mavenLocal()
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")
    compileOnly("io.github.derechtepilz:infinity-api:${project.version}")
    implementation("dev.jorel:commandapi-bukkit-shade:9.3.0")
}

tasks.withType<ProcessResources> {
    val properties = mapOf(
        "version" to project.version
    )

    inputs.properties(properties)

    filteringCharset = "UTF-8"

    filesMatching("plugin.yml") {
        expand(properties)
    }
}

tasks.withType<ShadowJar> {
    dependencies {
        include(dependency("dev.jorel:commandapi-bukkit-shade:9.3.0"))
    }
    relocate("dev.jorel.commandapi", "io.github.derechtepilz.commandapi")
    minimize()
    archiveClassifier = ""
}

tasks.withType<RunServer> {
    dependsOn(tasks["shadowJar"])
    minecraftVersion("1.20.4")
}
