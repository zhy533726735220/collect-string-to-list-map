package com.github.hcsp.collection;


import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

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
            if (map.containsKey(department)) { // 已有当前部门
                map.get(department).add(user);
                Collections.sort(map.get(department), new MyIntComparator());
//                Comparator<? super E> comparator = ;
//                map.get(department).sort(comparator);
            } else {
                ArrayList<User> tempArr = new ArrayList<>();
                tempArr.add(user);
                map.put(department, tempArr);
            }
        }
        return map;
    }

    public static void main(String[] args) {
        System.out.println(
                collect(
                        Arrays.asList(
                                new User(1, "张三", 40, "技术部"),
                                new User(2, "李四", 300, "技术部"),
                                new User(3, "王五", 40, "市场部"))));
    }

    /**
     * 整数比较器，将整数按降序排列
     */
    static class MyIntComparator implements Comparator {

        /**
         * o1比o2大，返回-1；o1比o2小，返回1。
         */
        public int compare(Object o1, Object o2)  {
            int i1 = 0;
            int i2 = 0;
            try {
                i1 = getValueByReflect(o1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                i2 = getValueByReflect(o2);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (i1 < i2){
                return -1;
            }
            if (i1 > i2){
                return 1;
            }
            return 0;
        }

        public static int getValueByReflect(Object model) throws Exception {
            // 返回值
            int value = 0;
            String paraName = "age";

            // 获取属性值
            Field[] fields = model.getClass().getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);

                if (field.getName().equals(paraName)) {
                    value = (Integer) field.get(model);
                    break;
                }
            }
            return value;
        }



    }

}
