package com.alibaba.dingyue.k8sweb.repo;

import com.alibaba.dingyue.k8sweb.data.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author somebody
 */
public interface StudentRepo  extends JpaRepository<Student,Long> {
}
