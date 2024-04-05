#!/usr/bin/python
# from BaseHTTPServer import BaseHTTPRequestHandler,HTTPServer
from http.server import BaseHTTPRequestHandler,HTTPServer
from socketserver import ThreadingMixIn
import json
import re
from urllib.parse import parse_qs
import cgi

PORT_NUMBER = 8099

# This class will handle any incoming request from
# a browser
class myHandler(BaseHTTPRequestHandler): # 괄호 안이 부모 클래스 (자바의 extends 같은 것)

    # Handler for the GET requests
    # do_get() 함수는 부모랑 동일한 함수명을 써서 오버라이드 한 것
    # 파이썬에서 첫 번째 파라미터는 무조건 self! (자바의  this)
    def do_GET(self):

        print('Get request received')
        if None != re.search('/api/*', self.path): # re(regular expression) 정규표현식 => 위에 import 되어 있음
            print('Get '+self.client_address[0])
            self.send_response(200) # 200 ok 응답 주면서 밑에 애들 뿌려 주는 것
            self.send_header('Content-type', 'text/html')
            self.end_headers()
            # Send the html message
            self.wfile.write(bytes("<html><head><title>Title goes here.</title></head>", "utf-8")) #"euc-kr"
            self.wfile.write(bytes("<body><p>This is a test.</p>", "utf-8"))
            self.wfile.write(bytes("<p>You accessed path: %s</p>" % self.path, "utf-8"))
            self.wfile.write(bytes("<p>Your IP: %s</p>" % self.client_address[0], "utf-8")) # %s 자리에 % 뒤에 있는 self.client_address[0] 이게 들어오는 것
            self.wfile.write(bytes("</body></html>", "utf-8"))

        else:
            self.send_response(400, 'Bad Request: record does not exist')
            self.send_header('Content-Type', 'application/json')
            self.end_headers()


# 스레드가 되는 애로 하겠다? 스레딩어쩌구 상속 받아야 함
class ThreadedHTTPServer(ThreadingMixIn, HTTPServer): # 부모 클래스가 두 개
    """Handle requests in a separate thread."""

try:

    # Create a web server and define the handler to manage the
    # incoming request
    #server = HTTPServer(('', PORT_NUMBER), myHandler)
    server = ThreadedHTTPServer(('', PORT_NUMBER), myHandler) # 서버 객체 만듦! myHandler 파라미터로 받는 것은 콜백 등록!
    # 서버를 HTTPS 스레드로 띄운다?
    # ( , ) 는 튜플
    # '' : 모든 ip 허용
    # PORT_NUMBER는 위에 8099로 전역변수 선언 되어 있음

    print ('Started httpserver on port ' , PORT_NUMBER)

    # Wait forever for incoming http requests
    server.serve_forever() # 객체만 생성하고 이거 호출 안 하면 서버 안 뜸!

except:
    print ('^C received, shutting down the web server')
    print("서버 종료1!!")
    server.socket.close()