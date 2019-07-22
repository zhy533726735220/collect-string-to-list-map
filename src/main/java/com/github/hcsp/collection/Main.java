package com.github.hcsp.collection;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;
import java.util.Comparator;
import java.util.Arrays;

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
        users.forEach(user -> {
            String department = user.getDepartment();
            List<User> departmentUsers = new ArrayList<>();
            if (map.get(department) != null) {
                departmentUsers = map.get(department);
            }
            departmentUsers.add(user);
            map.put(department, departmentUsers);
        });
        for (Map.Entry<String, List<User>> user : map.entrySet()) {
            mapValueSort(user);
        }
        return map;
    }

    public static void mapValueSort(Map.Entry<String, List<User>> user) {
        Collections.sort(user.getValue(), new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                if (o1.getAge() - o1.getAge() > 0) {
                    return 1;
                } else if (o1.getAge() - o2.getAge() < 0) {
                    return -1;
                }
                return 0;
            }
        });
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
