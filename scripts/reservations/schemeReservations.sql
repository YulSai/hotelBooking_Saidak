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
room_id BIGSERIAL NOT NULL REFERENCES rooms,
reservation_date TIMESTAMPTZ NOT NULL,
check_in date NOT NULL,
check_out date NOT NULL,
room_price DECIMAL(6, 2),
total_cost DECIMAL(6, 2),
status_id BIGINT NOT NULL REFERENCES statuses,
deleted BOOLEAN NOT NULL DEFAULT FALSE
);