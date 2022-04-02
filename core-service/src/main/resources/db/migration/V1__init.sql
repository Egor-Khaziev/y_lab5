create table players

(
    id          bigint,
    name        varchar(30) primary key,
    win         bigint,
    loss        bigint,
    series      bigint,
    simbol      varchar(5),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

insert into players (name, win, loss, series)
values ('MaXXX', 2,1,2),
       ('ZerOne', 1,2,0);
