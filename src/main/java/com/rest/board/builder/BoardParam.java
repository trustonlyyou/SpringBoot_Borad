package com.rest.board.builder;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class BoardParam {
    private String username;
    private String title;
    private String content;
}
