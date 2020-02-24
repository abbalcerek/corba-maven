# Corba hello world client/server application


### Building - generate stubs and build java client and server

```bash
mvn clean package
```

### Start - orbd server on given port

```bash
ORB_PORT=11050
orbd -ORBInitialPort $ORB_PORT
```

### Run server

```bash
java -jar server/target/server.jar \
 -ORBInitialPort $ORB_PORT &
```

### Run client
```bash
java -jar client/target/client.jar \
 -ORBInitialPort $ORB_PORT
```