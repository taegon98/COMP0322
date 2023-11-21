- init.sql
phase 2에서 Customer 에 Amount , Order_Detail 안의 total_price 값이 order 에 따라 달라지지 않은점 수정했습니다.

- phase 2에서 fift_query에서 작성한 쿼리를 서비스에 맞게 바꾸어서 jdbc를 이용한 Java 코드를 작성했습니다.

- 아래 기능 설명에 query의 구체적인 내용과 query를 기술했습니다.

- init.sql 부분은 init.class 를 만들어 fiftService 가 실행되기전 DB 내 정보들을 수정할수 있게 했습니다.

- DB 커넥션을 위한 JDBC의 USER_ID fift로 설정하였고 USER_PASSWD 는 comp322로 설정하였습니다.

- 기능 설명
  - 기능들을 선택하면 예시가 나오는데 예시와 똑같이 입력하면 기능들이 수행됩니다.
  - 기능을 가장 잘 보여줄 수 있는 data들을 예시로 두었습니다.


1. 회원가입
-- Args
-- Name : ‘seockhwan’
-- USER_ID : “sk123’
-- USER_PW : “1234”
-- Phone_Number : “12-123”
-- Address : “Daehyun”
-- Postal_Code : “42363”
-- 현재의 인원을 Count 후 +1 한 값을 Customer_Id 로 설정했습니다.


2. 로그인
-- Args
-- ID : “sk123”
-- PW : “1234”
-- ID,PW 를 작성하여서 로그인 할수있게 하였습니다.


3. 해당하는 사이즈 보다 크거나 같은 사이즈를 같은 회원 수 반환
-- Args
-- Shoulder : “53”
-- Waist : “34”
-- 어꺠와 허리 사이즈를 입력받고 그와 같은 정보를 가진 사람의 수를 보여줍니다.


4. 구매한 금액을 입력 받고 해당 금액보다 같거나 큰 회원 정보 반환
-- Args
-- Price : 2000
--총 결제한 금액이 2000보다 크거나 같은 사람의 정보를 반환합니다.


5.  비밀번호 변경
-- Args
-- currnet_Id : “sk123”
-- currnet_pw: ”123”
-- change_pw: “234”
-- 자신의 현재 비밀번호를 입력받고 변환하고자 하는 비밀번호로 변경합니다.


6. 신체 사이즈 업데이트
-- Args
-- ID:”sk123”
-- top_length: “53”
-- waist : “32”
-- 사용자가 변경하고자 하는 Top_length 와 Waist 를 입력하여서 변경합니다.


7. 금액을 입력받고 해당 금액보다 큰 물품 반환
-- Args
-- Price : 20
-- 전체 물품중 20 보다 큰 금액을 가지는 물품들의 정보를 반환합니다.


8. 물품 이름의 일부를 입력 받고 해당 글자가 포함된 물품 검색
-- Args: 물품 이름 or 물품 이름 일부
-- Return: Product Name, Description, Price
-- ex. shirt → shirt 단어를 Product Name에 포함한 모든 물픔들 반환


9. 주문일이 특정 시점에 있는 오더 디테일 검색
-- Args: Order_date
-- Return: Order_date, Name, Quantity
-- ex. 2023-03-03 → 주문 날자가 2023년 03월 03일dp 주문한 order_detail의 주문 날짜, product_name, 수량 반환


10. 금액을 입력 받고 Order_Detail에서 총 구매액이 입력 금액 보다 큰 값 검색
-- Args: Price
-- Return: Customer Name, Order Date, Total Price
-- ex. 700 달러가 입력되면 총 구매 금액이 700 달러 이상인 회원 이름, 주문 날짜, 총 주문 금액 반환


11. 티어 입력 할인율
-- Args: Name
-- Return: Benefit
-- ex. level_5 → level_5인 경우 discount percentage를 반환


12. 특정 티어 이상의 회원 검색
-- Args: Name
-- Return: Customer_id, Name
-- ex. level_1 입력 → level_1 이상인 회원의 user_id, name 반환