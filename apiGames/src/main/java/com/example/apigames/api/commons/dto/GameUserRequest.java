package com.example.apigames.api.commons.dto;

import jdk.jfr.StackTrace;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameUserRequest {
    @NonNull
    private Long idGame;

}
