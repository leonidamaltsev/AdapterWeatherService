package ru.hb.aws;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageFromServiceA {
    private String msg;
    private String lng;
    private Coordinates coordinates;

}
