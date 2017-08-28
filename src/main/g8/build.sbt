name := "$name;format="Camel"$"
version := "$version$"

// what version of scala to use
scalaVersion := "$scala_version$"

// scala-js client project
lazy val app = project.in(file("."))
    .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
    .settings(
        libraryDependencies ++= Seq(
            "org.scala-js" %%% "scalajs-dom" % "0.9.1"
        ),

        // nodejs modules
        npmDependencies in Compile ++= Seq(
            // TODO:
        ),

        // root webpack config file
        webpackConfigFile := Some(baseDirectory.value / "webpack.config.js"),

        $if(use_yarn)$
        // use yarn over npm
        useYarn := true,        
        $endif$

        // put all js dependencies into a single output file
        skip in packageJSDependencies := false,

        // call the `main` method after the js is loaded
        scalaJSUseMainModuleInitializer := true,

        // emit source maps in production
        emitSourceMaps in fullOptJS := false
    )
