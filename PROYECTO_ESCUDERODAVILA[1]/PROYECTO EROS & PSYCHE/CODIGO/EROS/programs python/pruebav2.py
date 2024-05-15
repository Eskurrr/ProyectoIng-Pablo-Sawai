import busio
import time
import adafruit_ads1x15.ads1115 as ADS
from adafruit_ads1x15.analog_in import AnalogIn
import RPi.GPIO as GPIO
import socket

s = socket.socket()
host = '192.168.210.66' # needs to be in quote
port = 1234
s.connect((host, port))
i2c = busio.I2C(3, 2)
ads = ADS.ADS1115(i2c)
ads.mode = ADS.Mode.CONTINUOUS

GPIO.setup(17, GPIO.IN)
GPIO.setup(27, GPIO.IN)
chan = AnalogIn(ads, ADS.P0)


while True:
    if GPIO.input(17) == 1 or GPIO.input(27) == 1 :
        time.sleep(0.2)
        if GPIO.input(17) == 1 or GPIO.input(27) == 1 :
            time.sleep(0.1)
        else:
            print(chan.value)
            num = chan.value
            B = num.to_bytes(4, byteorder='big')
            client_socket.sendall(B)
    else:
        print(chan.value)
        num = chan.value
        B = num.to_bytes(4, byteorder='big')
        client_socket.sendall(B)
    time.sleep(0.6)
