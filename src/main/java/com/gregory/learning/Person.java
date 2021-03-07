package com.gregory.learning;

import java.util.Objects;

public class Person {

  String id;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  private String name;
  private int age;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Person person = (Person) o;
    return age == person.age &&
        id.equals(person.id) &&
        name.equals(person.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, age);
  }
}
