# TIL

> sub2 명세서를 기반으로 학습한 내용을 정리한 문서입니다.

**22.03.14**

- numpy, scipty, Scikit-learn, 추천 시스템 종류에 대해 간단한 개념정리
- numpy 함수 활용 튜토리얼을 일정부분만 진행해보았다.

**22.03.15**

- 남은 numpy 함수 활용 튜토리얼 진행
- 시간적 여유가 없어서 다른 함수는 보지 못했다. 조금씩 공부할 예정

**22.03.16**

- EC2에서 Docker + 젠킨스로 CI/CD 진행

**22.03.17**

- numpy 함수 튜토리얼 진행

# 목차

- [데이터 과학 라이브러리](#데이터-과학-라이브러리)
  - [Numpy](#Numpy)
  - [Scipy](#Scipy)
  - [Scikit-learn](#Scikit-learn)
- [추천 시스템 종류](#추천-시스템-종류)
  - [컨텐츠 기반 필터링 학습 및 구현](#컨텐츠-기반-필터링-학습-및-구현)
  - [협업 필터링 학습 및 구현](#협업-필터링-학습-및-구현)

------

# 데이터 과학 라이브러리

## Numpy

- 다차원 배열의 행렬 연산을 빠르게 수행하기 위한 데이터 구조를 제공하는 파이썬 라이브러리
- 기본적인 데이터 구조와 연산을 제공
- [참고](http://aikorea.org/cs231n/python-numpy-tutorial/#numpy)

### 배열

> rank란 배열이 몇 차원인지를 의미
>
> shape는 각 차원의 크기를 알려주는 정수들이 모인 튜플

```python
import numpy as np

a = np.array([1, 2, 3]) # rank가 1인 배열 = 1차원 배열 생성
b = np.array([[1,2,3],[4,5,6]]) # rank가 2인 배열 = 2차원 배열 생성

print a.shape # 출력 "(3,)"
print b.shape # 출력 "(2, 3)"

print a[0]	  # 출력 "1"
print b[0, 0] # 출력 "1"
```

### 배열 인덱싱

> **[사전학습] 파이썬 리스트의 슬라이스**
>
> ```python
> a = [0, 10, 20, 30, 40, 50, 60, 70, 80, 90]
> 
> # 슬라이스로 가져오는 요소는 "시작 인덱스 ~ 끝 인덱스-1"
> a[0:4] # 인덱스 0부터 3까지 자르기 => [0, 10, 20, 30]
> a[1:1] # 인덱스 1부터 0까지 자르기, 즉 아무것도 안가져온다 => []
> 
> # 인덱스에서 -1은 뒤에서 첫 번째 요소를 의미한다
> # 인덱스 4부터 -2까지 요소 5개를 자르기
> a[4:-1] # [40, 50, 60, 70, 80]
> 
> # 인덱스 생략
> a[:7] # 리스트 처음부터 인덱스 6까지 자르기 => [0, 10, 20, 30, 40, 50, 60]
> ```

- `슬라이싱` : 다차원인 경우가 많기에, 각 차원별로 **어떻게 슬라이스할건지 명확**히 해야 한다.
  - **정수 인덱싱**과 **슬라이싱을 혼합**해서 사용하면 **낮은 rank의 배열**이 생성된다. 하지만 **슬라이싱만** 사용하면 **원본 배열과 동일한 rank의 배열**이 생성된다.
  - 슬라이싱된 배열은 **원본 배열과 같은 데이터를 참조**한다. 즉, 슬라이싱된 배열을 <u>수정하면 원본 배열 역시 수정</u>된다.

```python
import numpy as np

a = np.array([[1,2,3,4], [5,6,7,8], [9,10,11,12]])
row_r1 = a[1, :] 	# [5 6 7 8]
row_r2 = a[:2, 1:3] # [[2 3], [6 7]] => 1:3 때문에 0열이 잘림

print row_r1.shape # 출력 (4,)
print row_r2.shape # 출력 (2,2)

print a[0, 1] # 출력 "2"
row_r2[0, 0] = 77 # row_r2[0, 0]은 a[0, 1]과 같은 데이터
print a[0, 1] # 출력 "77" -> a 원본 배열 수정됨
```

- `정수 배열 인덱싱`
  - 정수 배열 인덱싱을 유용하게 사용하는 방법 중 하나는 **행렬의 각 행에서 하나의 요소를 선택하거나 바꾸는 것**
- `불리언 배열 인덱싱`
  - 배열 속 요소를 취사선택할 수 있다.
  - **특정 조건을 만족하게 하는 요소만 선택**하고자 할 때 자주 사용

```python
import numpy as np

a = np.array([[1,2,3], [4,5,6], [7,8,9], [10, 11, 12]])

# 정수 배열 인덱싱 예1
print a[[0, 1, 2], [0, 1, 0]]  # 출력 "[1 5 7]"

# 정수 배열 인덱싱 예2
print np.array([a[0, 0], a[1, 1], a[2, 0]])  # 출력 "[1 5 7]"

# 인덱스를 저장할 배열 생성
b = np.array([0, 2, 0, 1])

# b에 저장된 인덱스를 이용해 각 행에서 해당하는 열의 요소를 선택한다
print a[np.arange(4), b]  # 출력 "[ 1  6  7 11]"

# b에 저장된 인덱스를 이용해 각 행에서 해당하는 열의 요소를 변경한다.
a[np.arange(4), b] += 10

print a 
# array([[11,  2,  3],
#       [ 4,  5, 16],
#       [17,  8,  9],
#       [10, 21, 12]])
```

```python
import numpy as np

a = np.array([[1,2], [3, 4], [5, 6]])

# 2보다 큰 a의 요소를 찾는다
# 각 모든 요소에서 조건 체크
bool_idx = (a > 2)

print bool_idx
# 출력 "[[False False]
#       [ True  True]
#       [ True  True]]"

# 아래와 같이 불리언 배열 인덱싱 사용 가능
print a[bool_idx] # 출력 "[3 4 5 6]" 
```

### 자료형

> Numpy 배열은 동일한 자료형을 가지는 값들이 격자판 형태로 있는 것으로, 배열을 구성하는데 사용할 수 있는 **다양한 숫자 자료형 제공**한다.
>
> 배열이 생성될 때 **자료형을 스스로 추측**한다. but, **명시적**으로 특정 자료형을 **지정**할 수도 있다.

```python
import numpy as np

x = np.array([1.0, 2.0])  # Numpy가 자료형을 추측해서 선택
print x.dtype             # 출력 "float64"

x = np.array([1, 2], dtype=np.int64)  # 특정 자료형을 명시적으로 지정
print x.dtype                         # 출력 "int64"
```

### 배열 연산

> 기본적인 수학함수는 **배열의 각 요소별로 동작**하며 **연산자를 통해 동작**하거나 **numpy 함수모듈을 통해 동작**한다.

```python
import numpy as np

x = np.array([[1,2],[3,4]], dtype=np.float64)
y = np.array([[5,6],[7,8]], dtype=np.float64)

# 요소별 합
# [[ 6.0  8.0] # 1+5, 2+6
#  [10.0 12.0]] # 3+7, 4+8
print x + y
print np.add(x,y)

# 요소별 차
# [[-4.0 -4.0]
#  [-4.0 -4.0]]
print x - y
print np.subtract(x, y)

# 요소별 곱
# [[ 5.0 12.0]
#  [21.0 32.0]]
print x * y
print np.multiply(x, y)

# 요소별 나눗셈
# [[ 0.2         0.33333333]
#  [ 0.42857143  0.5       ]]
print x / y
print np.divide(x, y)

# 요소별 제곱근
# [[ 1.          1.41421356]
#  [ 1.73205081  2.        ]]
print np.sqrt(x)

# ==== add를 sum과 헷갈리지 말자! ====
print np.sum(x)  # 모든 요소를 합한 값을 연산; 출력 "10.0"
print np.sum(x, axis=0)  # 각 열에 대한 합을 연산; 출력 "[4 6]"
print np.sum(x, axis=1)  # 각 행에 대한 합을 연산; 출력 "[3 7]"
```

> 벡터의 내적, 백터와 행렬의 곱, 행렬곱 => dot 사용

```python
x = np.array([[1,2],[3,4]])
y = np.array([[5,6],[7,8]])

v = np.array([9,10])
w = np.array([11, 12])

# 벡터의 내적; 둘 다 결과는 219
print v.dot(w)
print np.dot(v, w)

# 행렬과 벡터의 곱; 둘 다 결과는 rank 1인 배열 [29 67]
print x.dot(v)
print np.dot(x, v)

# 행렬곱; 둘 다 결과는 rank 2인 배열
# [[19 22]
#  [43 50]]
print x.dot(y)
print np.dot(x, y)
```

> 배열의 모양을 바꾸거나 데이터를 처리해야 할 때도 사용할 수 있다. 간단한 예는 행렬의 주 **대각선을 기준**으로 **대칭되는 요소끼리 뒤바꾸는 것**<br/>=> **전치**

## Scipy

- 계산과학 분야에 사용되는 다양한 수학 알고리즘을 모아 놓은 파이썬 라이브러리
- Numpy의 데이터 구조를 활용하여 다양하고 복잡한 알고리즘을 구현하여 제공

## Scikit-learn

- 데이터 마이닝 및 분석에 필요한 효과적인 기능을 제공해주는 파이썬 라이브러리
- Numpy 및 Scipy로 만들어짐

# 추천 시스템 종류

> 정보 필터링 기술의 일종으로, 특정 사용자가 관심을 가질만한 정보를 추천하는 것
>
> [참고 자료]
>
> - https://brunch.co.kr/@kakao-it/72
> - [https://www.samsungsemiconstory.com/kr/백발백중-취향저격수-추천-알고리즘의-비밀/](https://www.samsungsemiconstory.com/kr/%EB%B0%B1%EB%B0%9C%EB%B0%B1%EC%A4%91-%EC%B7%A8%ED%96%A5%EC%A0%80%EA%B2%A9%EC%88%98-%EC%B6%94%EC%B2%9C-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98%EC%9D%98-%EB%B9%84%EB%B0%80/)

## 컨텐츠 기반 필터링 학습 및 구현

- **Content Based Filtering (CBF)**

- 추천의 대상이 되는 아이템과 사용자에 대한 이해를 바탕으로 추천하는 방식

- **아이템의 특성을 뽑아내는 것이 중요**

- 예를 들어 추천 대상이 영화라면..

  - `객관적 특성` : 장르, 감독, 배우, 작곡가, 제작사와 같은 영화 자체 **구성요소**

    => 영화 메타 데이터를 적절히 가공하여 **벡터화** 시키기

  - `사용자 주관적 특성` : 영화에 대한 각 관람객의 **리뷰와 평점**

    => 텍스트 데이터를 **자연어 처리(NLP) 기법**을 통해 **핵심 단어** 찾기 or **단어들을 임베딩**하여 처리

  - 그 외 복잡한 특성 : 수치화하기 힘든 영상미, 흐름의 개연성, 연기력, 포스터 등등

    => 이미지, 비디오 데이터는 **이미지 분석 기법**을 사용해 정보 얻기  (딥러닝)

## 협업 필터링 학습 및 구현

- **Collaborative Filtering (CF)**

- 사용자의 상품에 대한 기록 정보를 바탕으로 특성 벡터를 직접 수치화 하는 것이 아닌 머신 러닝 방식으로 자동적으로 수치화함으로써 각 사용자가 무엇을 좋아할 지를 예측하는 기법