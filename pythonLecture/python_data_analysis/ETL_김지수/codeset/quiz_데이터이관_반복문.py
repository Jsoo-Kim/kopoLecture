#!/usr/bin/env python
# coding: utf-8

# In[142]:


# 현재 코드 작성 중인 곳으로 들어감
get_ipython().run_line_magic('cd', 'codeset/')


# In[146]:


import pandas as pd
import os

# 추출할 파일들이 들어있는 폴더를 이용해 파일 리스트 생성
targetFolder = "../dataset/"
fileList = os.listdir(targetFolder) # ../dataset에 있는 파일들을 리스트에 담아줌!
# print(fileList)


# 이관할 데이터 넣어줄 폴더 생성
targetDir = "../dataset_out/"

if os.path.exists(targetDir):
    print(f"dataset_out 폴더가 이미 존재합니다: {targetDir}")
else:
    os.makedirs(targetDir)
    print(f"dataset_out 폴더가 생성되었습니다: {targetDir}")
    
    
# 파일 포맷 설정
from datetime import datetime
now = datetime.now()
filename_format = "%Y%m%d%H%M"
formatted_datetime = now.strftime(filename_format)    


# 파일 추출이 잘 되는지 확인 (생략 가능)
# inData = pd.read_csv("../dataset/kopo_product_volume.csv") 
# inData.head(2) # 추출한 파일의 맨 위 2개 행 읽어 옴


# 특정 파일명을 가진 파일들을 특정 폴더에 저장
preFix = "kopo_product"
endFix = ".csv"
preFixLen = len(preFix)
# print(preFixLen)
endFixLen = len(endFix)
# print(endFixLen)

for file in fileList:
    if (file[ : preFixLen] == preFix and file[ -endFixLen : ] == endFix):
        filename = f"{file[ : -endFixLen]}_{formatted_datetime}" # 여기에서 파일 저장명 재설정
        print(filename)
        inData = pd.read_csv(f"{targetFolder}{file}") # 파일 추출
        inData.to_csv(f"{targetDir}{filename}_{formatted_datetime}.csv", index=False) # 파일 저장


# ### 교수님 답안 - 틀만 잡아주신 것!

# In[128]:


# # 1. 폴더 생성
# inFolder = "../dataset/"
# outFolder = "../dataset_out2/"

# if os.path.exists(outFolder):
#     print(f"dataset_out2 폴더가 이미 존재합니다: {outFolder}")
# else:
#     os.makedirs(outFolder) # 여기서 폴더 생성
#     print(f"dataset_out2 폴더가 생성되었습니다: {outFolder}")


# In[129]:


# import pandas as pd
# import os

# # 추출할 파일들이 들어있는 폴더를 이용해 파일 리스트 생성
# fileList = os.listdir(inFolder)
# # print(inFolder + fileList[0])

# keyWord = "kopo_product"
# keyWordLen = len(keyWord)


# In[130]:


# # ".csv"로 끝나야 한다는 조건도 추가되어야 함! 미완성 코드
# for i in range(0, fileListLen):
#     eachFile = fileList[i]
#     print(eachFile)
    
#     if eachFile[:keyWordLen] == keyWord:
#         # 데이터 불러오기
#         indata = pd.read_csv(inFolder + eachFile) # 폴더 경로가 포함된 파일명이 들어가야 함
#         # 데이터 저장하기
#         indata.to_csv(outFolder + eachFile)
#     else:
#         pass

