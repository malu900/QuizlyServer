package com.example.quizly.accessingData;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="players")
public class Player {
    @Id
    private long PlayerId;
}
