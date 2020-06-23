plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":core"))

    implementation(project(":domain:dayplan"))
    implementation(project(":domain:groceries"))
    implementation(project(":domain:recipe"))
    implementation(project(":domain:user"))

    implementation("org.jsoup:jsoup:1.13.1")

    implementation("org.seleniumhq.selenium:selenium-java:3.141.59")
    implementation(kotlin("stdlib-jdk8"))
}
