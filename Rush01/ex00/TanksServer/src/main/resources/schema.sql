create table if not exists tanks_game
(
    identifier serial
);

create unique index if not exists tanks_game_id
    on tanks_game (identifier);

alter table tanks_game
    drop constraint if exists tanks_game_pk;

alter table tanks_game
    add constraint tanks_game_pk
        primary key (identifier);

create table if not exists tanks_stats
(
    identifier serial,
    game int
        constraint tanks_game_fk
            references tanks_game,
    nickname varchar not null,
    shots int,
    hits int,
    misses int
);

create unique index if not exists tanks_stats_id
    on tanks_stats (identifier);

alter table tanks_stats
    drop constraint if exists tanks_stats_pk;

alter table tanks_stats
    add constraint tanks_stats_pk
        primary key (identifier);