# Springboot-board
스프링 부트 프레임워크와 마이바티스를 활용한 게시판

# 버전 관리 규칙

## 브랜치 목록, 설명
<메인 브랜치>
 - master : 배포 가능한 소스만 있는 브랜치
 - dev : 다음 출시 버전을 개발하는 브랜치

<보조 브랜치> </br>
 - feature : 기능을 개발하는 브랜치
 - release : 이번 출시 버전을 준비하는 브랜치
 - hotfix : 출시 버전에서 발생한 버그를 수정하는 브랜치

## commit 명명
이슈가 없을경우 이슈번호 생략

제목(#이슈번호)</br>
(여기 한줄 띄워줘야한다)</br>
내용 </br>

## 버전 업
+ +1.0.0 : 디자인 변경, 리뉴얼, 기능이 10개 이상 추가되어 X.9.9 넘어갈 경우
+ +0.1.0 : 기능 추가, API 변경 </br>
+ +0.0.1 : 버그 수정 </br>




## 작업 순서

### <상시로 버그 수정>

##### 1. dev 브랜치로 이동, 개발

###### git checkout dev

###### /버그에 대한 수정 및 테스트/

##### 2. release 브랜치 생성, 버전 태그 부여

###### git checkout -b release-'버전명' dev

###### git tag -a 버전명

##### 3. 정상작동하면 master 브랜치로 이동, release 브랜치 merge

###### git checkout master

###### git merge release-'버전명'

##### 4. dev 브랜치로 이동, release 브랜치 merge 후 삭제

###### git checkout dev

###### git merge release-'버전명'

###### git branch -d release-'버전명'

### <새로운 기능 추가 작업 (feature 브랜치 사용)>

##### 1. dev 브랜치에서 feature 브랜치 생성, 개발

###### git checkout -b feature-'이슈번호'- 기능설명

###### /새로운 기능 개발 및 테스트/

##### 2. release 브랜치 생성, 버전 태그 부여

###### git checkout -b release-'버전명' dev

###### git tag -a 버전명

##### 3. 정상작동하면 master 브랜치로 이동, release 브랜치 merge

###### git checkout master

###### git merge release-'버전명'

##### 4. dev 브랜치로 이동, release 브랜치 merge 후 삭제

###### git checkout dev

###### git merge release-'버전명'

###### git branch -d release-'버전명'


### <출시 버전에서 발생한 버그 수정>

##### 1. master 브랜치에서 hotfix 브랜치를 분기, 버그 수정

###### / 문제가 되는 부분만을 빠르게 수정 /

##### 2. 수정 후 master 브랜치로 이동, 병합

###### git checkout master

###### git merge hotfix-다음 버전명

##### 3. 태그 부여

###### git tag -a 다음버전명

##### 3. dev 브랜치에도 적용

###### git checkout dev

###### git merge hotfix-다음 버전명
