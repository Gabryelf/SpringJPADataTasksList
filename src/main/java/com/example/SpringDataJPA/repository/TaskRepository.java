package com.example.SpringDataJPA.repository;

import com.example.SpringDataJPA.model.Task;
import com.example.SpringDataJPA.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(TaskStatus status);
}


