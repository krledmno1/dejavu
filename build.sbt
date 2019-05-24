import sbt.Process.cat

name := "dejavu"

version := "0.1"

scalaVersion := "2.11.1"

lazy val resource = taskKey[Unit]("Copy Monitor.scala to resources")
resource := {
  val source = file(baseDirectory.value+"/src/main/scala/dejavu/Monitor.scala")
  val target = baseDirectory.value+"/src/main/resources/Monitor.txt"
  cat(source) #| "grep -v ^package" #> file(target) !
}

lazy val pack = taskKey[Unit]("Packages DejaVu")
pack := {
    val res = resource.value
    val ass = assembly.value
    val source = file(baseDirectory.value+"/target/scala-2.11/dejavu-assembly-0.1.jar")
    val target = baseDirectory.value+"/out/artifacts/dejavu_jar/dejavu.jar"
    cat(source) #> file(target) !
  }
