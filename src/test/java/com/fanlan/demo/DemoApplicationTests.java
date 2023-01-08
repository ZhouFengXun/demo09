package com.fanlan.demo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fanlan.demo.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
        List<User> users = Arrays.asList(
                new User(1, "a", 1, "110"),
                new User(2,"a", 2, "110110"),
                new User(2, "b", 0, "119"),
                new User(3, "c", 1, "120"),
                new User(4, "d", 0, "10086"),
                new User(5, "e", 1, "10010")
        );

//        final String s = User.builder().age(1).toString();
//        System.out.println(s);

        List<User> collect = new ArrayList<>(users);
        System.out.println(collect);

        List<String> collect1 = users.stream().map(User::getName).collect(Collectors.toList());
        System.out.println(collect1);

        List<User> collect2 = users.stream().filter(user -> user.getAge().equals(1)).collect(Collectors.toList());
        System.out.println(collect2);

        List<String> collect3 = users.stream().
                filter(user -> ObjectUtils.nullSafeEquals(user.getAge(), 1)).
                map(User::getName).collect(Collectors.toList());
        System.out.println(collect3);

        Map<String, List<User>> collect4 = users.stream()
                .filter(user -> user.getAge().equals(1))
                .collect(Collectors.groupingBy(User::getName));
        System.out.println(collect4);
        List<User> users1 = collect4.get("a");
        System.out.println(users1);
        for (User user : users1) {
            System.out.println(user);
        }

        Map<Integer, User> collect5 = users.stream()
                .collect(Collectors.toMap(User::getId, user -> user, (o1, o2) -> o1.getId() > o2.getId() ? o1 : o2));
        System.out.println(collect5);

        collect5.forEach((k,v)->{
            System.out.println(v);
        });
    }
}
