/*
 * This file was generated by the Gradle 'init' task.
 */

plugins {
    `java-library`
    `maven-publish`
    id("io.papermc.hangar-publish-plugin") version "0.1.2"
    id("com.modrinth.minotaur") version "2.+"
}

modrinth {
    token.set(System.getenv("MODRINTH_TOKEN"))
    projectId.set("DisablePortals")
    versionType.set("Release") // `Release` `Beta`
    uploadFile.set(tasks.jar)
    loaders.add("paper")
    gameVersions.addAll("1.20", "1.20.1", "1.20.2", "1.20.3", "1.20.4", "1.20.5", "1.20.6")
}

hangarPublish {
    publications.register("plugin") {
        version.set("1.0.0")
        channel.set("Release") // `Release` `Beta`
        id.set("DisablePortals")
        apiKey.set(System.getenv("HANGAR_API_TOKEN"))
        platforms {
            paper() {
                jar.set(tasks.jar.flatMap { it.archiveFile })
                val versions: List<String> = (property("paperVersion") as String)
                    .split(",")
                    .map { it.trim() }
                platformVersions.set(versions)
            }
        }
    }
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }

    maven {
        url = uri("https://oss.sonatype.org/content/groups/public/")
    }

    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

dependencies {
    compileOnly(libs.io.papermc.paper.paper.api)
}

group = "dev.andrewdragon"
version = "1.0.0"
description = "DisablePortals"
java.sourceCompatibility = JavaVersion.VERSION_17

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc>() {
    options.encoding = "UTF-8"
}
