package com.example.votes.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

/**
 * Результат голосования.
 *
 * 3. Результат голосования состоит из слудующего набора данных:
 *    - уникальный индитификатор проголосовавшенго человека (userId)
 *    - выбор, который был сделан в результате голосования: "За" или "Против" (voteValue)
 */

@Entity
@Table(name = "votes")
public class Vote {

    @Id
    private UUID userId;

    @Column(updatable = false)
    private VoteValue value;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public VoteValue getValue() {
        return value;
    }

    public void setValue(VoteValue value) {
        this.value = value;
    }
}

