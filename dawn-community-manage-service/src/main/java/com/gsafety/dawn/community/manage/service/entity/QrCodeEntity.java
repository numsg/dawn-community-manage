package com.gsafety.dawn.community.manage.service.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "b_qr_code")
public class QrCodeEntity {
    @Id
    @Column(name = "qr_code_id")
    private String qrCodeId;
    @Column(name = "business_id")
    private String businessId;
    @Column(name = "content")
    private String content;
    @Column(name = "image")
    private String image;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ctime")
    private Date ctime;

    public String getQrCodeId() {
        return qrCodeId;
    }

    public void setQrCodeId(String qrCodeId) {
        this.qrCodeId = qrCodeId;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}
