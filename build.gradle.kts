plugins {
    val indraVersion = "3.1.3"
    id("net.kyori.indra") version indraVersion
    id("net.kyori.indra.checkstyle") version indraVersion

    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("xyz.jpenilla.run-paper") version "2.2.0"
    id("xyz.jpenilla.gremlin-gradle") version "0.0.2"
}


indra {
    javaVersions {
        target(17)
    }
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    sonatype.s01Snapshots()
}

fun DependencyHandler.runtimeDownloadApi(group: String, name: String, version: String) {
    api(group, name, version)
    runtimeDownload(group, name, version)
}

dependencies {
    compileOnly("io.papermc.paper", "paper-api", "1.20.2-R0.1-SNAPSHOT")

    runtimeDownloadApi("cloud.commandframework", "cloud-paper", "1.8.4")
    runtimeDownloadApi("com.google.inject", "guice", "7.0.0")

    runtimeDownloadApi("com.zaxxer", "HikariCP", "5.1.0")
    runtimeDownloadApi("org.flywaydb", "flyway-core", "10.0.0")
    runtimeDownloadApi("com.h2database", "h2", "2.2.224")
    runtimeDownloadApi("org.spongepowered", "configurate-hocon", "4.1.2")

    runtimeDownloadApi("com.github.ben-manes.caffeine", "caffeine", "3.1.0")
}

configurations.runtimeDownload {
    exclude("io.papermc.paper")
    exclude("net.kyori", "adventure-api")
    exclude("net.kyori", "adventure-text-minimessage")
    exclude("net.kyori", "adventure-text-serializer-plain")
    exclude("org.slf4j", "slf4j-api")
    exclude("org.ow2.asm")
}

tasks {
    runServer {
        minecraftVersion("1.20.2")
    }

    processResources {
        expand("version" to version)
    }

    shadowJar {
        dependencies {
            include(dependency("xyz.jpenilla:gremlin-runtime:0.0.2"))
        }

        relocate("xyz.jpenilla.gremlin", "broccolai.tags.lib.xyz.jpenilla.gremlin")

        archiveFileName.set(project.name + ".jar")
    }

    build {
        dependsOn(shadowJar)
    }

    writeDependencies {
        repos.set(listOf(
            "https://repo.papermc.io/repository/maven-public/",
            "https://repo.maven.apache.org/maven2/",
        ))
    }
}

