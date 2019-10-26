package com.github.hcsp.collection;

import java.util.Objects;

public class User {
    // 用户的id
    private final Integer id;
    // 用户的姓名
    private final String name;
    // 用户的年龄
    private final int age;
    // 用户的部门，例如"技术部"/"市场部"
    private final String department;

    public User(Integer id, String name, int age, String department) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.department = department;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User person = (User) o;
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
