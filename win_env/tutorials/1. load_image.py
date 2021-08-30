# 1. cv.imread
# 2. cv.imshow
# 3. cv.imwrite
# 4. cv.waitKey

import cv2 as cv
import sys
# img = cv.imread(cv.samples.findFile("pkq.jpg"))
img = cv.imread("pkq.jpg", flags=cv.IMREAD_GRAYSCALE)
# https://docs.opencv.org/4.5.3/d8/d6a/group__imgcodecs__flags.html#ga61d9b0126a3e57d9277ac48327799c80

if img is None:
    sys.exit("Could not read the image")

cv.imshow("Display window", img)
k = cv.waitKey(0)

if k == ord("s"):
    cv.imwrite("pkq.png", img)
