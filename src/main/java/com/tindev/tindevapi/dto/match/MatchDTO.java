package com.tindev.tindevapi.dto.match;

import lombok.Data;

@Data
public class MatchDTO{

    private Integer matchId;
    private Integer matchedUserFirst;
    private String nameFirst;

    private Integer matchedUserSecond;
    private String nameSecond;

}
