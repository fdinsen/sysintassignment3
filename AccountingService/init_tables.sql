CREATE TABLE orders(
    o_id       SERIAL PRIMARY KEY NOT NULL,
    student_id INT                NOT NULL,
    order_date DATE               NOT NULL,
    order_time TIME               NOT NULL
);
CREATE TABLE orderlines(
    ol_id     SERIAL PRIMARY KEY NOT NULL,
    o_id      INT                NOT NULL REFERENCES orders(o_id),
    book_id   INT                NOT NULL,
    price     INT                NOT NULL -- price is in cents 
);