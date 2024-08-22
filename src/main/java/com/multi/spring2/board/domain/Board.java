package com.multi.spring2.board.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter
@ToString
//@Data
public class Board {
    private Integer boardNo;
    private String boardTitle;
    private String boardContent;
    private String boardWriter;
}
