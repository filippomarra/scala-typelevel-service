# Scala Typelevel Service

The project consists of a minimal skeleton for a web server. It demonstrates a straightforward approach to building a simple backend using fundamental components, primarily sourced from the **[typelevel stack](https://typelevel.org/)**.

**Libraries:**
- **[cats](https://typelevel.org/cats/)**
- **[http4s](https://http4s.org/)**
- **[circe](https://circe.github.io/circe/)**
- **[doobie](https://tpolecat.github.io/doobie/)**
- **[scala-logging](https://github.com/typesafehub/scala-logging/)**
- **[logback](https://logback.qos.ch/)**
- **[typesafe-config](https://github.com/lightbend/config/)**

**Compiler Plugins:**
- **[kind-projector](https://github.com/typelevel/kind-projector)**
- **[better-monadic-for](https://github.com/oleg-py/better-monadic-for)**

The server is packaged together with a **[PostgreSQL database](https://www.postgresql.org/)** using **[docker-compose](https://docs.docker.com/compose/)**, which allows for easy local deployment. A container was also defined to generate an **[sbt](https://www.scala-sbt.org/)** server that will run on port 8080 and allow the application to run without the need to install Scala on your computer.

Anyway, if you wish to install **[Scala](https://www.scala-lang.org/)**, you can do so by following the instructions provided on **[this link](https://www.scala-lang.org/download/)**.

Server defines three endpoints:
- `GET /users` - gets all users from the database
- `GET /users/{id}` - gets a single user from the database
- `POST /users` - adds a user to the database

First spin up docker and populate the database, then run the server:
All you have to do to run the sbt server and the database, is to run the following command from your terminal:
```
docker-compose up -d
```

**IMPORTANT**: Of course, to run this command you have to install [Docker](https://docs.docker.com/get-docker/) and [docker-compose](https://docs.docker.com.zh.xy2401.com/v17.12/compose/install/) on your computer. Follow the instruction related to your operating system.

Once both the `sbt-server` and the `postgres-db` containers are running, I suggest to put some test data in the "users" table, so you can work better with the APIs.

If you want to configure some DBMS client to navigate more easily on the PostgreSQL database you can download one of those alternatives:
- **[pgAdmin](https://www.pgadmin.org/download/)**
- **[DBeaver](https://dbeaver.io/download/)**
- **[DataGrip](https://www.jetbrains.com/datagrip/download/)**

The credentials to access the database can be found into the [application.conf](<src/main/resources/application.conf>) file.

To try out the server you can import the JSON files under the `/postman` folder in your **[Postman](https://www.postman.com/)** client:
- **[ScalaTypelevelService Collection.postman_collection.json](<postman/ScalaTypelevelService Collection.postman_collection.json>)**
- **[ScalaTypelevelService Environment.postman_environment.json](<postman/ScalaTypelevelService Environment.postman_environment.json>)**

Make sure to correctly import both the collection and the environment file and to set the environment called "**ScalaTypelevelService Environment**" before trying the various requests.

Alternatively, if you don't want to install Postman on your computer, you can test the APIs via **[cURL](https://curl.se/)** requests or directly from your browser.

Here are some examples of cURL requests that you can use (make sure that both the containers are running on Docker):
- `curl 'http://localhost:8080/users'`
- `curl 'http://localhost:8080/users/1'`
- `curl -X POST 'http://localhost:8080/users' --data '{"name": "John", "surname": "Doe", "email": "john.doe@email.com"}'`


