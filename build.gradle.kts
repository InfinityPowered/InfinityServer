import org.gradle.api.publish.maven.internal.publisher.MavenLocalPublisher

plugins {
    `java-library`
    `maven-publish`
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
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")
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

java {
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()
            artifact(tasks["sourcesJar"])
            artifact(tasks["javadocJar"])
            pom {
                name.set(project.name)
                description.set(project.description)
            }
        }
    }
}
