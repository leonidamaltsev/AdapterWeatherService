package ru.hb.aws;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageToServiceB {
    private String txt;
    private String createdDt;
    private int currentTemp;
}
