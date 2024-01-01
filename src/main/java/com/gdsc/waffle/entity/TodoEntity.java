package com.gdsc.waffle.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString
@Builder
@Table(name = "todo")
public class TodoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column @NotNull
    private String contents; // 내용
    @Column
    @ColumnDefault("true")
    private Boolean complete_chk; // 완료 여부
    @Column
    private LocalDate startTime; // 시작 시간. 년, 월, 일

    @ManyToOne // 다대일
    @JoinColumn(name = "category_id")
    private CategoryEntity category; // 연관 관계 매핑
}
