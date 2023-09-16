package com.piseth.java.school.phoneshopenight.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

public class GeneralUtils {
    public static List<Integer> toIntegerList(List<String> list){
       return list.stream()
               .map(s -> s.length())
               .toList();
    }
    public static List<Integer> getEvenNumber(List<Integer> list){
       return list.stream()
                .filter(x -> x%2 == 0)
                .toList();

    }
    //todo ===========================
   // @Test
   public void showPassword(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("dara123");
        System.out.println(encode);
    }
}