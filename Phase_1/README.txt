# ENTITY
### 1. 고객 정보를 저장하기 위한 CUSTOMER entity
 - Customer_id [primary key]
 - Name
 - Address
 - Postal_code
 - Phone_number
 - User_id
 - Password<BR>
→ CUSTOMER를 구분짓기 위한 Customer_id 저장<BR>
→ 고객의 인적정보 및 주소정보를 저장하기 위한 이름(Name), 주소(Address), 우편번호(Postal_code), 전화번호(Phone_number), 아이디(User_id), 비밀번호(Password) attribute


### 2. 주문 정보를 저장하기 위한 ORDERS entity
- Order_id [primary key]
- Order_data
- Expected_data
- Shipped_data
- Status<BR>
→ ORDERS 구분짓기 위한 Order_id 저장<BR>
→ 주문정보(Order_data) attribute <BR>
→ 배송 정보(Shipped_data) attribute <BR>
→ 배송에 관한 예상 배송일(Expected_data), 배송 진행 상태(Status) attribute <BR>


### 3. ORDER와 PRODUCT 사이의 N:M 관계를 해소하기 위해 ORDER_DETAIL [Weak Entity]
- Product_id [primary key]
- Order_id [primary key]
- Quantity<BR>
→ ORDER_DETAIL 구분짓기 위한 Product_id, Order_id 저장<bR>
→ 수량(Quantity) attribute

### 4. 상품에 관한 정보를 저장하기 위해 PRODUCT entity
 - Product_id [primary key]
 - Name
 - Description
 - Price
 - Photo<BR>
→ PRODUCT 구분짓기 위한 Product_id 저장<BR>
→ 상품에 관한 상품이름(Name), 상품설명(Description), 상품가격(Price), 상품사진(Photo) attribute

### 5. 쿠폰에 관한 정보를 저장하기 위해 COUPON entity
  - Coupon_id [primary key]
  - Status
  - Expiration_data
  - Discount_percentage<bR>
 → COUPON 구분짓기 위한 Coupon_id 저장<BR>
 → 쿠폰에 관한 상태(Status), 만료일(Expiration_data), 할인율(Discount_percentage) attribute

### 6. 회원 등급에 관한 정보를 저장하기 위해 TIER entity
  - Tier_id [primary key]
  - Name
  - Purchase_amount
  - Benefit<BR>
 → TIER 구분짓기 위한 Tier_id 저장<BR>
 → 등급이름(Name), 등급 분류를 위한 구매양(Purchase_amount, Benefit) attribute

### 7. 고객의 개인 신체 사이즈를 저장하기 위한 CUSTOMER_SIZE entity
  - Size_id [primary key]
  - Top - Shoulder, Sleeve_length, Length, Chest_circumference
  - Bottom - Inseam, Hip_circumference, Thigh_circumference, Waist
  - Foot_length
  - Head_circumference<BR>
 → CUSTOMER_SIZE 구분짓기 위한 Size_id 저장<BR>
 → 상체(Top), 하체(Bottom)의 attribute는 여러 신체 부분이 있으므로 composite attribute로 저장<BR>
 → 상체(Top) - 어깨, 소매길이, 기장, 가슴둘레(Shoulder, Sleeve_length, Length, Chest_circumference) attribute<BR>
 → 하체(Bottom) - 다리길이, 엉덩이둘레, 허벅지둘레, 허리(Inseam, Hip_circumference, Thigh_circumference, Waist) attribute<BR>
 → 머리둘레(Head_circumference) attribute<BR>
 → 발길이(Foot_length) attribute 

### 8. 구매 및 선물을 위한 GIFTICON entity
 - Gifticon_id [primary key]
 - Name
 - Value
 - Status
 - Expiration_date<BR>
 → GIFTICON을 구분짓기 위한 Gifticon_id 저장<BR>
 → GIFTICON의 상세정보를 담기 위한 Name, Value, Status, Expected_date attribute

---

# Relationship
### 1. CUSTOMER Relationship
- CUSTOMER, ORDERS 간의 ORDERS relationship
 → 1개의 CUSTOMER가 여러 ORDERS를 가질 수 있으므로 1:N 관계
- CUSTOMER, CUSTOMER_SIZE 간의 BELONGS TO relationship
 → 1개의 CUSTOMER는 CUSTOMER_SIZE를 하나만 가질 수 있으므로 1:1 관계
- CUSTOMER, GIFTICON 간의 PUCRCHASE/ PRESENT relationship
 → 1개의 CUSTOMER는 여러 개의 GIFTICON을 구매/선물 할 수 있으므로 1:N 관계
- CUSTOMER, COUPON 간의 IS_ISSUED_TO relationship
→ 1개의 CUSTOMER는 여러개의 COUPON을 가질 수 있으므로 1:N 관계

### 2. ORDERS Relationship
- ORDERS, ORDER_DETAIL 간의 HAS relationship
→ ORDERS와 PRODUCT 사이의 N:M 관계를 해소하기 위해 ORDER_DETAIL을 추가하여 1:N 관계 

### 3. PRODUCT Relationship
- PRODUCT, ORDER_DETAIL 간의 HAS relationship
→ ORDERS와 PRODUCT 사이의 N:M 관계를 해소하기 위해 ORDER_DETAIL을 추가하여 1:N 관계 

### 4. TIER Relationship
- TIER, CUSTOMER 간의 BELONGS_TO relationship
→ 1개의 티어는 여러개의 CUSTOMER에 속할 수 있으므로 1:N 관계
