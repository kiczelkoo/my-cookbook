plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":domain:groceries"))
    implementation(project(":domain:recipe"))

    implementation(kotlin("stdlib-jdk8"))

    testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")
    testImplementation("org.assertj:assertj-core:3.11.1")
}
