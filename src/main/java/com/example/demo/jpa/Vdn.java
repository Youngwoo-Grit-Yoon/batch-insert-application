package com.example.demo.jpa;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
@Table(name="tb_bm_mn_vdn")
public class Vdn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vdn_sq_id")
    private String vdnSqId;
    @Column(name = "vdn_no")
    private final String vdnNo;
    @Column(name = "center_id")
    private final String centerId;
    @Column(name = "server_id")
    private final String serverId;
    @Column(name = "monitoring_yn")
    private String monitoringYn;
    @Column(name = "vdn_type")
    private String vdnType;
    @Column(name = "split")
    private String split;
    @Column(name = "check_link")
    private String checkLink;
    @Column(name = "comment")
    private String comment;
    @Column(name = "result")
    private String result;
    @Column(name = "regi_user_id")
    private String regiUserId;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "regi_dttm")
    private Date regiDttm;
    @Column(name = "updt_user_id")
    private String updtUserId;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updt_dttm")
    private Date updtDttm;
    @Column(name = "modify_flag")
    private final String modifyFlag = "1";
}
