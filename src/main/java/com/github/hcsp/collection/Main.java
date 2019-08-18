package com.github.hcsp.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    // 请编写一个方法，对传入的List<User>进行如下处理：
    // 返回一个从部门名到这个部门的所有用户的映射。同一个部门的用户按照年龄进行从小到大排序。
    // 例如，传入的users是[{name=张三, department=技术部, age=40 }, {name=李四, department=技术部, age=30 },
    // {name=王五, department=市场部, age=40 }]
    // 返回如下映射：
    //    技术部 -> [{name=李四, department=技术部, age=30 }, {name=张三, department=技术部, age=40 }]
    //    市场部 -> [{name=王五, department=市场部, age=40 }]
    public static Map<String, List<User>> collect(List<User> users) {
        Map<String, List<User>> map = new HashMap<>();
        for (User user : users) {
            String department = user.getDepartment();
            // 省得部门作为 key 时，重复添加
            if (!map.containsKey(department)) {
                // 根据非当前部门的元素
                ArrayList<User> newUsers = new ArrayList<>();
                for (User newUser : users) {
                    if (department.equals(newUser.getDepartment())) {
                        newUsers.add(newUser);
                    }
                }
                // 使用工具集也可以，但这样要侵入 User 类中实现 Comparable 接口
                // Collections.sort(newUsers);
                newUsers.sort((o1, o2) -> {
                    if (o1.getAge() < o2.getAge()) {
                        return -1;
                    } else if (o1.getAge() > o2.getAge()) {
                        return 1;
                    }
                    return 0;
                });
                map.put(department, newUsers);
            }
        }
        return map;
    }

    public static void main(String[] args) {
        System.out.println(
                collect(
                        Arrays.asList(
                                new User(1, "张三", 40, "技术部"),
                                new User(2, "李四", 30, "技术部"),
                                new User(3, "王五", 40, "市场部"))));
    }
}
