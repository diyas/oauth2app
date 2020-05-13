package com.auth.app.user.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "mobile_app_users")
public class UserMobile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "merchant_id")
    private int merchantId;
    @Column(name = "passwd_hash")
    private String password;
    @Column
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column
    private String address;
    @Column(name = "print_receipt_merchant_name")
    private String printReceiptMerchantName;
    @Column(name = "print_receipt_address_line_1")
    private String printReceiptAddressLine1;
    @Column(name = "print_receipt_address_line_2")
    private String printReceiptAddressLine2;
    @Column(name = "stan")
    private int stan;
    @Column(name = "invoice_num")
    private int invoiceNum;
    @Column(name = "last_user_data_update_timestamp")
    private String lastUserDataUpdateTimestamp;
    @Column(name = "lock_flag")
    private int lockFlag;
//    @Column(name = "header_ksn")
//    private String headerKsn;
//    @Column(name = "enc_header_ipek")
//    private String encHeaderIpek;
}
