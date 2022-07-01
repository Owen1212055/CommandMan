plugins {
    java
    id("com.github.johnrengelman.shadow") version "7.1.0"
    id("xyz.jpenilla.run-paper") version "1.0.6"
}

group = "com.owen1212055"

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    implementation(project(":api"))
    implementation(project(":nms", configuration = "reobf"))

   // implementation("org.bstats:bstats-bukkit:3.0.0")
}


tasks {
    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(17)
    }

    runServer {
        minecraftVersion("1.19")
    }

}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}