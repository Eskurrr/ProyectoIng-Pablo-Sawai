import busio
import time
import adafruit_ads1x15.ads1115 as ADS
from adafruit_ads1x15.analog_in import AnalogIn
import RPi.GPIO as GPIO

i2c = busio.I2C(3, 2)
ads = ADS.ADS1115(i2c)
ads.mode = ADS.Mode.CONTINUOUS

GPIO.setup(17, GPIO.IN)
GPIO.setup(27, GPIO.IN)
chan = AnalogIn(ads, ADS.P0)


while True:
    if GPIO.input(17) == 1 :
        print('!')
    elif GPIO.input(27) == 1 :
        print('!')
    else:
        print(chan.value)
    time.sleep(0.4)