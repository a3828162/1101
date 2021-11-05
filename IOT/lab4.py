import RPi.GPIO as GPIO
import time
import serial

ser = serial.Serial('/dev/ttyAMA1', baudrate=9600,
parity=serial.PARITY_NONE,
stopbits=serial.STOPBITS_ONE,
bytesize=serial.EIGHTBITS
)

GPIO.setmode(GPIO.BOARD)

LED_PIN = 12
GPIO.setup(LED_PIN, GPIO.OUT)

BUZZ_PIN = 16
pitches = [262, 294, 330, 349, 392, 440, 493]
GPIO.setup(BUZZ_PIN, GPIO.OUT)

pwm = GPIO.PWM(BUZZ_PIN, pitches[0])
pwm.start(0)

pwm2 = GPIO.PWM(LED_PIN, 100)
pwm2.start(0)

bright=50

def changeL(bri):
  global bright
  bright+=bri
  if bright>100:
    bright=100
  elif bright<0:
    bright=0
  pwm2.ChangeDutyCycle(int(bright))

try:
#  ser.write(b'Hello World\r\n')
#  ser.write(b'Serial Communication Using Raspberry Pi\r\n')
  pwm2.ChangeDutyCycle(int(bright))
  while True:
#    pwm.ChangeDutyCycle(50)
    data = ser.readline()
    print(data.decode("utf-8").strip())
    ser.write(data)
    ser.flushInput()
    #time.sleep(0.1)
    print(len(data.strip()))
    if data.strip() == b'playc':
      print("correct1")
      pwm.ChangeDutyCycle(50)
      pwm.ChangeFrequency(pitches[0])
    elif data.strip() == b'playd':
      pwm.ChangeDutyCycle(50)
      pwm.ChangeFrequency(pitches[1])
    elif data.strip() == b'playe':
      pwm.ChangeDutyCycle(50)
      pwm.ChangeFrequency(pitches[2])
    elif data.strip() == b'playf':
      pwm.ChangeDutyCycle(50)
      pwm.ChangeFrequency(pitches[3])
    elif data.strip() == b'playg':
      pwm.ChangeDutyCycle(50)
      pwm.ChangeFrequency(pitches[4])
    elif data.strip() == b'playa':
      pwm.ChangeDutyCycle(50)
      pwm.ChangeFrequency(pitches[5])
    elif data.strip() == b'playb':
      pwm.ChangeDutyCycle(50)
      pwm.ChangeFrequency(pitches[6])
    elif data.strip() == b'stop':
      pwm.ChangeDutyCycle(0)
    elif data.strip() == b'bri':
      changeL(10)
    elif data.strip() == b'dim':
      changeL(-10)

    time.sleep(0.1)


except KeyboardInterrupt:
  pass
finally:
  ser.close()
  pwm.stop()
  GPIO.cleanup()
