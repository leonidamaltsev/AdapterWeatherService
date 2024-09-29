package ru.hb.aws;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageToServiceB {
    private String txt;
    private String createdDt;
    private int currentTemp;

    public MessageToServiceB(String txt, String createdDt, int currentTemp) {
        this.txt = txt;
        this.createdDt = createdDt;
        this.currentTemp = currentTemp;
    }
}
