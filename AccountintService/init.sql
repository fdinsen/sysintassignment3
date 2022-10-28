CREATE TABLE orders(
    o_id       INT PRIMARY KEY NOT NULL,
    student_id CHAR(7)         NOT NULL,
    order_date DATE            NOT NULL,
    order_time TIME            NOT NULL
);
CREATE TABLE orderlines(
    ol_id     INT PRIMARY KEY NOT NULL,
    o_id      INT             NOT NULL REFERENCES orders(o_id),
    book_isbn CHAR(13)        NOT NULL,
    price     INT             NOT NULL -- price is in cents 
);