CREATE SCHEMA IF NOT EXISTS "house-reservation-system";

CREATE TABLE IF NOT EXISTS "house-reservation-system".house (
                                                                id BIGSERIAL PRIMARY KEY,
                                                                name VARCHAR(255) NOT NULL,
                                                                description TEXT,
                                                                type VARCHAR(255) NOT NULL,
                                                                capacity INTEGER,
                                                                price DECIMAL(10, 2) NOT NULL,
                                                                location_type VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS "house-reservation-system".house_amenities (
                                                                          house_id BIGINT NOT NULL, -- Changed from INTEGER to BIGINT
                                                                          amenity VARCHAR(255) NOT NULL,
                                                                          FOREIGN KEY (house_id) REFERENCES "house-reservation-system".house(id)
);
