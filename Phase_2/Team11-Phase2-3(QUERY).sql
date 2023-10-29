-- SQL QUERY

--1

SELECT
    c.name,
    c.user_id,
    c.phone_number,
    c.address,
    c.postal_code
FROM
    customer c
WHERE
    c.customer_id = 1;

SELECT
    c.name,
    c.user_id,
    c.phone_number,
    c.address,
    c.postal_code
FROM
    customer c
WHERE
    c.customer_id = 190;

--2

SELECT
    c.name,
    c.user_id,
    s.*
FROM
         customer c
    JOIN customer_size s ON c.customer_id = s.customer_id
WHERE
    c.customer_id = 1;

SELECT
    c.name,
    c.user_id,
    s.*
FROM
         customer c
    JOIN customer_size s ON c.customer_id = s.customer_id
WHERE
    c.customer_id = 190;

--3

SELECT
    c.name,
    COUNT(*) AS ordercnt
FROM
    (
             customer c
        JOIN orders       o ON c.customer_id = o.customer_id
    )
    JOIN order_detail d ON o.order_id = d.order_id
GROUP BY
    c.name;

SELECT
    d.quantity,
    COUNT(d.quantity) AS quantity_ratio
FROM
    (
             customer c
        JOIN orders       o ON c.customer_id = o.customer_id
    )
    JOIN order_detail d ON o.order_id = d.order_id
GROUP BY
    d.quantity
ORDER BY
    d.quantity ASC;

--4

SELECT
    c.name,
    c.user_id,
    c.password
FROM
    customer c
WHERE
    c.customer_id IN (
        SELECT DISTINCT
            o.customer_id
        FROM
            orders o
        WHERE
            o.status = 0
    );

SELECT
    c.name,
    c.user_id,
    c.password
FROM
    customer c
WHERE
    c.customer_id IN (
        SELECT
            s.customer_id
        FROM
            customer_size s
        WHERE
            foot_length = 280
    );
    

--5

SELECT
    c.name,
    c.user_id,
    c.password
FROM
    customer c
WHERE
    c.customer_id IN (
        SELECT
            s.customer_id
        FROM
            customer_size s
        WHERE
            foot_length = 280
    )
    AND EXISTS (
        SELECT
            *
        FROM
            orders o
        WHERE
            o.status = 0
    );

SELECT
    *
FROM
    customer
WHERE
    EXISTS (
        SELECT
            *
        FROM
                 customer c
            JOIN orders o ON c.customer_id = o.customer_id
    )
    AND address LIKE 'B%';

--6

SELECT
    c.name,
    c.phone_number
FROM
    customer c
WHERE
    c.address IN ( 'New York', 'Los Angeles', 'Chicago' );

SELECT
    o.order_id,
    o.order_date
FROM
    orders o
WHERE
    o.status IN ( 0, 1 );

--7

WITH tiercustomerorders AS (
    SELECT
        t.name            AS tiername,
        c.customer_id,
        COUNT(o.order_id) AS ordercount,
        ROW_NUMBER()
        OVER(PARTITION BY t.tier_id
             ORDER BY
                 COUNT(o.order_id) DESC
        )                 AS rn
    FROM
             tier t
        JOIN customer c ON t.tier_id = c.tier_id
        LEFT JOIN orders   o ON c.customer_id = o.customer_id
    GROUP BY
        t.name,
        c.customer_id,
        t.tier_id
)
SELECT
    tiername,
    customer_id,
    ordercount
FROM
    tiercustomerorders
WHERE
    rn = 1;

SELECT
    *
FROM
    (
        SELECT
            order_date,
            expected_date
        FROM
            orders o
        WHERE
            o.status IN ( 0, 1 )
    )
ORDER BY
    order_date ASC;

--8

SELECT
    o.order_id,
    c.name AS customername,
    p.name AS productname,
    o.order_date
FROM
         orders o
    JOIN customer     c ON o.customer_id = c.customer_id
    JOIN order_detail od ON o.order_id = od.order_id
    JOIN product      p ON od.product_id = p.product_id
WHERE
    o.status = 1
ORDER BY
    o.order_date;

SELECT
    o.order_id,
    c.name AS customername,
    p.name AS productname,
    o.order_date
FROM
         orders o
    JOIN customer     c ON o.customer_id = c.customer_id
    JOIN order_detail od ON o.order_id = od.order_id
    JOIN product      p ON od.product_id = p.product_id
WHERE
    c.tier_id = 1  -- 여기에 특정 티어 ID를 넣어주세요
ORDER BY
    o.order_date DESC;

--9

SELECT
    c.customer_id,
    SUM(d.quantity) AS total_purchased_count
FROM
         customer c
    INNER JOIN customer_size s ON c.customer_id = s.customer_id
    INNER JOIN orders        o ON c.customer_id = o.customer_id
    INNER JOIN order_detail  d ON o.order_id = d.order_id
GROUP BY
    c.customer_id
ORDER BY
    total_purchased_count DESC;

SELECT
    p.product_id,
    COUNT(*) AS the_number_of_gifticon
FROM
         product p
    INNER JOIN gifticon     g ON p.product_id = g.product_id
    LEFT OUTER JOIN customer     c ON c.customer_id = g.customer_id
    INNER JOIN order_detail d ON p.product_id = d.product_id
GROUP BY
    p.product_id
ORDER BY
    the_number_of_gifticon DESC,
    p.product_id ASC;

-- 10

SELECT
    product_id
FROM
    product
MINUS
SELECT
    product_id
FROM
    gifticon;
    
    

SELECT
    tier_id
FROM
    tier
INTERSECT
SELECT
    coupon_id
FROM
    coupon;