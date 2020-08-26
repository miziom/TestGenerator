package com.mizio.model;

import com.mizio.converter.ColorConverter;
import lombok.*;

import javax.persistence.*;
import java.awt.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "groups_detail")
public class GroupDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_detail_id",
        updatable = false,
        nullable = false)
    private int imageID;

    @Column(name = "group_name",
        nullable = false)
    private String groupName;

    @Column(name = "group_color",
        nullable = false)
    @Convert(converter = ColorConverter.class)
    private Color groupColor;
}