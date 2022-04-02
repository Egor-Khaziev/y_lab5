create table players

(
    name        varchar(30) primary key,
    win         bigint,
    loss        bigint,
    seriesWin   bigint,
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

insert into players (name, win, loss, seriesWin)
values ('MaXXX', 2,1,2),
       ('ZerOne', 1,2,0);
