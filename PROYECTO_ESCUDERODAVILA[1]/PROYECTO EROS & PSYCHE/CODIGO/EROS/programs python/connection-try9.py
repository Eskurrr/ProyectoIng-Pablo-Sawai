import busio
import time
import adafruit_ads1x15.ads1115 as ADS
from adafruit_ads1x15.analog_in import AnalogIn
import RPi.GPIO as GPIO
import datetime as dt
import matplotlib.pyplot as plt
import matplotlib.animation as animation

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

def animate(i, xs, ys):
    i = 0
    while i != 1:
        if (GPIO.input(17) != 1) and (GPIO.input(27) != 1):
            ys.append(chan.value)
            i = 1
        
    xs.append(dt.datetime.now().strftime('%H:%M:%S.%f'))
    # Limit x and y lists to 20 items
    xs = xs[-20:]
    ys = ys[-20:]
    
    ax.clear()
    ax.plot(xs, ys)

    # Format plot
    plt.xticks(rotation=45, ha='right')
    plt.subplots_adjust(bottom=0.30)
    plt.title('TMP102 Temperature over Time')
    plt.ylabel('Temperature (deg C)')

anim = animation.FuncAnimation(fig, animate, fargs=(xs, ys), interval=1000)
plt.show()