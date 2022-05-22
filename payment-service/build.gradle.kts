dependencies {
    implementation(project(":common"))
    implementation(project(":messaging-common"))
}

tasks.bootJar {
    mainClass.set("org.popug.tracker.payment.Application")
}
