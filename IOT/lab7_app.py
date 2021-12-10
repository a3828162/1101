# Origin : http://blog.miguelgrinberg.com/post/video-streaming-with-flask

from flask import Flask, render_template, Response
from camera_pi import Camera
import RPi.GPIO as GPIO
import requests
import random
import time
import cv2

def ButtonPressed(btn):
    global cnt
    cnt+=45
    if cnt>=360:
      cnt=0
#    print(cnt)


BTN_PIN = 12
GPIO.setmode(GPIO.BOARD)
GPIO.setup(BTN_PIN, GPIO.IN, pull_up_down=GPIO.PUD_UP)
GPIO.add_event_detect(BTN_PIN, GPIO.FALLING, ButtonPressed, 200)
cnt=0

app = Flask(__name__)

@app.route('/')
def index():
    return render_template('stream.html')
def gen(camera):
    global cnt
    while True:
#         frame = camera.get_frame()

         jpeg = camera.get_frame(cnt)
#         jpeg_rotate = jpeg.rotate(45)
         frame = jpeg
#         frame = jpeg.tostring()
#        rows, cols = jpeg.shape[:2]
#        M = cv2.getRotationMatrix2D((cols/2, rows/2), 45, 1)
#        rotation = cv2.warpAffine(jpeg, M, (cols, rows))
#        cv2.imshow('a',jpeg)
#        frame = rotation.tostring()
#        cv2.waitKey(0)
         yield (b'--frame\r\n'
               b'Content-Type: image/jpeg\r\n\r\n' + frame + b'\r\n\r\n')

@app.route('/video_feed')
def video_feed():
    return Response(gen(Camera()), mimetype='multipart/x-mixed-replace; boundar$

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=8000, debug=True)
