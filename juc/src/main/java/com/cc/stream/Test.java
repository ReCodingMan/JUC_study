package com.cc.stream;

import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        User u1 = new User(1, "a", 21);
        User u2 = new User(2, "b", 22);
        User u3 = new User(3, "c", 23);
        User u4 = new User(4, "d", 24);
        User u5 = new User(5, "e", 25);

        List<User> list = Arrays.asList(u1,u2,u3,u4,u5);

        list.stream()
                .filter(u->{return u.getId()%2==0;})
                .filter(u->{return u.getAge()>23;})
                .map(u->{return u.getName().toUpperCase();})
                .sorted((uu1,uu2)->{return uu1.compareTo(uu2);})
                .limit(1)
                .forEach(System.out::println);
    }
}
