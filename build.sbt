name := "rscala test"

version := "0.1"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")
scalaVersion := "2.11.8"

libraryDependencies  ++= Seq(
            "org.scalanlp" %% "breeze" % "0.12",
            "org.scalanlp" %% "breeze-natives" % "0.12",
            "org.ddahl" %% "rscala" % "1.0.14-SNAPSHOT"
)

resolvers += Resolver.mavenLocal

//unmanagedJars in Compile += file("/home/ndjw1/R/x86_64-pc-linux-gnu-library/3.2/rscala/java/rscala_2.11-1.0.6.jar")
