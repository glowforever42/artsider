from bs4 import BeautifulSoup

from selenium import webdriver
from selenium.webdriver.common.keys import Keys
import pandas as pd
import GetCategory as cate
# from selenium.webdriver.common.desired_capabilities import DesiredCapabilities
# from selenium.webdriver.chrome.service import Service
# from webdriver_manager.chrome import ChromeDriverManager

import time
### Crawling Setup
chromedriver = 'C:/Users/multicampus/Downloads/chromedriver_win32/chromedriver'
# browser = webdriver.Chrome(chromedriver)
# browser.implicitly_wait(3)
urls = cate.get_urls_list("http://mticket.interpark.com/genre?Genre=MU")#여기부분 밑에 있는 주소로 바꿔주셔야 합니당(뮤지컬,콘서트, 연극,클래식 무용, 아동가족 등 전체 리스트 있는 URL 입니당)
#http://mticket.interpark.com/genre?Genre=MU
#http://mticket.interpark.com/genre?Genre=CO
#http://mticket.interpark.com/Genre?Genre=DR
#http://mticket.interpark.com/genre?Genre=CL
#http://mticket.interpark.com/genre?Genre=FA

#FOR문 하나 더 만드셔서 한번에 돌리셔도 되긴 하는데, 저는 그렇게 돌리다 에러나면 힘들거 같아서 따로 돌렸습니당
#위의 부분 수정하시고 29라인 FOR문 밑에 치우님이 작성하신부분 넣으시면 될 것 같아요!



review_list = []
for url in urls:
    browser = webdriver.Chrome(chromedriver)
    browser.implicitly_wait(3)
    browser.get(url)

    # 인터파크 처음 모달창 없애기(있을 경우에만 실행)
    try:
        browser.find_element_by_class_name("buttonLayerCloseToday").send_keys(Keys.ENTER)
        time.sleep(0.5)
    except:
        print("no modal")

    # 인터파크 리뷰보기 클릭
    try:
        browser.find_element_by_class_name("productsTabPosts").click()
    except:
        print("review tab doesn't exist")

    # 인터파크 리뷰 더보기 버튼 끝까지 클릭(리뷰 정보를 전체다 띄우기 위해서)
    while 1:
        try:
            browser.find_element_by_class_name("moreBtn").send_keys(Keys.ENTER)
            time.sleep(0.2)
        except:
            break

    try:

        # 크롤링 시작
        html = browser.page_source
        soup = BeautifulSoup(html, 'html.parser')

        # score 크롤링 userBoardList
        reviews = soup.find("ul", {"id": "writerInfo"})
        reviews = reviews.find_all("li")

        # 리뷰 하나당 별점, 좋아요, 내용, user, 조회, 날짜, 예매자인지 여부

        for review in reviews:
            # 공연id 임의값으로 넣어 놨음 수정 필요
            id = url[61:69]
            # 별점
            score = review.find("div", {"class": "shareInfo"}).find('div').get('class')[1]
            score = score[5:]  # point10 형태에서 숫자만 나오도록 자르기
            # 좋아요
            like = review.find("span", {"class": "good"}).getText()
            # 내용
            content = review.find("div", {"class": "userBoardTitle"}).getText()

            plus_data = review.find("div", {"class": "userBoardWriterInfo"}).find_all("span")
            # user
            user = plus_data[0].getText()
            # 조회수
            views = plus_data[2].getText()[2:]
            # 등록날짜
            date = plus_data[3].getText()
            # 관람객 여부
            is_buyer = True
            if len(plus_data) != 5:
                is_buyer = False
            review_list.append([id, score, like, content, user, views, date, is_buyer])
        browser.close()
    except:
        print("no reviews")

total = len(review_list)
print("총 리뷰 수: "+str(total))
print("총 공연 수: " + str(len(urls)))
df = pd.DataFrame(review_list, columns=['id', 'score', 'like', 'content', 'user', 'views', 'date', 'is_buyer'])
df = df.replace('\n', ' ', regex=True)
df.to_csv("reviews.csv", index=False, encoding="utf-8-sig")
