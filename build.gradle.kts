import org.jetbrains.kotlin.gradle.dsl.JvmTarget

val kotlinVersion = "2.1.0-RC2"
val coroutinesVersion = "1.9.0-RC"
val serializationVersion = "1.7.3"
val jnaVersion = "5.12.1"
val joglVersion = "2.4.0"

plugins {
	id("org.jetbrains.kotlin.jvm") version "2.1.0-RC2"
	id("org.jetbrains.kotlin.plugin.serialization") version "2.1.0-RC2"
	application
}


group = "org.example"
version = "1.0"




kotlin {
	jvmToolchain(17)

	compilerOptions {
		jvmTarget.set(JvmTarget.JVM_17)
		compilerOptions.suppressWarnings = false
		extraWarnings.set(true)

	}

	compilerOptions {
		freeCompilerArgs.add("-Xnon-local-break-continue")
		freeCompilerArgs.add("-Xmulti-dollar-interpolation")
		freeCompilerArgs.add("-Xwhen-guards")
	}

}


repositories {
	mavenCentral()
	google()
	gradlePluginPortal()

	maven { url = uri("https://jogamp.org/deployment/maven") }
}

dependencies { // future ideas?
	implementation("net.java.dev.jna:jna:$jnaVersion")
	implementation("org.jogamp.jogl:jogl-all-main:$joglVersion")
	implementation("com.google.protobuf:protobuf-java-util:4.28.2")   // Deprecated in favour of kotlinx.serialization
	implementation("com.google.protobuf:protobuf-kotlin:4.28.2")      // Deprecated in favour of kotlinx.serialization
	testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-protobuf:$serializationVersion")
	implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
	implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
}

tasks.jar {
	manifest.attributes["Main-Class"] = "MainKt"
}

tasks.test {
	enabled = false
}


val fatJar = tasks.create("FatJar", Jar::class) {
	group = "better build"
	description = "Creates a self-contained fat JAR."
	manifest.attributes["Main-Class"] = "MainKt"
	duplicatesStrategy = DuplicatesStrategy.EXCLUDE

	repositories {
		mavenCentral()
	}

	from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
	with(tasks.jar.get())
}

tasks.startScripts {
	dependsOn(fatJar)
}
application {
	mainClass.set("MainKt")
}

tasks.register<JavaExec>("r") {
	group = "execution"
	description = "Runs the MVM from the fat JAR."

	dependsOn(fatJar)

	classpath = files(fatJar.archiveFile)
	mainClass.set(application.mainClass.get())

}

tasks {
	"build" {
		dependsOn(fatJar)
	}
}