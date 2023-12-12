package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="tb_bm_mn_vdn")
public class VDN {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vdn_sq_id")
    private String vdnSqId;
    @Column(name = "vdn_no")
    private String vdnNo;
    @Column(name = "center_id")
    private String centerId;
    @Column(name = "server_id")
    private String serverId;
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
    @Column(name = "regi_dttm")
    private String regiDttm;
    @Column(name = "updt_user_id")
    private String updtUserId;
    @Column(name = "updt_dttm")
    private String updtDttm;
    @Column(name = "modify_flag")
    private String modifyFlag;
}
