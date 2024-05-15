import time
import struct
import socket
import random
# Create a socket object
client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

# Define the server address and port
host = '192.168.214.66'
port = 1235

# Connect to the server
client_socket.connect((host, port))

while True:
    random_integer = random.randint(1, 4444)
    value_bytes = struct.pack('>i', random_integer) 
    client_socket.sendall(value_bytes)
    print(random_integer)
    time.sleep(0.4)
    