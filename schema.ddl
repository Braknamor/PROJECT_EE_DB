
    alter table Album 
        drop constraint FK_kwuvprn1dehixr1mubkpmrptx

    alter table Album_Song 
        drop constraint FK_irnk9rq75wob9jni1vph3b1do

    alter table Album_Song 
        drop constraint FK_k4k1e185nu54qlpv2lcxxig1t

    alter table Artist 
        drop constraint FK_135j3ltiyk54gf23h6akxfove

    alter table artist_album 
        drop constraint FK_m66mdv2dkfwiwtsyfieabl6tg

    alter table artist_album 
        drop constraint FK_beyqy1dfohlhj7k10egu1t5qk

    drop table Album if exists

    drop table Album_Song if exists

    drop table Artist if exists

    drop table Label if exists

    drop table Song if exists

    drop table artist_album if exists

    drop sequence hibernate_sequence

    create table Album (
        id bigint not null,
        name varchar(255),
        picture varchar(255),
        ReleaseYear integer,
        label_id bigint,
        primary key (id)
    )

    create table Album_Song (
        albums_id bigint not null,
        songs_id bigint not null
    )

    create table Artist (
        id bigint not null,
        email varchar(255),
        name varchar(255),
        picture varchar(255),
        label_id bigint,
        primary key (id)
    )

    create table Label (
        id bigint not null,
        location varchar(255),
        name varchar(255),
        primary key (id)
    )

    create table Song (
        id bigint not null,
        name varchar(255),
        style varchar(255),
        primary key (id)
    )

    create table artist_album (
        artist_id bigint,
        id bigint not null,
        primary key (id)
    )

    alter table Album 
        add constraint FK_kwuvprn1dehixr1mubkpmrptx 
        foreign key (label_id) 
        references Label

    alter table Album_Song 
        add constraint FK_irnk9rq75wob9jni1vph3b1do 
        foreign key (songs_id) 
        references Song

    alter table Album_Song 
        add constraint FK_k4k1e185nu54qlpv2lcxxig1t 
        foreign key (albums_id) 
        references Album

    alter table Artist 
        add constraint FK_135j3ltiyk54gf23h6akxfove 
        foreign key (label_id) 
        references Label

    alter table artist_album 
        add constraint FK_m66mdv2dkfwiwtsyfieabl6tg 
        foreign key (artist_id) 
        references Artist

    alter table artist_album 
        add constraint FK_beyqy1dfohlhj7k10egu1t5qk 
        foreign key (id) 
        references Album

    create sequence hibernate_sequence start with 1
