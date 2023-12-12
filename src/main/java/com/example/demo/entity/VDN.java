package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="tb_bm_mn_vdn")
public class VDN {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String vdn_sq_id;
    private String vdn_no;
    private String center_id;
}
