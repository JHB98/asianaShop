id
password
이름
주소
전화번호
이메일


```sql
CREATE TABLE MEMBERS(
   ID VARCHAR(50) PRIMARY KEY,
   PWD VARCHAR(50) not null,
   NAME  VARCHAR(50) not null,
   ADDRESS VARCHAR(200) not null,
   PHONE VARCHAR(15) not null,
   EMAIL VARCHAR(50) not null
);
```

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

PostgreSQL 사용법
PostgreSQL docker 활용법
docker desktop 설치

https://www.docker.com/
Docker Desktop Installer.exe

PostgreSQL 설치
docker run --name postgres -e POSTGRES_PASSWORD=mysecretpassword -p 5432:5432 -d postgres

PostgreSQL 내 psql 실행
docker exec -it postgres psql -U postgres

사용자 생성 (psql 접속 후)
create user munsoo with encrypted password 'munsoo';

데이터베이스 생성 (psql 접속 후)
create database project owner munsoo;

데이터베이스 접속 (psql 접속시)
docker exec -it postgres psql -U munsoo -d project;

PostgreSQL 서비스 구동/중지
docker start postgres
docker stop postgres

PostgreSQL 사용법 (인터넷자료)
https://velog.io/@gwak2837/%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B2%A0%EC%9D%B4%EC%8A%A4-%EC%8B%9C%EC%8A%A4%ED%85%9C-%EC%8B%A4%EC%8A%B52

DB 관련 툴
구분	URL	파일
DBever	https://dbeaver.io/	dbeaver-ce-22.3.3-x86_64-setup.exe
DataGrip	https://www.jetbrains.com/datagrip/	
SQL Developer	https://www.oracle.com/kr/database/sqldeveloper/	sqldeveloper-22.2.1.234.1810-x64.zip
