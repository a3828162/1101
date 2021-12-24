from gtts import gTTS
import os
import speech_recognition as sr
import apa102
import time

LED_NUM = 3

leds = apa102.APA102(num_led=3)
colors = [[255,0,0],[0,255,0],[0,0,255]]

#obtain audio from the microphone
r=sr.Recognizer()

# recognize speech using Google Speech Recognition
try:
#    leds.cleanup()
    while True:

      with sr.Microphone(device_index = 2, sample_rate = 48000) as source:
        print("Please wait. Calibrating microphone...")
        #listen for 1 seconds and create the ambient noise energy level
        #r.adjust_for_ambient_noise(source, duration=1)
        r.energy_threshold = 4000
        print("Say something!")
        audio=r.listen(source)
      print("Google Speech Recognition thinks you said:")
      print(r.recognize_google(audio, language='zh-TW'))
      word = r.recognize_google(audio, language='zh-TW')
      if '關燈' in word:
        tts = gTTS(text='燈已關閉',lang='zh-TW')
        tts.save('tmp.mp3')
        os.system('omxplayer -o local -p tmp.mp3 > /dev/null 2>&1')
        #leds.set_pixel(i,color[0][0],color[0][1],color[0][2])
        leds.clear_strip()
      elif '開燈' in word:
        tts = gTTS(text='燈已開啟',lang='zh-TW')
        tts.save('tmp.mp3')
        os.system('omxplayer -o local -p tmp.mp3 > /dev/null 2>&1')
#        leds.set_pixel(0,colors[0][0],colors[0][1],colors[0][2],10)
        leds.set_pixel(0,255,0,0,10)
        leds.set_pixel(1,0,255,0,10)
        leds.set_pixel(2,0,0,255,10)
        leds.show()



#      tts = gTTS(word,lang='zh-TW')
#      tts.save('tmp.mp3')
#      os.system('omxplayer -o local -p tmp.mp3 > /dev/null 2>&1')

except sr.UnknownValueError:
    print("Google Speech Recognition could not understand audio")
    leds.clear_strip()
    leds.cleanup()
except sr.RequestError as e:
    print("No response from Google Speech Recognition service: {0}".format(e))
    leds.clear_strip()
    leds.cleanup()
