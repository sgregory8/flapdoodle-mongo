package com.gregory.learning;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import java.io.IOException;

public class App {

  private static final MongodStarter starter = MongodStarter.getDefaultInstance();
  private static MongodExecutable mongodExecutable;
  private static int port;
  private static final String COLLECTION_NAME = "test";
  private static final String DATABASE_NAME = COLLECTION_NAME;

  static {
    try {
      port = Network.getFreeServerPort();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void setUp() throws IOException {
    MongodConfig mongodConfig = MongodConfig.builder()
        .version(Version.Main.PRODUCTION)
        .net(new Net(port, Network.localhostIsIPv6()))
        .build();
    mongodExecutable = starter.prepare(mongodConfig);
    mongodExecutable.start();
  }

  private static void tearDown() {
    if (mongodExecutable != null) {
      mongodExecutable.stop();
    }
  }

  public static void main(String[] args) throws IOException {
    try {
      setUp();
      Repository repository = new Repository("mongodb://localhost:" + port, COLLECTION_NAME,
          DATABASE_NAME);
      Person person = new Person();
      person.setId("1");
      person.setAge(29);
      person.setName("Sam");
      repository.insert(person);
      Person returnedPerson = repository.find("1");
      System.out.println("Returned person = original person = " + person.equals(returnedPerson));
    } finally {
      tearDown();
    }
  }
}
