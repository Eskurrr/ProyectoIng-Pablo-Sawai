import board
import busio
import time
import adafruit_ads1x15.ads1115 as ADS
from adafruit_ads1x15.analog_in import AnalogIn
import RPi.GPIO as GPIO

GPIO.setmode(GPIO.BOARD)
i2c = busio.I2C(board.SCL, board.SDA)
ads = ADS.ADS1015(i2c)
ads.mode = Mode.CONTINUOUS

GPIO.setup(11, GPIO.IN)
GPIO.setup(13, GPIO.IN)
chan = AnalogIn(ads, ADS.P0)


while True:
    if GPIO.input(11) == 1 :
        print('!')
    elif GPIO.input(13) == 1 :
        print('!')
    else:
        print(chan.value)
    time.sleep(0.4)