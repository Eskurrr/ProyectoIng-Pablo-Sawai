import socket
IP = "192.168.181.66"
PORT = 6333
def server():
    server = socket.socket(socket.AF_INET, socket.SOCK_STREAM) 
    server.bind((IP,PORT))
    server.listen(0)
    print(f"Listening on {IP}:{PORT}")
    client_socket, client_address = server.accept()
    print(f"Accepted connection from {client_address[0]}:{client_address[1]}")
    while True:
        request = client_socket.recv(1024)
        request = request.decode("utf-8") # convert bytes to string

        # if we receive "close" from the client, then we break
        # out of the loop and close the conneciton
        if request.lower() == "close":
        # send response to the client which acknowledges that the
        # connection should be closed and break out of the loop
        client_socket.send("closed".encode("utf-8"))
        break

        print(f"Received: {request}")
        response = "accepted".encode("utf-8") # convert string to bytes
        # convert and send accept response to the client
        client_socket.send(response)
    
    client_socket.close()
    print("Connection to client closed")
    # close server socket
    server.close()


server()