plugins {
    id("java")
    id("maven-publish")
}

group = "com.github.juanegiraldoa"
version = "20250912"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    withSourcesJar()
}

repositories {
    mavenCentral()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = "objects-utils"
            version = project.version.toString()
            from(components["java"])
        }
    }
}

dependencies {  }

tasks.test {  }