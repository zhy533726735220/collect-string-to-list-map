package com.github.hcsp.collection;


import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Arrays;
import java.util.Comparator;
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
        Map<String,List<User>> map = new HashMap<>();
        Set<String> department =new HashSet<>();

        for(User user: users){
            department.add(user.getDepartment());//先拿到一个部门的set集合类
        }
        for (String value:department){
            List<User> list = new ArrayList<>();
            for(User user: users){//双重循环语句遍历部门和人,判断这个人是否是这个部门的
                if (value.equals(user.getDepartment())){
                    list.add(user);
                    list.sort(new Comparator<User>() {
                        @Override
                        public int compare(User user, User t1) {
                            if(user.getAge()>t1.getAge()){
                                return 1;
                            }else if (user.getAge()==0||t1.getAge()==0||user.getAge()==t1.getAge()){
                                return 0;
                            }else{
                                return -1;
                            }
                        }
                    });
                    map.put(value,list);//是这个本部门就加到一个特定的List<User类>,使用比较大小
                }
            }

        }
        return map;
    }

    public static void main(String  [] args) {
        System.out.println(
                collect(
                        Arrays.asList(
                                new User(1, "张三", 40, "技术部"),
                                new User(2, "李四", 30, "技术部"),
                                new User(3, "王五", 40, "市场部"),
                                new User(4, "王二", 20, "技术部"))));
    }
}
