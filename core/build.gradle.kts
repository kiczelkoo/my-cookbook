plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":domain:diet"))
    implementation(project(":domain:groceries"))
    implementation(project(":domain:recipe"))
    implementation(project(":domain:user"))
    implementation(kotlin("stdlib-jdk8"))

    testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")
    testImplementation("org.assertj:assertj-core:3.11.1")
    testImplementation("io.mockk:mockk:1.9.3")
}
