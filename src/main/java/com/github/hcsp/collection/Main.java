package com.github.hcsp.collection;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    // 请编写一个方法，对传入的List<User>进行如下处理：
    // 返回一个从部门名到这个部门的所有用户的映射。同一个部门的用户按照年龄进行从小到大排序。
    // 例如，传入的users是[{name=张三, department=技术部, age=40 }, {name=李四, department=技术部, age=30 },
    // {name=王五, department=市场部, age=40 }]
    // 返回如下映射：
    //    技术部 -> [{name=李四, department=技术部, age=30 }, {name=张三, department=技术部, age=40 }]
    //    市场部 -> [{name=王五, department=市场部, age=40 }]
    public static Map<String, List<User>> collect(List<User> users) {
        Map<String, List<User>> departmentMappingUser = new HashMap<>();
        Set<String> departmentKeys = departmentMappingUser.keySet();
        Comparator<User> c = Comparator.comparingInt(User::getAge);

        // 按 department 存储 User
        for (User user : users) {
            if (!departmentKeys.contains(user.getDepartment())) {
                departmentMappingUser.put(user.getDepartment(), new ArrayList<>());
            }
            departmentMappingUser.get(user.getDepartment()).add(user);
        }

        // 同一个部门的用户按照年龄升序排序
        for (
                String key : departmentKeys) {
            departmentMappingUser.get(key).sort(c);
        }

        return departmentMappingUser;
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
