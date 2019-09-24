package com.alibaba.dingyue.k8sweb.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author somebody
 */
@Data
@NoArgsConstructor
@Entity
public class Student {
    @Id
    private Long id;
    private String name;
    private String description;
}
