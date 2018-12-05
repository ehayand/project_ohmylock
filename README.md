# Oh My Lock
2018.11 KB App Challenge 장려상

이 문서는 Oh My Lock 어플리케이션에서 사용되는 API에 대해 기술합니다.
 
### API specifications
 
#### 1. 로그인
```http request
Method : POST
URL : http://:server_url/formlogin
Content-Type : application/json
Parameters
    필드          타입      필수여부           설명
    id           String    Required        유저 아이디
    password     Stirng    Required        유저 비밀번호
```

```http request
curl -XPOST 'http://:server_url/firmlogin' -d '{"id":"userId", "password":"userPassword"}'
```
 
```http request
Response
    필드          타입      필수여부           설명
    token        String    Required        Authentication JSON WEB TOKEN
```

#### 2. 회원 가입
```http request
Method : POST
URL : http://:server_url/signup
Content-Type : application/json
Parameters
    필드          타입      필수여부           설명
    name         String    Required        유저 이름
    id           String    Required        유저 아이디
    password     Stirng    Required        유저 비밀번호
    email        String    Required        유저 이메일
    phone        String    Required        유저 연락처
```

```http request
curl -XPOST 'http://:server_url/firmlogin' -d '{"name":"userName", "id":"userId", "password":"userPassword", "email":"userEmai", "phone":"userPhone"}'
```

```http request
Response
    필드          타입      필수여부           설명
    message      String    Required        SUCCESS / FAILED
    error        String    Required        Error Message
    data         Map       Optional        유저 이름
```
         
#### 3. 회원 탈퇴
```http request
Method : DELETE
URL : http://:server_url/key/user
Authorization : TOKEN
```

```http request
curl -XDELETE 'http://:server_url/key/user'
```

```http request
Response
    필드          타입      필수여부           설명
    message      String    Required        SUCCESS / FAILED
    error        String    Required        Error Message
    data         Map       Optional        유저 이름
```
 
#### 4. 회원 정보
```http request
Method : GET
URL : http://:server_url/key/user
Authorization : TOKEN
```

```http request
curl -XGET 'http://:server_url/key/user'
```

```http request
Response
    필드          타입      필수여부           설명
    message      String    Required        SUCCESS / FAILED
    error        String    Required        Error Message
    data         Map       Optional        유저 정보
```
 
#### 5. 연락처 변경
```http request
Method : POST
URL : http://:server_url/key/user/update/phone
Authorization : TOKEN
Parameters
    필드          타입      필수여부           설명
    newPhone     String    Required        새로운 비밀번호
```

```http request
curl -XPOST 'http://:server_url/key/user/update/phone' -d '{"newPhone":"userNewPhone"}'
```

```http request
Response
    필드          타입      필수여부           설명
    message      String    Required        SUCCESS / FAILED
    error        String    Required        Error Message
    data         Map       Optional        
```

#### 6. 이메일 변경
```http request
Method : POST
URL : http://:server_url/key/user/update/email
Authorization : TOKEN
Parameters
    필드          타입      필수여부           설명
    newEmail     String    Required        새로운 이메일
```

```http request
curl -XPOST 'http://:server_url/key/user/update/email' -d '{"newEmail":"userNewEmail"}'
```

```http request
Response
    필드          타입      필수여부           설명
    message      String    Required        SUCCESS / FAILED
    error        String    Required        Error Message
    data         Map       Optional        
```

#### 7. 비밀번호 변경
```http request
Method : POST
URL : http://:server_url/key/user/update/password
Authorization : TOKEN
Parameters
    필드          타입      필수여부           설명
    newPassword  String    Required        새로운 비밀번호
```

```http request
curl -XPOST 'http://:server_url/key/user/update/password' -d '{"newPassword":"userNewPassword"}'
```

```http request
Response
    필드          타입      필수여부           설명
    message      String    Required        SUCCESS / FAILED
    error        String    Required        Error Message
    data         Map       Optional        
```
                        
#### 8. 포인트 정보
```http request
Method : GET
URL : http://:server_url/key/point
Authorization : TOKEN
```

```http request
curl -XGET 'http://:server_url/key/point'
```

```http request
Response
    필드          타입      필수여부           설명
    message      String    Required        SUCCESS / FAILED
    error        String    Required        Error Message
    data         Map       Optional        포인트 정보
```

#### 9. 포인트 변경
```http request
Method : POST
URL : http://:server_url/key/point/update
Authorization : TOKEN
Parameters
    필드          타입      필수여부           설명
    difference   long    Required        추가 포인트
```

```http request
curl -XPOST 'http://:server_url/key/point/update' -d '{"difference":"addPoint"}'
```

```http request
Response
    필드          타입      필수여부           설명
    message      String    Required        SUCCESS / FAILED
    error        String    Required        Error Message
    data         Map       Optional        포인트 정보
```

#### 10. 포인트 상품 정보
```http request
Method : GET
URL : http://:server_url/key/point/products
Authorization : TOKEN
```

```http request
curl -XGET 'http://:server_url/key/point/products'
```

```http request
Response
    필드          타입      필수여부           설명
    message      String    Required        SUCCESS / FAILED
    error        String    Required        Error Message
    data         Map       Optional        포인트 상품(List)
```

#### 11. 카드 정보(락 화면)
```http request
Method : GET
URL : http://:server_url/key/card/lock
Authorization : TOKEN
```

```http request
curl -XGET 'http://:server_url/key/card/lock'
```

```http request
Response
    필드          타입      필수여부           설명
    message      String    Required        SUCCESS / FAILED
    error        String    Required        Error Message
    data         Map       Optional        카드 정보(List)
```

#### 12. 카드 정보(인 앱)
```http request
Method : GET
URL : http://:server_url/key/card/inapp
Authorization : TOKEN
```

```http request
curl -XGET 'http://:server_url/key/card/inapp'
```

```http request
Response
    필드          타입      필수여부           설명
    message      String    Required        SUCCESS / FAILED
    error        String    Required        Error Message
    data         Map       Optional        카드 정보(List)
```

#### 13. 광고 정보(락 화면)
```http request
Method : GET
URL : http://:server_url/key/advert/lock
Authorization : TOKEN
```

```http request
curl -XGET 'http://:server_url/key/card/lock'
```

```http request
Response
    필드          타입      필수여부           설명
    message      String    Required        SUCCESS / FAILED
    error        String    Required        Error Message
    data         Map       Optional        광고 정보(랜덤 1개)
```

#### 14. 광고 정보(인 앱)
```http request
Method : GET
URL : http://:server_url/key/advert/inapp/list
Authorization : TOKEN
```

```http request
curl -XGET 'http://:server_url/key/card/inapp/list'
```

```http request
Response
    필드          타입      필수여부           설명
    message      String    Required        SUCCESS / FAILED
    error        String    Required        Error Message
    data         Map       Optional        광고 정보(List)
```
