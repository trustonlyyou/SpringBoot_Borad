package com.rest.board.entity;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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
    @NotEmpty
    private String username;

    @Column(nullable = false, length = 50)
    @NotEmpty
    private String title;

    @Column(nullable = false)
    @NotEmpty
    private String content;

    private LocalDateTime regTime;

    private LocalDateTime updateTime;
}
