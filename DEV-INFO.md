## To get everything up and running

1. Install the latest IntelliJ IDEA Ultimate Edition. 
2. Configure JDK and Plugin SDK.
    * Since we are using gradle you can choose JDK 1.8
    * Since we are using JavaScriptLanguage support :
        * Create a new library with - [IDEA Installation]/plugins/JavaScriptDebugger/lib/\*.jar
        * Add this library to your project
        
        
Since we use Javascript code and Java code  make sure you CD into `ngConsoleCli` and run npm 
install

```
    cd src/ngConsoleCli
    npm i
```

after you install npm packages please run ` ./node_modules/.bin/tsc -p tsconfig.json` so it gets compiled
into the `projectDir/gen` (I dont have it automated yet)

After you do it then you can run plugin build that supposed to copy generated JS files into the plugin
directory using this gradle task:

```
project.afterEvaluate {
    buildPlugin.doLast {

        def libraries = "$it.destinationDir/$intellij.pluginName/ngConsoleCli/"
        print("Copy librays: $libraries" )
        copy {
            from "$project.projectDir/gen/ngConsoleCli"
            into libraries

        }
    }

    prepareSandbox.doLast {

        def libraries = "$it.destinationDir/$intellij.pluginName/ngConsoleCli/"
        print("Copy librays: $libraries" )
        copy {
            from "$project.projectDir/gen/ngConsoleCli"
            into libraries

        }
    }
}
```



    
