package com.siddu.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.siddu.todo.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long>{

}
