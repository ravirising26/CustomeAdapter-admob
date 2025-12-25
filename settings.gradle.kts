pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri ("https://jitpack.io")
//            url = uri("https://maven.pkg.github.com/ravirising26/TapMindSdk")
            credentials {
                username = providers.gradleProperty("GITHUB_TOKEN").get()
                password = ""
            }
        }

    }
}

rootProject.name = "CustomAdapter-admob"
include(":app")
 