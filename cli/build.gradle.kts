plugins {
    application
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))
}

application {
    mainClass.set("ru.otus.danilchenko.cli.CliApp")
}
