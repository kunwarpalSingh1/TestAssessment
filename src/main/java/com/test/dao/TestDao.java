package com.test.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.test.entity.Test;

@Repository
public interface TestDao extends JpaRepository<Test, Long>, JpaSpecificationExecutor<Test> {

}
