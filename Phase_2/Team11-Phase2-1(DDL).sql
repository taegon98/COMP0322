--- TIER
CREATE TABLE TIER (
    Tier_id     NUMBER      NOT NULL,
    Name        VARCHAR(15) NOT NULL,
    Amount      NUMBER DEFAULT 0 NOT NULL,
    Benefit     NUMBER DEFAULT 0 NOT NULL,
    PRIMARY KEY (Tier_id),
    UNIQUE (Name)
);

--- COUPON
CREATE TABLE COUPON (
    Coupon_id       NUMBER      NOT NULL,
    Name            VARCHAR(30) NOT NULL,
    Percentage      NUMBER      NOT NULL,
    Expiration_date DATE,
    Status          NUMBER      NOT NULL,
    PRIMARY KEY (Coupon_id),
    UNIQUE (Name)
);


--- CUSTOMER
CREATE TABLE CUSTOMER (
    Customer_id     NUMBER      NOT NULL,
    Tier_id         NUMBER      NOT NULL,
    Name            VARCHAR(30) NOT NULL,
    User_id         VARCHAR(30) NOT NULL,
    Password        VARCHAR(30) NOT NULL,
    Phone_number    VARCHAR(30),
    Address         VARCHAR(30),
    Postal_code     NUMBER,
    Amount	    NUMBER,
    Coupon_id       NUMBER,
    PRIMARY KEY (Customer_id),
    UNIQUE (User_id),
    FOREIGN KEY (Tier_id) REFERENCES TIER(Tier_id),
    FOREIGN KEY (Coupon_id) REFERENCES COUPON(Coupon_id)
);


--- ORDERS
CREATE TABLE ORDERS (
    Order_id        NUMBER      NOT NULL,
    Customer_id     NUMBER      NOT NULL,
    Order_date      DATE        NOT NULL,
    Expected_date   DATE        NOT NULL,
    Shipped_date    DATE,
    Status          NUMBER      NOT NULL,
    PRIMARY KEY (Order_id),
    FOREIGN KEY (Customer_id) REFERENCES CUSTOMER(Customer_id)
);


--- PRODUCT
CREATE TABLE PRODUCT(
    Product_id     NUMBER      NOT NULL,
    Name           VARCHAR(30) NOT NULL,
    Description    CLOB,
    Price          NUMBER      NOT NULL,
    Photo          VARCHAR(100)NOT NULL, 
    PRIMARY KEY(Product_id)
);


--- ORDER_DETAIL
CREATE TABLE ORDER_DETAIL (
    Product_id  NUMBER     NOT NULL,
    Order_id    NUMBER     NOT NULL,
    Quantity    NUMBER     NOT NULL,
    Total_price NUMBER,
    FOREIGN KEY (Product_id) REFERENCES PRODUCT (Product_id),
    FOREIGN KEY (Order_id) REFERENCES ORDERS (Order_id)
);


--- GIFTICON
CREATE TABLE GIFTICON (
    Gifticon_id     NUMBER      NOT NULL,
    Customer_id     NUMBER,
    Product_id      NUMBER      NOT NULL,
    Expiration_date DATE        NOT NULL,
    Status          NUMBER      NOT NULL,
    PRIMARY KEY (Gifticon_id),
    FOREIGN KEY (Customer_id) REFERENCES CUSTOMER (Customer_id),
    FOREIGN KEY (Product_id) REFERENCES PRODUCT (Product_id)
);


--- CUSTOMER_SIZE
CREATE TABLE CUSTOMER_SIZE (
    Customer_id            NUMBER      NOT NULL,
    Sleeve_length          NUMBER      DEFAULT 0 NOT NULL,
    Top_Length             NUMBER      DEFAULT 0 NOT NULL,
    Shoulder               NUMBER      DEFAULT 0 NOT NULL,
    Chest_circumference    NUMBER      DEFAULT 0 NOT NULL,
    Waist                  NUMBER      DEFAULT 0 NOT NULL,
    Hip_circumference      NUMBER      DEFAULT 0 NOT NULL,
    Thigh_circumference    NUMBER      DEFAULT 0 NOT NULL,
    Inseam                 NUMBER      DEFAULT 0 NOT NULL,
    Foot_length            NUMBER      DEFAULT 0 NOT NULL,
    Head_circumference     NUMBER      DEFAULT 0 NOT NULL,
    FOREIGN KEY (Customer_id) REFERENCES CUSTOMER (Customer_id)
);
