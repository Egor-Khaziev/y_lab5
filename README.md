## REST запросы:
* GET запрос с 2мя параметрами player1 и player2
создает новую игру с 2мя игроками.

> http://localhost:8080/gameplay/new?player1=Maxxx&player2=тест
***
* POST запрос на запись нового шага.

> http://localhost:8080/gameplay/step

пример тела запроса:

{
"x" : 2,
"y" : 3,
"playerDTO": {
"name": "Maxxx",
"simbol": "X",
"id": 1
}
}
***
* POST запрос на воспроизведение сохраненной игры.

> http://localhost:8080/gameplay/uploadJSON

ниже приводится пример сохраненной игры из game-100.json (находится в core-service): 

{
"gamePlay" : {
"player" : [ {
"name" : "Maxxx",
"simbol" : "X",
"id" : 1
}, {
"name" : "тест",
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
## Статистика 
* лог результатов прошедших игр сохраняется в rating.txt (core-service)
* сохранение игроков со всей статистикой побед/поражений/серии побед сохраняется в файл players.txt\
ДОБАВЛЕНО: дублирование результатов в SQL.
