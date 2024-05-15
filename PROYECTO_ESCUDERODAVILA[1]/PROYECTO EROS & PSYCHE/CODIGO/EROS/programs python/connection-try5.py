import busio
import time
import adafruit_ads1x15.ads1115 as ADS
from adafruit_ads1x15.analog_in import AnalogIn
import RPi.GPIO as GPIO
import datetime as dt
import matplotlib as plt

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
    while True:
        if GPIO.input(17) == 1 :
        elif GPIO.input(27) == 1 :
        else:
            ys.append(chan.value)
            break
    xs.append(dt.datetime.now().strftime('%H:%M:%S.%f'))
    xs = xs[-20:]
    ys = ys[-20:]
    ax.clear()
    ax.plot(xs, ys)

    # Format plot
    plt.xticks(rotation=45, ha='right')
    plt.subplots_adjust(bottom=0.30)
    plt.title('Pulse')

ani = animation.FuncAnimation(fig, animate, fargs=(xs, ys), interval=1000)
plt.show()