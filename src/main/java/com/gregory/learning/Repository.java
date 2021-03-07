package com.gregory.learning;

import static com.mongodb.client.model.Filters.eq;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

public class Repository {

  private MongoCollection<Person> collection;

  public Repository(String connectionString, String databaseName, String collectionName) {
    CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
        MongoClientSettings.getDefaultCodecRegistry(),
        CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
    MongoClientSettings settings = MongoClientSettings.builder()
        .applyConnectionString(new ConnectionString(connectionString))
        .codecRegistry(codecRegistry)
        .build();
    MongoClient mongoClient = MongoClients.create(settings);
    this.collection = mongoClient.getDatabase(databaseName)
        .getCollection(collectionName, Person.class);
  }

  public void insert(Person person) {
    collection.insertOne(person);
  }

  public Person find(String id) {
    return collection.find(eq("_id", id)).first();
  }
}

