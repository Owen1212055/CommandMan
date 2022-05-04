

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://papermc.io/repo/repository/maven-public/")
        mavenCentral()
    }
}

rootProject.name = "CommandMan"

include("api", "nms", "plugin")
