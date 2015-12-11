## Whereabouts

This project will identify the whereabouts of stuff.



### Testing
When running tests, first do a pack command in sbt. This generates a distribution that you can run by
executing in the root folder of the project:
```target/pack/bin/where-abouts-server-app
```
Now the gatling test will run against a running application.