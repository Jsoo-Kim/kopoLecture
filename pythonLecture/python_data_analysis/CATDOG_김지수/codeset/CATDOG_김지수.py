#!/usr/bin/env python
# coding: utf-8

# In[3]:


# %pwd


# In[4]:


import pandas as pd
import os
import cv2


# In[55]:


# 1. 추출할 파일들이 들어있는 폴더를 이용해 파일 리스트 생성

petImagesFolder = "../petImages/"
petImagesFolderList = os.listdir(petImagesFolder)
# print(petImagesFolderList)
petImagesFolderListLen = len(petImagesFolderList)
# print(petImagesFolderListLen)

petFileList = [ [] for _ in range(petImagesFolderListLen)]

for i in range(0, petImagesFolderListLen):
    petFolder = f"{petImagesFolder}{petImagesFolderList[i]}/"
    print(petFolder)
    petFileList[i] = (os.listdir(petFolder))
    print(petFileList[i])

# print(petFileList)


# In[56]:


# 2. 이관할 데이터를 넣어줄 폴더 생성

myPetDirArr = [ "" for _ in range(petImagesFolderListLen)]

for i in range(0, petImagesFolderListLen):
    myPetDirArr[i] = f"../{petImagesFolderList[i]}_out/"
    myPetDir = myPetDirArr[i]
    # print(myPetDir)
    
    if os.path.exists(myPetDir):
        print(f"{petImagesFolderList[i]}_out 폴더가 이미 존재합니다: {myPetDir}")
    else:
        os.makedirs(myPetDir)
        print(f"{petImagesFolderList[i]}_out 폴더가 생성되었습니다: {myPetDir}")


# In[62]:


# 3. 특정 파일 확장자를 가진 파일들을 특정 폴더에 저장

endFix = ".jpg"
endFixLen = len(endFix)
petFileListlen = len(petFileList)

for i in range(0, petFileListlen):
    petFile = petFileList[i]
    petFileLen = len(petFile)
    petDir = petImagesFolderList[i]
    myPetDir = myPetDirArr[i]
    for i in range(0, petFileLen):
        petImage = petFile[i]
        if (petImage[ -endFixLen : ] == endFix):
            img = cv2.imread(f"{petImagesFolder}{petDir}/{petImage}", cv2.IMREAD_COLOR) # 이미지 추출(불러오기) => 왜 img에 None이 담기지? => petImagesFolder와 petImage 사이에 "cat/"같은 경로가 추가되어야 함
            # print(f"img load{petImagesFolder}{petDir}/{petImage}")
            # print(img)
            cv2.imwrite(f"{myPetDir}{petImage}", img) # 이미지 저장
            # print(f"{myPetDir}{petImage}")
        else:
            pass

