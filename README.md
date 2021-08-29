# The Java Hornet Project

There is currently only one node software for the IOTA main net (aka [chrysalis](https://chrysalis.iota.org/)). This
node is called [hornet](https://github.com/gohornet/hornet) and is written in Go Lang and has the focus to be operated
by everyone, even on devices with low requirements.

This project attempts to take a slightly different approach. In many large companies, very old software is still used
due to the often old IT systems. Therefore, it is not unusual that most systems are based on e.g. JAVA or C++. New
programming languages like Go Lang or Rust are just starting to enter the companies.

This project was created to help companies or people with JAVA knowledge to enter the IOTA easily. Also, the focus
during the hole development is always on companies. Thus, the code can be simplified in some places, since various
plugins or dashboards can be skipped. Additionally, the database is detached from the node and moved to a separate
instance. In this case, the local RocksDB implementation of the hornet node is moved to an outsourced RedisDB.
Separating backend services and databases makes sense from a security and backup perspective in enterprises.

Hornet's focus on resource-efficient operation is also secondary in this project, as server instances in large
enterprises tend to have fewer problems with higher CPU or RAM consumption. Since we don't want to waste the resources,
we build on the Quarkus, the Supersonic Subatomic Java Framework, which is heavily optimized for performance and memory
usage.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/. <br />
If you want to learn more about IOTA, start [here](https://iota-beginners-guide.com/).

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw clean install -DskipTests=true
./mvnw package -DskipTests=true
java -jar target/quarkus-app/quarkus-run.jar
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory. Be aware that it’s not an _über-jar_ as
the dependencies are copied into the `target/quarkus-app/lib/` directory.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.type=uber-jar -DskipTests=true
```

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/javahornet-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.html
.

## Provided Code

### RESTEasy JAX-RS

Easily start your RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started#the-jax-rs-resources)

## Donations

iota1qpq3f0r8xa4jeu88llvy0teykauws840lf4pyev0uyql4kjk2rxu5vrkdqm