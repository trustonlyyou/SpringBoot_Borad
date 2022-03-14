package com.rest.board.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false)
    private String content;

    private LocalDateTime regTime;

    private LocalDateTime updateTime;
}
