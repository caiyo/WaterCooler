name := "WaterCooler"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaJpa,
  cache,
  "org.hibernate" % "hibernate-core" % "4.2.3.Final",
  "org.hibernate" % "hibernate-entitymanager" % "4.2.3.Final",
  "postgresql" % "postgresql" % "9.1-901-1.jdbc4",
  "org.apache.commons" % "commons-email" % "1.3.3"    
)     

val appDependencies = Seq(
  javaJdbc,
  javaJpa
)

play.Project.playJavaSettings
