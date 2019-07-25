package com.github.hcsp.collection;


import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Arrays;
/**
 * @author  Jinlf
 */
public class Main {
    // 请编写一个方法，对传入的List<User>进行如下处理：
    // 返回一个从部门名到这个部门的所有用户的映射。同一个部门的用户按照年龄进行从小到大排序。
    // 例如，传入的users是[{name=张三, department=技术部, age=40 }, {name=李四, department=技术部, age=30 },
    // {name=王五, department=市场部, age=40 }]
    // 返回如下映射：
    //    技术部 -> [{name=李四, department=技术部, age=30 }, {name=张三, department=技术部, age=40 }]
    //    市场部 -> [{name=王五, department=市场部, age=40 }]

    public static Map<String, List<User>> collect(List<User> users) {
        //创建一个空的Map
        Map orderedMap = new HashMap<String, List<User>>(5);

        //提取所有部门到一个新的depSet
        Set<String> depSet = new HashSet<>();
        users.forEach(user -> depSet.add(user.getDepartment()));

        depSet.forEach(dep -> {
            List<User> userList = new LinkedList<>();
            users.forEach(user -> {
                if (user.getDepartment() == dep) {
                    userList.add(user);
                }
            });
            Collections.sort(userList);
            orderedMap.put(dep, userList);
        });

        return orderedMap;

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
