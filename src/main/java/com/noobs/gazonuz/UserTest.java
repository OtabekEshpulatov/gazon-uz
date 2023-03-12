package com.noobs.gazonuz;

import com.noobs.gazonuz.domains.auth.Permission;
import com.noobs.gazonuz.repositories.BaseDAO;

public class UserTest {


    public static void main(String[] args) {


        BaseDAO<Permission, String> baseDAO = new BaseDAO<>();

        final Permission build = Permission.builder().name("e").code("e").build();

        final Permission save = baseDAO.save(build);

        System.out.println(save);

    }
}
