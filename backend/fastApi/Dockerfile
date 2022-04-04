FROM tiangolo/uvicorn-gunicorn:python3.8

# 작업디렉토리 설정
WORKDIR /app

COPY requirements.txt /app/requirements.txt

# install dependencies
RUN pip install --no-cache-dir -r requirements.txt

# 현재경로에 존재하는 모든 소스파일을 이미지에 복사
COPY . .

EXPOSE 8000
 
CMD ["uvicorn", "main:app"]
