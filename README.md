# Shopping_Mall
Make a Shop about ....
## dev -> Backend(dev) : 이태양 Frontend(dev) : 김현수, 이현구


----------------

### 기능 목록

1. 회원 기능 :
    - 회원가입 -> O
    - 로그인
    - 회원 정보 조회(MyPage) -> O
    로그인 생성 및 인증 관련 개발 필요

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

-----------------
### Commit Rules
1. dev : 새로운 개발인 경우 (path도 꼭 쓸것!!)
2. upd : 수정한 경우 (path도 꼭 쓸것!!), add(추가), del(삭제), upd(수정)
3. del : 삭제한 경우 (path도 꼭 쓸것!!)
4. "Rule : 파일명 -> 설명(간략하게) 알아서 잘 쓰세용"

### ex)
### git commit -m "dev : login.java -> login "
### git commit -m "upd : readme.md -> Commit_Rules and URI_Rules (add) 리뷰기능 (del)  테이블 Address (upd)"
### git commit -m "del : readme.md "


-----------------
### URI Rules 
1. resource 위주로 짜기 
-----------------

### 도메인 모델 설계

![image](https://user-images.githubusercontent.com/74217059/129038788-73acaad4-bc0d-4b2d-938f-42934b19ed85.png)

-----------------
### 테이블

1. Member :
   + id : Long(pk)
   + userId : String
   + userPw : String
   + nickname : String
   + userName : String
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
   + review : Review
   

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
   + content : String

8. Address
   + city : String
   + street : String
   + zipcode : String
   
-----------------
### 테이블 다이어그램   


![KakaoTalk_Photo_2021-08-21-01-19-06](https://user-images.githubusercontent.com/56467808/130263530-baee26ea-c13b-4504-9a69-f050cdc7cf1e.png)


 
    
   
