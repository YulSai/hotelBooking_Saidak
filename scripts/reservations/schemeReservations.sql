/*
DROP TABLE IF EXISTS reservations;
DROP TABLE IF EXISTS reservation_statuses;
*/

CREATE TABLE IF NOT EXISTS reservation_statuses (
id BIGSERIAL PRIMARY KEY,
name VARCHAR(15) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS reservations (
id BIGSERIAL PRIMARY KEY,
user_id BIGSERIAL NOT NULL REFERENCES users,
room_price DECIMAL(6, 2),
total_cost DECIMAL(6, 2),
status_id BIGINT NOT NULL REFERENCES reservation_statuses,
deleted BOOLEAN NOT NULL DEFAULT FALSE
);