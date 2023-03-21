package com.noobs.gazonuz;

import com.google.gson.Gson;
import com.noobs.gazonuz.UserTest;
import com.noobs.gazonuz.domains.Pitch;
import lombok.RequiredArgsConstructor;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@RequiredArgsConstructor
public class Main {
    public static void main(String[] args) {


        Gson gson = new Gson();

        final myclass src = new myclass(10 , "jack" , "myjack");

        final String s = gson.toJson(src);


        System.out.println(s);
    }

    static class myclass {

        int age;
        String name;
        String username;

        public myclass(int age , String name , String username) {
            this.age = age;
            this.name = name;
            this.username = username;
        }
    }

}
