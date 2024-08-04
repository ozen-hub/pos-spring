package com.pos.system.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;

@Entity(name="loyalty_card")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoyaltyCard {
    @Id
    @Column(name="card_id", length = 80)
    private String cardId;
    @Column(name="customer_name", length = 150)
    private String customerName;
    @Column(name="member_type", length = 20)
    private String memberType;// GOLD,SILVER
    @Lob
    @Column(name = "barcode", nullable = false)
    private Blob barcode;
    @Column(name="contact_number", length = 20)
    private String contactNumber;
    @Column(name="email", length = 100)
    private String email;// email verification
    @Column(name="is_verified", columnDefinition = "TINYINT")
    private boolean isVerified;

}
