package ru.hb.aws;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageFromServiceA {
    private String msg;
    private String lng;
    private Coordinates coordinates;
}
