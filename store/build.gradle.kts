plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":core"))

    implementation(project(":domain:dayplan"))
    implementation(project(":domain:groceries"))
    implementation(project(":domain:recipe"))
    implementation(project(":domain:user"))

    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.4")
    implementation("org.json:json:20201115")

    testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")
    testImplementation("org.assertj:assertj-core:3.11.1")
}
