plugins {
    application
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":lib:api"))
    implementation(project(":lib:v1"))
}

application {
    mainClass.set("ru.otus.danilchenko.cli.CliApp")
}
