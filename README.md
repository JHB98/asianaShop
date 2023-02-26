## :lock: Team Code Convention
### Git Commit Message

| 커밋명   | 내용                                        |
| -------- | ------------------------------------------- |
| feat     | 파일, 폴더, 새로운 기능 추가                |
| fix      | 버그 수정                                   |
| docs     | 제품 코드 수정 없음                         |
| style    | 코드 형식, 정렬, 주석 등의 변경             |
| refactor | 코드 리팩토링                               |
| test     | 테스트 코드 추가                            |
| chore    | 환경설정, 빌드 업무, 패키지 매니저 설정등.. |
| hotfix   | 치명적이거나 급한 버그 수정                 |
| remove   | 사용하지 않는 변수, 파일 etc 삭제           |
| working  | 이미 만들어진 기능, 함수 작업중             |
| merge    | branch merge                                |

### Branch

| 브랜치명 | 내용                         |
| -------- | ---------------------------- |
| develop  | 파일, 폴더, 새로운 기능 추가 |
| fix      | 버그 수정                    |
| docs     | 제품 코드 수정 없음          |
| refactor | 코드 리팩토링                |
| hotfix   | 치명적이거나 급한 버그 수정  |
| feat     | 새로운 기능 추가             |

## :file_folder: Data Base
### DB Build

| 순서 | 상세설명                         |
| -------- | ---------------------------- |
| docker desktop 설치  | https://www.docker.com/ |
| PostgreSQL 설치      | docker run --name postgres -e POSTGRES_PASSWORD=mysecretpassword -p 5432:5432 -d postgres    |
| PostgreSQL 내 psql 실행     | docker exec -it postgres psql -U postgres     |
| 사용자 생성 (psql 접속 후) | create user munsoo with encrypted password 'munsoo';      |
| 데이터베이스 생성 (psql 접속 후)  | create database project owner munsoo; |
| 데이터베이스 접속 (psql 접속시)     | docker exec -it postgres psql -U munsoo -d project;     |
| PostgreSQL 서비스 구동/중지     | docker start postgres   /   docker stop postgres    |
| PostgreSQL 사용법 (인터넷자료)     | https://velog.io/@gwak2837/%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B2%A0%EC%9D%B4%EC%8A%A4-%EC%8B%9C%EC%8A%A4%ED%85%9C-%EC%8B%A4%EC%8A%B52 |


### DB Default Setting

#### Create Member Table
```sql
CREATE TABLE MEMBERS(
   ID VARCHAR(50) PRIMARY KEY,
   PWD VARCHAR(100) not null,
   NAME  VARCHAR(50) not null,
   ADDRESS VARCHAR(200) not null,
   PHONE VARCHAR(15) not null,
   EMAIL VARCHAR(50) not null
);
```
#### Insert Member Data
```sql
INSERT INTO members
VALUES
(
   'testId1',
   'testPw1',
   'testName1',
   'testAddress1',
   'testPhone1',
   'testEmail1'
),
(
   'testID2',
   'testPw2',
   'testName2',
   'testAddress2',
   'testPhone2',
   'testEmail2'
),
(
   'testID3',
   'testPw3',
   'testName3',
   'testAddress3',
   'testPhone3',
   'testEmail3'
),
(
   'testID4',
   'testPw4',
   'testName4',
   'testAddress4',
   'testPhone4',
   'testEmail4'
),
(
   'testID5',
   'testPw5',
   'testName5',
   'testAddress5',
   'testPhone5',
   'testEmail5'
);
```
#### Create Product Table
```sql
CREATE TABLE product(
   number INTEGER PRIMARY KEY,
   brand VARCHAR(50) not null,
   name VARCHAR(50) not null,
   price  INTEGER not null,
   category VARCHAR(10) not null
);
```
#### Insert Product Data
```sql
INSERT INTO product
VALUES
(
   1111,
   '수아레',
   '워셔블 니트',
   39900,
   'TOP'
),
(
   2222,
   '스파오',
   '(시티보이) 오버핏 옥스포드 셔츠',
   35910,
   'TOP'
),
(
   3333,
   '예일',
   '2 TONE ARCH HOODIE GRAY',
   63200,
   'TOP'
),
(
   4214124,
   '어반드레스',
   'SHINE SLIM ONEPIECE SKY',
   26550,
   'DRESS'
),
(
   15515,
   '하트클럽',
   'HEART 21 카라 리본 로고 드레스',
   109000,
   'DRESS'
),
(
   567567,
   '파르티',
   '멘토 오바사이즈 롱 코트',
   46500,
   'OUTER'
),
(
   464578,
   '스파오',
   '파스텔 푸퍼',
   39900,
   'OUTER'
),
(
   53736,
   '제로',
   'Deep One Tuck Sweat Pants',
   39000,
   'BOTTOM'
);

INSERT INTO product
VALUES
(
   215415,
   '드로우 핏',
   '터틀넥 니트 티셔츠',
   43900,
   'TOP'
),
(
   692982,
   'MainBooth',
   'Traveler Oversized Sweater',
   53400,
   'TOP'
),
(
   80560,
   '가니송',
   '보우 셔링 원피스',
   159600,
   'DRESS'
),
(
   180484,
   '마르디 메르크디',
   'MAXI DRESS V NECK STRIPE_GREY',
   175750,
   'DRESS'
),
(
   1508048,
   '도프제이슨',
   '오버핏 비건레더 싱글 자켓',
   89000,
   'OUTER'
),
(
   6812391,
   '코드그라피',
   'CGP아치 로고 후드 집업',
   35800,
   'OUTER'
),
(
   48701498,
   '무신사',
   '스탠다드 스트레이트 데님 팬츠',
   44790,
   'BOTTOM'
),
(
   91870490,
   '유니폼 브릿지',
   '1960 sweatshirts navy',
   58500,
   'TOP'
),
(  
   7980980, 
   '스파오',
   '베이직 퍼플리스 집업_SPFZC4TU01',
   14900,
   'OUTER'
),
( 
   4088049,
   '제로',
   'Classic Sweat Pants [Grey]',
   35100,
   'BOTTOM'
),
( 
   65924592,
   '제이마크뉴욕',
   'Single button collar neck dress - Navy',
   115200,
   'DRESS'
),
(
   605940780,
   '페이탈리즘',
   '#0214 Moderation indigo straight fit',
   59000,
   'BOTTOM'
);
```
```sql
update product set price=31 where number = 1111;
update product set price=27 where number = 2222;
update product set price=48 where number = 3333;
update product set price=20 where number = 4214124;
update product set price=84 where number = 15515;
update product set price=35 where number = 567567;
update product set price=31 where number = 464578;
update product set price=30 where number = 53736;
update product set price=33 where number = 215415;
update product set price=41 where number = 692982;
update product set price=122 where number = 80560;
update product set price=135 where number = 180484;
update product set price=68 where number = 1508048;
update product set price=27 where number = 6812391;
update product set price=34 where number = 48701498;
update product set price=45 where number = 91870490;
update product set price=11 where number = 7980980;
update product set price=27 where number = 4088049;
update product set price=88 where number = 65924592;
update product set price=45 where number = 605940780;
```
#### Create# Cart Table

```sql
CREATE TABLE cart(
   cartNum INTEGER PRIMARY KEY,
   userId VARCHAR(50) REFERENCES members(id),
   productNum INTEGER REFERENCES product(number),
   amount INTEGER not null
);
```
#### Insert Cart Data (munsoo, munsoo2 먼저 회원가입 필수)
```sql
INSERT INTO cart
VALUES
(
   1,
   'munsoo',
   567567,
   2
),
(
   2,
   'munsoo',
   1111,
   1
),
(
   3,
   'munsoo',
   2222,
   4
),
(
   4,
   'munsoo2',
   3333,
   10
),
(
   5,
   'munsoo2',
   4214124,
   2
);
```


## :page_with_curl: Diagram
### Sequence Diagram
```mermaid
sequenceDiagram
    actor Client
    Client->>Bob: Hi Bob
    Bob->>Client: Hi Alice
```
### Entity Relationship Diagrams
```mermaid
erDiagram
    members ||--|{ cart : have
    members {
        VARCHAR id
        VARCHAR pwd
        VARCHAR name
        VARCHAR address
        VARCHAR phone
        VARCHAR email
    }
    product ||--|{ cart : contain
    cart {
      INTEGER cartNum
      VARCHAR userId
      INTEGER productNum
      INTEGER amount
    }
    product {
      INTEGER number
      VARCHAR brand
      VARCHAR name
      INTEGER price
      VARCHAR category
    }
```