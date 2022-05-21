dependencies {
    implementation(project(":common"))
    implementation("org.postgresql:postgresql:42.3.5")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
}

tasks.bootJar {
    mainClass.set("org.popug.tracker.accounting.Application")
}

allOpen {
    annotations("javax.persistence.Entity", "javax.persistence.MappedSuperclass", "javax.persistence.Embedabble")
}
