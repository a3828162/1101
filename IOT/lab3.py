import RPi.GPIO as GPIO
import time

def ButtonPressed(btn):
  global cnt
  cnt = (cnt + 1) % 4
  print("a")

GPIO.setmode(GPIO.BOARD)
BTN_PIN = 15
LED_PIN = 16
GPIO.setup(BTN_PIN, GPIO.IN, pull_up_down=GPIO.PUD_UP)
GPIO.setup(LED_PIN, GPIO.OUT)
GPIO.add_event_detect(BTN_PIN, GPIO.FALLING, ButtonPressed, 200)
cnt = 0
x = 0

try:

  while True:
    if cnt > 0:
      if x == 0:
        GPIO.output(LED_PIN, GPIO.LOW)
      else:
        GPIO.output(LED_PIN, GPIO.HIGH)

      x ^= 1

      if cnt == 1:
        time.sleep(1)
      elif cnt == 2:
        time.sleep(0.5)
      elif cnt == 3:
        time.sleep(0.25)
    else:
      GPIO.output(LED_PIN, GPIO.LOW)
except KeyboardInterrupt:
  print("Exception: KeyboardInterrupt")
finally:
  GPIO.cleanup()
