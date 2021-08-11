# Shopping_Mall
Make a Shop about ....
## dev -> Backend(dev) : 이태양 Frontend(dev) : 김현수, 이현구


----------------

### 기능 목록

1. 회원 기능 :
    - 회원가입
    - 로그인
    - 회원 정보 조회(MyPage)

2. 상품 기능 :
    - 상품 등록
    - 상품 수정
    - 상품 조회


3. 주문 기능 :
    - 상품 주문
    - 주문 내역 조회
    - 주문 취소



4. 리뷰 기능 :
    - 리뷰 등록
        - 별점 등록
    - 리뷰 조회
        - 별점 조회


### 도메인 모델 테이블 설계

![image](https://user-images.githubusercontent.com/74217059/129038788-73acaad4-bc0d-4b2d-938f-42934b19ed85.png)

-----------------

1. Member :
   + id : Long(pk)
   + Id : String
   + pw : String
   + nickname : String
   + name : String
   + address : Address
   + number : String
   + orders : List
   
2. Order :  
   + id : Long(pk)
   + member : Member
   + orderItems : List
   + delivery : Delivery
   + orderDate : Date
   + status : OrderStatus

3. Delivery :
   + id : Long(pk)
   + order : Order
   + address : Address
   + status : DeliveryStatus

4. OrderItem :  
   + id : Long(pk)
   + item : Item
   + order : Order
   + orderPrice : int
   + count : int

5. Category :
   + id : Long(pk)
   + name : String
   + items : List
   + parent : Category
   + child : List

6. Item :
   + id : Long(pk)
   + name : String
   + price : int
   + stock : int
   + Categories : List

7. Review :
   + id : Long(pk)
   + contnet : 글
   

    
   
