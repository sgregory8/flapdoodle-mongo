# Simple Flapdoodle example

Flapdoodle is a tool that manages everything Mongo. It will download, start and kill a local instance of 
Mongo with little configuration. Ultimately this is useful for writing client integration tests where spinning
up and connecting to a real instance of Mongo may be cumbersome!

## Running

Should be runnable directly from IDE otherwise mvn will work...

```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="com.gregory.learning.App"
```