name := "StateMachine4Scala"

version := "1.0"

scalaVersion := "2.10.4"

publishTo  <<= baseDirectory  { (base) => Some(Resolver.file("file",  base / "publish/releases" )) }

libraryDependencies ++= Seq(
	"org.specs2" %%	"specs2" %"2.1.1" %"test"
)



