package edu.ln.tour.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tab_admin")
public class SupUser {

        @Id
        Integer aid;
        @Column
        String apassword;
    }

