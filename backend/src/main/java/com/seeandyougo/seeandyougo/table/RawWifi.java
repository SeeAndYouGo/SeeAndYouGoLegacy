package com.seeandyougo.seeandyougo.table;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class RawWifi {

    @Id @GeneratedValue
    @Column(name = "raw_wifi_id")
    private Long id;

    @Column(columnDefinition="LONGTEXT")
    private String raw;

}
