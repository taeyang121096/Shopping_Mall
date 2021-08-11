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

![image](https://user-images.githubusercontent.com/74217059/129038486-43042474-cecd-400e-804d-7a4304ed4038.png)




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
   
    
   
