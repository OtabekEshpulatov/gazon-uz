package com.noobs.gazonuz;

import com.noobs.gazonuz.UserTest;
import com.noobs.gazonuz.domains.Pitch;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Main {

    private final UserTest userTest;


    public static void main(String[] args) {


        final Pitch pitch = Pitch.builder().build();

        pitch.getFullAddress();pitch.getName();

        pitch.getStatus();
    }
}
