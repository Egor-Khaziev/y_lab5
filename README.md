## REST �������:
* GET ������ � 2�� ����������� player1 � player2
������� ����� ���� � 2�� ��������.

> http://localhost:8080/gameplay/new?player1=Maxxx&player2=����> 

� ����� �������� JSON c ������:\
player1 - PlayerDTO ������1  \
player2 - PlayerDTO ������2 \
gameMap - ����� �������� ���� \
winner - ���������� ����������� ������ ��� ������ ������ �� ����������
***
* POST ������ ������ ����.

> http://localhost:8080/gameplay/step

������ ���� �������:

{
"x" : 2,
"y" : 3,
"playerDTO": {
"name": "Maxxx",
"simbol": "X",
"id": 1
}
}

����� ���������� ������� ����
***
* POST ������ �� ��������������� ����������� ����.

> http://localhost:8080/gameplay/uploadJSON

���� ���������� ������ ����������� ���� �� game-100.json (��������� � �����): 

{
"gamePlay" : {
"player" : [ {
"name" : "Maxxx",
"simbol" : "X",
"id" : 1
}, {
"name" : "����",
"simbol" : "O",
"id" : 2
} ],
"game" : {
"step" : [ {
"num" : 0,
"playerId" : 1,
"x" : 1,
"y" : 1
}, {
"num" : 1,
"playerId" : 2,
"x" : 2,
"y" : 1
}, {
"num" : 2,
"playerId" : 1,
"x" : 1,
"y" : 2
}, {
"num" : 3,
"playerId" : 2,
"x" : 2,
"y" : 2
}, {
"num" : 4,
"playerId" : 1,
"x" : 1,
"y" : 3
} ]
},
"gameResult" : {
"player" : {
"name" : "Maxxx",
"simbol" : "X",
"id" : 1
}
}
}
}
## Console
> http://localhost:8080/gameplay/h2-console 

JDBS url: jdbc:h2:mem:testdb

username: sa

password:
## ���������� 
* ��� ����������� ��������� ��� ����������� � rating.txt (core-service)
* ���������� ������� �� ���� ����������� �����/���������/����� ����� ����������� � ���� players.txt\
���������: ������������ ����������� � SQL.
