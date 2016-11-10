name := "rscala test"

version := "0.1"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")
scalaVersion := "2.11.8"

libraryDependencies  ++= Seq(
            "org.scalanlp" %% "breeze" % "0.12",
            "org.scalanlp" %% "breeze-natives" % "0.12",
            "org.ddahl" %% "rscala" % "1.0.13"
            //"org.ddahl" %% "rscala" % "1.0.14-SNAPSHOT"
)

resolvers += Resolver.mavenLocal

// finally got rid of this
//unmanagedJars in Compile += file("/usr/local/Cellar/r/3.3.2/Frameworks/R.framework/Resources/site-library/rscala/java/rscala_2.11-1.0.13.jar")
