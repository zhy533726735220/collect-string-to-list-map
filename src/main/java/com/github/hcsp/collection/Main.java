package com.github.hcsp.collection;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;
import java.util.Arrays;
import java.util.ArrayList;

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

        /**
         * containsKey()方法用于检查特定键是否被映射到HashMap。它将key元素作为参数，如果该元素在map中映射，则返回True。
         */
        for (User user: users) {
            if (!map.containsKey(user.getDepartment())) {
                List<User> list = new ArrayList<>();
                list.add(user);
                map.put(user.getDepartment(), list);   //put,建立一个，然后放置进去
            }else {
                map.get(user.getDepartment()).add(user);   //获取部门，然后添加到相应的key中去
            }
        }

        for (String key: map.keySet()) {    //根据map中的key，来sort
            Collections.sort(map.get(key));
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
