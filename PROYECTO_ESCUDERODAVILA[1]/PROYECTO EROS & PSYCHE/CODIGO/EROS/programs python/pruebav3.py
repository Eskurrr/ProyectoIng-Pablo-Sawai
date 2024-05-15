import busio
import time
import adafruit_ads1x15.ads1115 as ADS
from adafruit_ads1x15.analog_in import AnalogIn
import RPi.GPIO as GPIO
import socket

# Create a socket object
client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

# Define the server address and port
host = '192.168.210.66'
port = 1235

# Connect to the server
client_socket.connect((host, port))

# Configure GPIO pins
GPIO.setup(17, GPIO.IN)
GPIO.setup(27, GPIO.IN)

# Initialize I2C bus and ADC
i2c = busio.I2C(3, 2)
ads = ADS.ADS1115(i2c)
ads.mode = ADS.Mode.CONTINUOUS
chan = AnalogIn(ads, ADS.P0)

while True:
    if GPIO.input(17) == 1 or GPIO.input(27) == 1:
        print('!')
    else:
        # Read the ADC value and send it through the socket
        value = chan.value
        print(value)
        value_bytes = value.to_bytes(6, byteorder='big')  # Assuming ADC value is 16 bits
        client_socket.sendall(value_bytes)
    
    # Delay before the next reading
    time.sleep(0.4)