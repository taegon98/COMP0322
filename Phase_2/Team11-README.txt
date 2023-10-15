# ENTITY

### 1. COUPON entity
  - Discount_percentage → percentage [컬럼명 변경]
  - Coupon의 이름을 나타내는 NAME COLUMN 추가 [컬럼 추가]

	
### 2. TIER entity
  - Purchase_amount → amount [컬럼명 변경]


### 3. CUSTOMER_SIZE entity
  - Top(Composite attribute)의 length → Top_legnth [컬럼명 변경]


### 4. GIFTICON entity
 - Name  [컬럼 삭제]
 - Value [컬럼 삭제]


---

# Relationship

### 1. GIFTICON ↔ PRODUCT Relationship [관계 추가]
- 하나의 PRODUCT을 구매할 수 있는 여러개의 GIFTICON이 존재
 → PRODUCT : GIFTICON = 1 : N 매핑
