package com.github.hcsp.collection;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    public void test() {
        Map<String, List<User>> map =
                Main.collect(
                        Arrays.asList(
                                new User(1, "张三", 40, "技术部"),
                                new User(2, "李四", 30, "技术部"),
                                new User(3, "王五", 40, "市场部"),
                                new User(4, "王五", 50, "市场部")));

        Assertions.assertEquals(
                Arrays.asList(2, 1),
                map.get("技术部").stream().map(User::getId).collect(Collectors.toList()));
        Assertions.assertEquals(
                Arrays.asList(3, 4),
                map.get("市场部").stream().map(User::getId).collect(Collectors.toList()));
    }
}
