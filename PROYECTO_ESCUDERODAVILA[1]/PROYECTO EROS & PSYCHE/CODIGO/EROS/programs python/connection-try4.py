import busio
import time
import adafruit_ads1x15.ads1115 as ADS
from adafruit_ads1x15.analog_in import AnalogIn
import RPi.GPIO as GPIO
import datetime as dt
import matplotlib.pyplot as plt

i2c = busio.I2C(3, 2)
ads = ADS.ADS1115(i2c)
ads.mode = ADS.Mode.CONTINUOUS

GPIO.setup(17, GPIO.IN)
GPIO.setup(27, GPIO.IN)
chan = AnalogIn(ads, ADS.P0)

fig = plt.figure()
ax = fig.add_subplot(1, 1, 1)
xs = []
ys = []

for t in range(0, 10):
    if GPIO.input(17) == 1 :
        print('!')
    elif GPIO.input(27) == 1 :
        print('!')
    else:
        xs.append(dt.datetime.now().strftime('%H:%M:%S.%f'))
        ys.append(chan.value)
    time.sleep(0.4)
    
ax.plot(xs, ys)
plt.show()